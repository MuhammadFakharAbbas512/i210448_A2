package com.muhammadfakharabbas.i210448

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class pg11review : AppCompatActivity() {

    private lateinit var mid: String
    private lateinit var review: EditText
    private lateinit var oneStar: ImageView
    private lateinit var twoStar: ImageView
    private lateinit var threeStar: ImageView
    private lateinit var fourStar: ImageView
    private lateinit var fiveStar: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg11review)

        var name =  findViewById<TextView>(R.id.mentorname)
        var img =  findViewById<ImageView>(R.id.mentorimg)

        var back_btn = findViewById<Button>(R.id.back_btn)
        var feedback_btn = findViewById<Button>(R.id.feedback_btn)


        val intent = intent
        mid = intent.getStringExtra("mid").toString()

        // Retrieve mentor details from the database using the mentor ID
        val mentorsRef = FirebaseDatabase.getInstance().getReference("mentors").child(mid)
        mentorsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mentorName = dataSnapshot.child("name").getValue(String::class.java)
                val mentorImg = dataSnapshot.child("dpUrl").getValue(String::class.java)

               // val msg = "mentorDesc: ${mentorDesc}, mName: ${mentorName}, "
                //Toast.makeText(this@pg10mentor, msg, Toast.LENGTH_LONG).show()
                if (mentorName != null ) {
                    name.text = mentorName

                }
                mentorImg?.let {
                    Glide.with(this@pg11review)
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
            val intent = Intent(this, pg10mentor::class.java)
            startActivity(intent)
        }
        review = findViewById(R.id.review)
        oneStar = findViewById(R.id.star1)
        twoStar = findViewById(R.id.star2)
        threeStar = findViewById(R.id.star3)
        fourStar = findViewById(R.id.star4)
        fiveStar = findViewById(R.id.star5)

        oneStar.setOnClickListener { setStarRating(1) }
        twoStar.setOnClickListener { setStarRating(2) }
        threeStar.setOnClickListener { setStarRating(3) }
        fourStar.setOnClickListener { setStarRating(4) }
        fiveStar.setOnClickListener { setStarRating(5) }

        val feedbackBtn = findViewById<Button>(R.id.feedback_btn)
        feedbackBtn.setOnClickListener { saveReview() }


    }

    private var rating = 0

    private fun setStarRating(selectedRating: Int) {
        rating = selectedRating
        val stars = listOf(oneStar, twoStar, threeStar, fourStar, fiveStar)
        for (i in 0 until selectedRating) {
            stars[i].setImageResource(R.drawable.star_rate)
        }
        for (i in selectedRating until 5) {
            stars[i].setImageResource(R.drawable.baseline_star_outline_24)
        }
    }
    private fun saveReview() {
        val reviewText = review.text.toString().trim()
        if (reviewText.isNotEmpty()) {
            val mentorId = intent.getStringExtra("mentorId")
            val reviewCategory = "reviews" // Define your review category here
            val userId = FirebaseAuth.getInstance().currentUser?.uid

            val database = FirebaseDatabase.getInstance().reference
            val reviewRef = database.child("mentors").child(mid).child(reviewCategory).push()
            val reviewData = HashMap<String, Any>()
            reviewData["userId"] = userId!!
            reviewData["rating"] = rating
            reviewData["review"] = reviewText

            reviewRef.setValue(reviewData)
                .addOnSuccessListener {
                    Toast.makeText(this, "Review saved successfully", Toast.LENGTH_SHORT).show()
                    finish() // Close the activity after saving the review
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to save review", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "Please enter a review", Toast.LENGTH_SHORT).show()
        }
    }
}