package com.muhammadfakharabbas.i210448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        val email=findViewById<EditText>(R.id.email)
        val pass=findViewById<EditText>(R.id.pass)
        var login_btn = findViewById<Button>(R.id.login_btn)
        var signup_btn = findViewById<TextView>(R.id.signup_btn)
        var forgotpass_btn = findViewById<TextView>(R.id.forgot_txt)

        var auth = FirebaseAuth.getInstance()

        login_btn.setOnClickListener{
            auth.signInWithEmailAndPassword(
                email.text.toString(),
                pass.text.toString()
            ).addOnSuccessListener {
                val intent = Intent(this, pg7home::class.java)
                startActivity(intent)
                Toast.makeText(this,"Login Successful", Toast.LENGTH_LONG).show()
                finish()
            }
                .addOnFailureListener {

                    Log.e("Sigin_Error",it.message.toString())
                    Toast.makeText(this,"Failed To login; Try again please", Toast.LENGTH_LONG).show()
                }
        }
        signup_btn.setOnClickListener{
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }
        forgotpass_btn.setOnClickListener{
            val intent = Intent(this, pg5forgotpass::class.java)
            startActivity(intent)
        }
    }
}