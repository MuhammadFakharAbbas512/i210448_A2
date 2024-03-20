package com.muhammadfakharabbas.i210448

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class pg13calendar : AppCompatActivity() {
    //private lateinit var selectedTimeView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg13calander)

        var t0  = findViewById<TextView>(R.id.AM10)
        var t1  = findViewById<TextView>(R.id.AM11)
        var t2  = findViewById<TextView>(R.id.PM12)
        var t3  = findViewById<TextView>(R.id.PM1)
        var t4  = findViewById<TextView>(R.id.PM2)
        var t5  = findViewById<TextView>(R.id.PM5)

        var back_btn = findViewById<Button>(R.id.back_btn)
        var msg_btn = findViewById<ImageButton>(R.id.msg_btn)
        var call_btn = findViewById<ImageButton>(R.id.call_btn)
        var videocall_btn = findViewById<ImageButton>(R.id.videocall_btn)
        var bookappointment_btn = findViewById<Button>(R.id.bookappointment_btn)


        var name =  findViewById<TextView>(R.id.mentorname)
        var img =  findViewById<ImageView>(R.id.mentorimg)
        var fee =  findViewById<TextView>(R.id.mentorfee)
        val intent = intent
        val mid = intent.getStringExtra("mid")

        // Retrieve mentor details from the database using the mentor ID
        val mentorsRef = FirebaseDatabase.getInstance().getReference("mentors").child(mid.toString())
        mentorsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mentorName = dataSnapshot.child("name").getValue(String::class.java)
                val mentorDesc = dataSnapshot.child("description").getValue(String::class.java)
                val mentorImg = dataSnapshot.child("dpUrl").getValue(String::class.java)
                val mentorfee = dataSnapshot.child("fee").getValue(String::class.java)

                val msg = "mentorDesc: ${mentorDesc}, mName: ${mentorName}, "
                //Toast.makeText(this@pg10mentor, msg, Toast.LENGTH_LONG).show()
                if (mentorName != null && mentorDesc != null) {
                    name.text = mentorName
                    fee.text = mentorfee
                }
                mentorImg?.let {
                    Glide.with(this@pg13calendar)
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
        //default setting
        var selectedTime = t1.text.toString()

        t0.setOnClickListener {
           // selectTime(t0)
            val time = listOf(t0, t1, t2, t3, t4, t5)
            for (i in 0 until 6) {
                time[i].backgroundTintList = ContextCompat.getColorStateList(this, R.color.original_background_tint)
            }
            // Set the selected time view and change its backgroundTint
            t0.backgroundTintList = ContextCompat.getColorStateList(this, R.color.selected_background_tint)
            selectedTime = t0.text.toString()
        }
        t1.setOnClickListener {
           // selectTime(t1)
            val time = listOf(t0, t1, t2, t3, t4, t5)
            for (i in 0 until 6) {
                time[i].backgroundTintList = ContextCompat.getColorStateList(this, R.color.original_background_tint)
            }
            // Set the selected time view and change its backgroundTint
            t1.backgroundTintList = ContextCompat.getColorStateList(this, R.color.selected_background_tint)
            selectedTime = t1.text.toString()
        }
        t2.setOnClickListener {
           // selectTime(t2)
            val time = listOf(t0, t1, t2, t3, t4, t5)
            for (i in 0 until 6) {
                time[i].backgroundTintList = ContextCompat.getColorStateList(this, R.color.original_background_tint)
            }
            // Set the selected time view and change its backgroundTint
            t2.backgroundTintList = ContextCompat.getColorStateList(this, R.color.selected_background_tint)
            selectedTime = t2.text.toString()
        }
        t3.setOnClickListener {
         //   selectTime(t3)
            val time = listOf(t0, t1, t2, t3, t4, t5)
            for (i in 0 until 6) {
                time[i].backgroundTintList = ContextCompat.getColorStateList(this, R.color.original_background_tint)
            }
            // Set the selected time view and change its backgroundTint
            t3.backgroundTintList = ContextCompat.getColorStateList(this, R.color.selected_background_tint)
            selectedTime = t3.text.toString()
        }
        t4.setOnClickListener {
           // selectTime(t4)
            val time = listOf(t0, t1, t2, t3, t4, t5)
            for (i in 0 until 6) {
                time[i].backgroundTintList = ContextCompat.getColorStateList(this, R.color.original_background_tint)
            }
            // Set the selected time view and change its backgroundTint
            t4.backgroundTintList = ContextCompat.getColorStateList(this, R.color.selected_background_tint)
            selectedTime = t4.text.toString()
        }
        t5.setOnClickListener {
          //  selectTime(t5)
            val time = listOf(t0, t1, t2, t3, t4, t5)
            for (i in 0 until 6) {
                time[i].backgroundTintList = ContextCompat.getColorStateList(this, R.color.original_background_tint)
            }
            // Set the selected time view and change its backgroundTint
            t5.backgroundTintList = ContextCompat.getColorStateList(this, R.color.selected_background_tint)
            selectedTime = t5.text.toString()
        }
        back_btn.setOnClickListener{
            val intent = Intent(this, pg10mentor::class.java)
            intent.putExtra("mid",mid)
            startActivity(intent)
        }
        msg_btn.setOnClickListener{
            val intent = Intent(this, pg15chat_person::class.java)
            intent.putExtra("mid",mid)
            startActivity(intent)
        }
        call_btn.setOnClickListener{
            val intent = Intent(this, pg20call::class.java)
            startActivity(intent)
        }
        videocall_btn.setOnClickListener{
            val intent = Intent(this, pg19videocall::class.java)
            startActivity(intent)
        }
        bookappointment_btn.setOnClickListener{
            // Check if a time slot is selected
            if (t1 == null || t1 == null || t2 == null || t3 == null || t4 == null || t5 == null) {
                Toast.makeText(this, "Please select a time slot", Toast.LENGTH_SHORT).show()

            } else {
                // Save booking data to the database
                saveBookingData(selectedTime,mid)
                Toast.makeText(this, "Saving time slot for booking", Toast.LENGTH_SHORT).show()

            }
        }
    }

 /*   private fun selectTime(timeView: TextView) {
        // Set previously selected time view back to original backgroundTint
        selectedTimeView?.backgroundTintList = ContextCompat.getColorStateList(this, R.color.original_background_tint)

        // Set the selected time view and change its backgroundTint
        selectedTimeView = timeView
        selectedTimeView.backgroundTintList = ContextCompat.getColorStateList(this, R.color.selected_background_tint)
    }*/

    private fun saveBookingData(selectedTime:String ,mentorId: String?) {
        // Check if mentorId is not null
        if (!mentorId.isNullOrBlank()) {
            // Reference to the mentor's bookedTime in the database
            val bookedTimeRef = FirebaseDatabase.getInstance().getReference("mentors").child(mentorId).child("bookedTime")

            // Get the selected time
          //  val selectedTime = selectedTimeView.text.toString()

            // Update the bookedTime in the database with the selected time
            bookedTimeRef.setValue(selectedTime+" on 30-12-2025")
                .addOnSuccessListener {
                    Toast.makeText(this@pg13calendar, "Appointment Booked", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this@pg13calendar, "Error booking appointment: $e", Toast.LENGTH_LONG).show()
                    Log.e(TAG, "Error booking appointment", e)
                }
        } else {
            Toast.makeText(this@pg13calendar, "Mentor ID is null", Toast.LENGTH_LONG).show()
            Log.e(TAG, "Mentor ID is null")
        }
    }

    companion object {
        private const val TAG = "pg13calendar"
    }
}
