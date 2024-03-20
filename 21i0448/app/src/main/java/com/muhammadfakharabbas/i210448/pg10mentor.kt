package com.muhammadfakharabbas.i210448

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class pg10mentor : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg10mentorpg)
        var back_btn = findViewById<Button>(R.id.back_btn)
        var review_btn = findViewById<Button>(R.id.review_btn)
        var join_btn = findViewById<Button>(R.id.community_btn)
        var booksession_btn = findViewById<Button>(R.id.booksession_btn)

        var name =  findViewById<TextView>(R.id.mentorname)
        var img =  findViewById<ImageView>(R.id.mentorimg)
        var desc =  findViewById<TextView>(R.id.mentordesc)

        val intent = intent
        val mid = intent.getStringExtra("mid")

        // Retrieve mentor details from the database using the mentor ID
        val mentorsRef = FirebaseDatabase.getInstance().getReference("mentors").child(mid.toString())
        mentorsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mentorName = dataSnapshot.child("name").getValue(String::class.java)
                val mentorDesc = dataSnapshot.child("description").getValue(String::class.java)
                val mentorImg = dataSnapshot.child("dpUrl").getValue(String::class.java)

                val msg = "mentorDesc: ${mentorDesc}, mName: ${mentorName}, "
                //Toast.makeText(this@pg10mentor, msg, Toast.LENGTH_LONG).show()
                if (mentorName != null && mentorDesc != null) {
                   name.text = mentorName
                    desc.text = mentorDesc
                }
                mentorImg?.let {
                    Glide.with(this@pg10mentor)
                        .load(mentorImg)
                        .placeholder(R.drawable.profile_image) // Placeholder image while loading
                        .error(R.drawable.profile_image) // Error image if loading fails
                        .into(img)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        back_btn.setOnClickListener{
            val intent = Intent(this, pg7home::class.java)
            startActivity(intent)
        }
        review_btn.setOnClickListener{
            val intent = Intent(this, pg11review::class.java)
            intent.putExtra("mid",mid)
            startActivity(intent)
        }
        join_btn.setOnClickListener{
            val intent = Intent(this, pg16chat_community::class.java) //pg16
            startActivity(intent)
        }
        booksession_btn.setOnClickListener{
            val intent = Intent(this, pg13calendar::class.java)
            startActivity(intent)
        }
    }
}
