package com.muhammadfakharabbas.i210448

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class pg21profile : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg21myprofile)

        val userName = findViewById<TextView>(R.id.userName)
        val userImg = findViewById<ImageView>(R.id.img)
        val auth = FirebaseAuth.getInstance()
        val backBtn = findViewById<Button>(R.id.back_btn)
        var home_btn = findViewById<ImageView>(R.id.home_img)
        var search_btn = findViewById<ImageView>(R.id.search_img)
        var add_btn = findViewById<ImageView>(R.id.add_img)
        var chat_btn = findViewById<ImageView>(R.id.chat_img)
        var profile_btn = findViewById<ImageView>(R.id.profile_img)

        var editprofilepic_btn = findViewById<ImageView>(R.id.editprofilepic_btn)
        var editprofile_btn = findViewById<ImageView>(R.id.editprofile_btn)
        var booked_sessions = findViewById<Button>(R.id.booked_sessions)
        var john_btn = findViewById<ImageButton>(R.id.john_btn)


        val currentUser = auth.currentUser
        val userId = currentUser?.uid

        val userRef = FirebaseDatabase.getInstance().getReference("users").child(userId.toString())
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val name = snapshot.child("name").getValue(String::class.java)
                val profileImage = snapshot.child("profileImage").getValue(String::class.java)

                userName.text = name

                // Load user image using Picasso library
                if (!profileImage.isNullOrEmpty()) {
                    Picasso.get().load(profileImage).into(userImg)
                    //Picasso.get().load(profileImage).into(coverImg)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled
            }
        })

        backBtn.setOnClickListener {
            val intent = Intent(this, pg7home::class.java)
            startActivity(intent)
        }

        editprofilepic_btn.setOnClickListener{
            val intent = Intent(this, pg22edit_profile::class.java)
            startActivity(intent)
        }
        editprofile_btn.setOnClickListener{
            val intent = Intent(this, pg22edit_profile::class.java)
            startActivity(intent)
        }
        booked_sessions.setOnClickListener{
            val intent = Intent(this, pg23bookedsession::class.java)
            startActivity(intent)
        }
        john_btn.setOnClickListener{
            val intent = Intent(this, pg10mentor::class.java)
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