package com.muhammadfakharabbas.i210448

// Add imports for necessary classes
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class pg15chat_person : AppCompatActivity() {
    // Define constants for request codes
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_AUDIO_CAPTURE = 2
    private val REQUEST_FILE_PICK = 3

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var chatList: ArrayList<ChatMessageModel>
    private lateinit var databaseRef: DatabaseReference
    private var firebaseUser: FirebaseUser? = null
    private lateinit var userId: String
   /* private lateinit var dp: ImageView
    private lateinit var file: ImageView
    private lateinit var audio: ImageView*/

    // Define variables for storage reference and current file URI
    private var storageRef: StorageReference? = null
    private var fileUri: Uri? = null
    private var audioUri: Uri? = null
    private var dpUri: Uri? = null

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            dpUri = uri
            findViewById<ImageView>(R.id.img).setImageURI(uri)
        }
    }
    // Function to get URI for a file
    private val pickFile = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            fileUri = uri
            // Do something with the file URL, such as storing it or displaying it
            // For example, you can show the file URL in a TextView
            findViewById<ImageView>(R.id.file).setImageURI(fileUri)
        }
    }

    // Function to get URI for an audio file
    private val pickAudio = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            audioUri = uri
            // Do something with the audio URL, such as storing it or playing it
            // For example, you can play the audio using MediaPlayer
            val mediaPlayer = MediaPlayer.create(this, uri)
           // mediaPlayer.start()
        }
    }

    // Modify onCreate to initialize storage reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg15chat_john_person)

        // Initialize storage reference
        storageRef = FirebaseStorage.getInstance().reference
        chatRecyclerView = findViewById(R.id.chatRecyclerView)
        chatList = ArrayList()
        chatAdapter = ChatAdapter(this, chatList)
        chatRecyclerView.adapter = chatAdapter
        //chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        var intent = getIntent()
        var recvid = intent.getStringExtra("userId")
        var mentor = intent.getStringExtra("mid")
        if(mentor != null){
            recvid  = mentor
        }
        var userName = intent.getStringExtra("userName")
        val msg = "Chat recv ID: ${recvid}, Name: ${userName}"
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()


        var tvUserName = findViewById<TextView>(R.id.tvUserName)
        tvUserName.text = userName
        var etMessage = findViewById<EditText>(R.id.msg)

        val backBtn = findViewById<Button>(R.id.back_btn)
        val btnSendMessage = findViewById<ImageView>(R.id.send)
        val camBtn = findViewById<ImageView>(R.id.cam_btn)
        val audio = findViewById<ImageView>(R.id.audio)     // implement for audio button to select and save audio file
        val dp = findViewById<ImageView>(R.id.img)          // implement for dp button to select and save image file
        val file = findViewById<ImageView>(R.id.file)       // implement for file button to select and save files (pdf,txt,.etc)
        val callBtn = findViewById<ImageView>(R.id.call_btn)
        val videoCallBtn = findViewById<ImageView>(R.id.videocall_btn)

        val homeBtn = findViewById<ImageView>(R.id.home_img)
        val searchBtn = findViewById<ImageView>(R.id.search_img)
        val addBtn = findViewById<ImageView>(R.id.add_img)
        val chatBtn = findViewById<ImageView>(R.id.chat_img)
        val profileBtn = findViewById<ImageView>(R.id.profile_img)

        backBtn.setOnClickListener {
            onBackPressed()
        }
        homeBtn.setOnClickListener {
            startActivity(Intent(this, pg7home::class.java))
        }

        searchBtn.setOnClickListener {
            startActivity(Intent(this, pg8find::class.java))
        }

        addBtn.setOnClickListener {
            startActivity(Intent(this, pg12addmentor::class.java))
        }

        chatBtn.setOnClickListener {
            startActivity(Intent(this, pg14chat::class.java))
        }

        profileBtn.setOnClickListener {
            startActivity(Intent(this, pg21profile::class.java))
        }

        videoCallBtn.setOnClickListener {
            startActivity(Intent(this, pg19videocall::class.java))
        }

        callBtn.setOnClickListener {
            startActivity(Intent(this, pg20call::class.java))
        }

        camBtn.setOnClickListener {
            startActivity(Intent(this, pg17photo::class.java))
        }

        // Add button click listeners to capture audio, capture image, and select file
        audio.setOnClickListener {
            pickAudio.launch("audio/*")
        }

        dp.setOnClickListener {
            pickImage.launch("image/*")
        }

        file.setOnClickListener {
            pickFile.launch("*/*")
        }

        firebaseUser = FirebaseAuth.getInstance().currentUser

        // Get user ID from Firebase Authentication
        val currentUserID = firebaseUser?.uid

        // Reference to Firebase Realtime Database
        val usersRef = FirebaseDatabase.getInstance().reference.child("users").child(currentUserID.toString())

        // Retrieve user data from Firebase Realtime Database
        usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val userName = dataSnapshot.child("name").value.toString()
                    userId = dataSnapshot.child("userId").value.toString()
                    // Start reading messages after getting user data
                    readMessage(currentUserID!!, recvid.toString())
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle database error
            }
        })

        btnSendMessage.setOnClickListener {
            val message: String = etMessage.text.toString().trim()
            if (message.isEmpty()) {
                Toast.makeText(this, "Message is empty", Toast.LENGTH_SHORT).show()
            } else {
                sendMessage(currentUserID!!, recvid.toString(), message)
                etMessage.setText("")
                Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show()
                // Handle notification sending
            }
        }

        // Rest of the code remains unchanged
    }

    // Functions to send and recv only text:
    private fun sendMessage(senderId: String, receiverId: String, message: String) {
        val reference = FirebaseDatabase.getInstance().getReference("Chat")

        val hashMap = HashMap<String, String>()
        hashMap["senderId"] = senderId
        hashMap["receiverId"] = receiverId
        hashMap["message"] = message

        reference.push().setValue(hashMap)
    }

    private fun readMessage(senderId: String, receiverId: String) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("Chat")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                chatList.clear()
                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val chat = dataSnapShot.getValue(ChatMessageModel::class.java)
                    if ((chat!!.senderId == senderId && chat.receiverId == receiverId) ||
                        (chat.senderId == receiverId && chat.receiverId == senderId)
                    ) {
                        chatList.add(chat)
                    }
                }
                chatAdapter = ChatAdapter(this@pg15chat_person, chatList)
                chatRecyclerView.adapter = chatAdapter
                chatAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    // Function to send audio message
    private fun sendAudioMessage(senderId: String, receiverId: String, audioUri: Uri) {
        // Implement logic to upload audio to Firebase Storage
        // Then, send a message containing the download URL

    }

    // Function to send image message
    private fun sendImageMessage(senderId: String, receiverId: String, imageUri: Uri) {
        // Implement logic to upload image to Firebase Storage
        // Then, send a message containing the download URL
    }

    // Function to send file message
    private fun sendFileMessage(senderId: String, receiverId: String, fileUri: Uri) {
        // Implement logic to upload file to Firebase Storage
        // Then, send a message containing the download URL
    }


    // Modify onActivityResult to handle audio and file selection
   /* private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                intent?.data?.let { uri ->
                    if (requestCode == REQUEST_AUDIO_CAPTURE) {
                        // Send audio message
                        sendAudioMessage(currentUserID!!, recvid.toString(), uri)
                    } else if (requestCode == REQUEST_FILE_PICK) {
                        // Send file message
                        sendFileMessage(currentUserID!!, recvid.toString(), uri)
                    }
                }
            }
        }*/
}

