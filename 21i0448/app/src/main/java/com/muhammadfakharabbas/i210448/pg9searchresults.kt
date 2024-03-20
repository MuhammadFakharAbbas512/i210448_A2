package com.muhammadfakharabbas.i210448

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class pg9searchresults : AppCompatActivity() {
    private lateinit var currentUser: FirebaseUser
    private lateinit var databaseReference: DatabaseReference
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg9searchresults)

        var back_btn = findViewById<Button>(R.id.back_btn)
        var home_btn = findViewById<ImageView>(R.id.home_img)
        var search_btn = findViewById<ImageView>(R.id.search_img)
        var add_btn = findViewById<ImageView>(R.id.add_img)
        var chat_btn = findViewById<ImageView>(R.id.chat_img)
        var profile_btn = findViewById<ImageView>(R.id.profile_img)

        val mentorName = findViewById<TextView>(R.id.mentorname)
        val mentorDesc = findViewById<TextView>(R.id.mentordesc)
        val mentorStatus = findViewById<TextView>(R.id.mentorstatus)
        val imgUser = findViewById<ImageView>(R.id.mentorimg)
        var fee = findViewById<TextView>(R.id.mentorfee)

        var mid = intent.getStringExtra("mid").toString()
        currentUser = FirebaseAuth.getInstance().currentUser!!
        databaseReference = FirebaseDatabase.getInstance().getReference("mentors").child(mid)


        // Retrieve mentor data from Firebase Realtime Database using mentor ID (mid)
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Check if mentor data exists in the database
                if (dataSnapshot.exists()) {
                    // Get mentor data from the snapshot
                    val mentorNameValue = dataSnapshot.child("name").getValue(String::class.java)
                    val mentorDescValue = dataSnapshot.child("description").getValue(String::class.java)
                    val mentorStatusValue = dataSnapshot.child("status").getValue(String::class.java)
                    val mentorImageUrl = dataSnapshot.child("dpUrl").getValue(String::class.java)
                    val mentorFeeValue = dataSnapshot.child("fee").getValue(String::class.java)

                    // Set mentor data to the corresponding views
                    mentorName.text = mentorNameValue
                    mentorDesc.text = mentorDescValue
                    mentorStatus.text = mentorStatusValue
                    fee.text = mentorFeeValue

                    // Load mentor image using Glide or any other image loading library
                    if (!mentorImageUrl.isNullOrEmpty()) {
                        Glide.with(this@pg9searchresults)
                            .load(mentorImageUrl)
                            .placeholder(R.drawable.profile_image) // Placeholder image while loading
                            .error(R.drawable.profile_image) // Error image if loading fails
                            .into(imgUser)
                    }
                } else {
                    // Handle case where mentor data does not exist
                    Toast.makeText(this@pg9searchresults, "Mentor data not found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                Log.e(TAG, "Error retrieving mentor data: $error")
                Toast.makeText(this@pg9searchresults, "Error retrieving mentor data", Toast.LENGTH_SHORT).show()
            }
        })
        imgUser.setOnClickListener{
            Intent(this, pg10mentor::class.java)
            intent.putExtra("mid",mid)
            startActivity(intent)
        }
        mentorName.setOnClickListener{
            Intent(this, pg10mentor::class.java)
            intent.putExtra("mid",mid)
            startActivity(intent)
        }



        back_btn.setOnClickListener{
            val intent = Intent(this, pg8find::class.java)
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

    }
}