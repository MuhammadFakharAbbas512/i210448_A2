package com.muhammadfakharabbas.i210448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        var signup_btn = findViewById<Button>(R.id.signup_btn)
        var login_btn  = findViewById<TextView>(R.id.login_btn)

        login_btn.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        signup_btn.setOnClickListener{
            val intent = Intent(this, pg4Verify::class.java)
            startActivity(intent)
        }
    }
}