package com.muhammadfakharabbas.i210448

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class pg7home : ScreenshotDetectionActivity()  {

    private lateinit var mentorAdapter: MentorAdapter
    private lateinit var mentorRecyclerView: RecyclerView
    private lateinit var mentorList: ArrayList<MentorModel>
    private lateinit var currentUser: FirebaseUser
    private lateinit var databaseReference: DatabaseReference
    private var firebaseUser: FirebaseUser? = null
    private lateinit var mid: String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg7home)


        mentorRecyclerView = findViewById(R.id.mentorRecyclerView)

        mentorList = ArrayList()
        mentorAdapter = MentorAdapter(this, mentorList)
        mentorRecyclerView.adapter = mentorAdapter
        mentorRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        currentUser = FirebaseAuth.getInstance().currentUser!!
        databaseReference = FirebaseDatabase.getInstance().getReference("mentors")
        getMentorsList()

        var auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val userId = currentUser?.uid
        val database = FirebaseDatabase.getInstance().reference
        var name = findViewById<TextView>(R.id.name)
        //name.setText(getIntent().getStringExtra("userName").toString())

        val userRef = database.child("users").child(userId.toString())
        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Retrieve user data from the database
                    val userName= dataSnapshot.child("name").getValue(String::class.java)
                    name.text = userName
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("Home Activity", "Failed to read user data: ${databaseError.message}")
                Toast.makeText(this@pg7home, "Failed to read user data", Toast.LENGTH_SHORT).show()
            }
        })

        var bell_btn = findViewById<Button>(R.id.notification_btn)
     //   var john_btn = findViewById<ImageButton>(R.id.john_btn)
        val logout=findViewById<Button>(R.id.logout)
        var home_btn = findViewById<ImageView>(R.id.home_img)
        var search_btn = findViewById<ImageView>(R.id.search_img)
        var add_btn = findViewById<ImageView>(R.id.add_img)
        var chat_btn = findViewById<ImageView>(R.id.chat_img)
        var profile_btn = findViewById<ImageView>(R.id.profile_img)
        /*var intent = getIntent()
        var mid = intent.getStringExtra("mid")
        var mName = intent.getStringExtra("mentorName")
        val msg = "Mentor ID: ${mid}, Mentor name: ${mName}"
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()*/

        logout.setOnClickListener {
            var mAuth= FirebaseAuth.getInstance()
            mAuth.signOut()
            var i=Intent(this,Login::class.java)
            startActivity(i)
            Toast.makeText(this,"Logged Out", Toast.LENGTH_LONG).show()
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

        bell_btn.setOnClickListener{
            val intent = Intent(this, pg24notification::class.java)
            startActivity(intent)
        }
    /*    john_btn.setOnClickListener{
            val intent = Intent(this, pg10mentor::class.java)
            startActivity(intent)
        }*/
    }
    private fun getMentorsList() {
        databaseReference = FirebaseDatabase.getInstance().getReference("mentors")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@pg7home, "Database error: ${error.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                mentorList.clear()
                for (dataSnapshot in snapshot.children) {
                    val mentor = dataSnapshot.getValue(MentorModel::class.java)
                    mentor?.let {
                        mentorList.add(mentor)
                        val msg = "MID: ${mentor.mid}, Name: ${mentor.name}"
                     //   Toast.makeText(this@pg7home, msg, Toast.LENGTH_LONG).show()
                    }
                }
                mentorAdapter.notifyDataSetChanged() // Notify the adapter of changes
            }
        })
    }


}