package com.muhammadfakharabbas.i210448

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class pg12addmentor : AppCompatActivity() {

    private var dpUri: Uri? = null
    private var videoUri: Uri? = null

    private lateinit var dp: ImageView
    private lateinit var video: ImageView

    private lateinit var storageRef: StorageReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg12addmentor)

        dp = findViewById(R.id.photo_btn)
        val name = findViewById<EditText>(R.id.name)
        val desc = findViewById<EditText>(R.id.desc)
        val status = findViewById<EditText>(R.id.status)
        val fee = findViewById<EditText>(R.id.fee)
        video = findViewById(R.id.video_btn)

        var back_btn = findViewById<Button>(R.id.back_btn)
        var upload_btn = findViewById<Button>(R.id.upload_btn)
        var video_btn1 = findViewById<Button>(R.id.video_btn2)
        var photo_btn1 = findViewById<Button>(R.id.photo_btn2)


        var home_btn = findViewById<ImageView>(R.id.home_img)
        var search_btn = findViewById<ImageView>(R.id.search_img)
        var add_btn = findViewById<ImageView>(R.id.add_img)
        var chat_btn = findViewById<ImageView>(R.id.chat_img)
        var profile_btn = findViewById<ImageView>(R.id.profile_img)

        var auth = FirebaseAuth.getInstance()
        storageRef = FirebaseStorage.getInstance().reference

        // Other code...

        back_btn.setOnClickListener{
            val intent = Intent(this, pg7home::class.java)
            startActivity(intent)
        }
        home_btn.setOnClickListener{
            val intent = Intent(this, pg7home::class.java)
            startActivity(intent)
        }
        search_btn.setOnClickListener{
            val intent = Intent(this, pg8find::class.java)
            startActivity(intent)
        }
        add_btn.setOnClickListener{
            val intent = Intent(this, pg12addmentor::class.java)
            startActivity(intent)
        }
        chat_btn.setOnClickListener{
            val intent = Intent(this, pg14chat::class.java)
            startActivity(intent)
        }
        profile_btn.setOnClickListener{
            val intent = Intent(this, pg21profile::class.java)
            startActivity(intent)
        }

        photo_btn1.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_REQUEST_CODE)
        }

        video_btn1.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "video/*"
            startActivityForResult(intent, VIDEO_REQUEST_CODE)
        }

        upload_btn.setOnClickListener {
            val mentorName = name.text.toString()
            val mentorDesc = desc.text.toString()
            val mentorStatus = status.text.toString()
            val mentorfee = fee.text.toString()

            if (name == null || desc == null || status == null) {
                // Handle the case where any EditText view is null
                Toast.makeText(this, "One or more fields are null", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val mentorId = FirebaseDatabase.getInstance().getReference("mentors").push().key
            if (mentorId != null) {


            // Check if both image and video are selected
            if (dpUri != null || videoUri != null) {

                // Upload profile image (dp) to Firebase Storage
                val dpRef = storageRef.child("mentors").child("${System.currentTimeMillis()}_dp.jpg")
                dpRef.putFile(dpUri!!)
                    .addOnSuccessListener { dpTask ->
                        dpTask.storage.downloadUrl.addOnSuccessListener { dpDownloadUrl ->
                            // Save mentor data and image URL to the database
                            val mentorRef = FirebaseDatabase.getInstance().getReference("mentors")
                                .child(mentorId)
                            val mentorData = HashMap<String, Any>()
                            mentorData["mid"] =  mentorId     // how to get mentor id here
                            mentorData["name"] = mentorName
                            mentorData["description"] = mentorDesc
                            mentorData["status"] = mentorStatus
                            mentorData["fee"] = mentorfee
                            mentorData["dpUrl"] = dpDownloadUrl.toString()

                            // Check if video URI is not null
                            if (videoUri != null) {
                                val videoRef = storageRef.child("mentors")
                                    .child("${System.currentTimeMillis()}_video.mp4")
                                videoRef.putFile(videoUri!!)
                                    .addOnSuccessListener { videoTask ->
                                        videoTask.storage.downloadUrl.addOnSuccessListener { videoDownloadUrl ->
                                            mentorData["videoUrl"] = videoDownloadUrl.toString()

                                            mentorRef.setValue(mentorData)
                                                .addOnSuccessListener {
                                                    Toast.makeText(this, "Mentor added successfully", Toast.LENGTH_SHORT).show()
                                                    // Navigate back to the home screen after mentor is added

                                                    val intent = Intent(this, pg7home::class.java)
                                                    startActivity(intent)
                                                }
                                                .addOnFailureListener { e ->
                                                    Toast.makeText( this,"Failed to add mentor: ${e.message}", Toast.LENGTH_SHORT).show()
                                                }
                                        }
                                    }
                            } else {
                                mentorRef.setValue(mentorData)
                                    .addOnSuccessListener {
                                        Toast.makeText(this, "Mentor added successfully", Toast.LENGTH_SHORT).show()
                                        // Navigate back to the home screen after mentor is added
                                        val intent = Intent(this, pg7home::class.java)
                                        startActivity(intent)
                                    }
                                    .addOnFailureListener { e ->
                                        Toast.makeText(this, "Failed to add mentor: ${e.message}", Toast.LENGTH_SHORT).show()
                                    }
                            }
                        }
                    }
                }
            else {
                Toast.makeText(this, "Please select one (image or video)", Toast.LENGTH_SHORT).show()
            }
            } else {
                Toast.makeText(this, "Id cannot be null", Toast.LENGTH_SHORT).show()
            }

        }
        // Other code...
    }

    companion object {
        private const val IMAGE_REQUEST_CODE = 1
        private const val VIDEO_REQUEST_CODE = 2
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_REQUEST_CODE) {
                dpUri = data?.data
                dp.setImageURI(dpUri)
            } else if (requestCode == VIDEO_REQUEST_CODE) {
                videoUri = data?.data
                video.setImageURI(videoUri)
            }
        }
    }
}
