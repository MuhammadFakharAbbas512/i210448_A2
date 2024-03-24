package com.muhammadfakharabbas.i210448

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //onc()
       //  waiter()
       var auth = FirebaseAuth.getInstance()

        // Check if user is already logged in
        val currentUser = auth.currentUser
        val userId = currentUser?.uid
        val database = FirebaseDatabase.getInstance().reference

        val userRef = database.child("users").child(userId.toString())

        Handler().postDelayed({
            if (currentUser != null) {
                // User is already logged in, navigate to home screen
                userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // Retrieve user data from the database
                            val userName = dataSnapshot.child("name").getValue(String::class.java)

                            val intent = Intent(this@MainActivity, pg7home::class.java)
                            intent.putExtra("userName", userName)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@MainActivity, "User data not found", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.e("MainActivity", "Failed to read user data (name): ${databaseError.message}")
                        Toast.makeText(this@MainActivity, "Failed to read user data (name)", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                // User is not logged in, navigate to signup screen
                val intent = Intent(this, Signup::class.java)
                startActivity(intent)
            }
            finish() // Finish the Splash screen activity
        }, 5000) // Delay for 5 seconds (5000 milliseconds)


    }
    fun onc(){
        var txt_mentor = findViewById<TextView>(R.id.txt_mentor)
        var txt_me = findViewById<TextView>(R.id.txt_me)
        txt_mentor.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        txt_me.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
    suspend fun waiter(){
        delay(5000) // 5 secs delay
    }

}