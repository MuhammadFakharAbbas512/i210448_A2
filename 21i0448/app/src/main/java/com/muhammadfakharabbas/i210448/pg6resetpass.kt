package com.muhammadfakharabbas.i210448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class pg6resetpass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg6resetpass)

        var mAuth = FirebaseAuth.getInstance()
        var login_btn = findViewById<TextView>(R.id.login_btn)
        var reset_btn = findViewById<Button>(R.id.resetpass_btn)
        var back_btn = findViewById<Button>(R.id.back_btn)
        var pass = findViewById<TextView>(R.id.pass)
        var repass = findViewById<TextView>(R.id.repass)

        var email =  getIntent().getStringExtra("email")

        login_btn.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        reset_btn.setOnClickListener{

            val newPassword = pass.text.toString()
            val confirmPassword = repass.text.toString()

            // Check if passwords match
            if (newPassword != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth.sendPasswordResetEmail(email.toString())
                .addOnSuccessListener {
                    var msg = "Reset password sent to your email: "+email.toString()
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this,"Failed to Reset password", Toast.LENGTH_LONG).show()
                    finish()
                }

        }
        back_btn.setOnClickListener{
            val intent = Intent(this, pg5forgotpass::class.java)
            startActivity(intent)
        }
    }
}