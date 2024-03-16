package com.muhammadfakharabbas.i210448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
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
        Handler().postDelayed({
            if (currentUser != null) {
                // User is already logged in, navigate to home screen
                val intent = Intent(this, pg7home::class.java)
                startActivity(intent)
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