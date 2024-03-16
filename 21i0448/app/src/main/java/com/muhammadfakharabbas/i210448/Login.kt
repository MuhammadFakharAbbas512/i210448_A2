package com.muhammadfakharabbas.i210448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        var login_btn = findViewById<Button>(R.id.login_btn)
        var signup_btn = findViewById<TextView>(R.id.signup_btn)
        var forgotpass_btn = findViewById<TextView>(R.id.forgot_txt)

        login_btn.setOnClickListener{
            val intent = Intent(this, pg7home::class.java)
            startActivity(intent)
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