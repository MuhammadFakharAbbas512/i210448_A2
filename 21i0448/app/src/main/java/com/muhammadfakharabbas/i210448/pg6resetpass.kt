package com.muhammadfakharabbas.i210448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class pg6resetpass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg6resetpass)
        var login_btn = findViewById<TextView>(R.id.login_btn)
        var reset_btn = findViewById<Button>(R.id.resetpass_btn)
        var back_btn = findViewById<Button>(R.id.back_btn)


        login_btn.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        reset_btn.setOnClickListener{
            val intent = Intent(this, pg5forgotpass::class.java)
            startActivity(intent)
        }
        back_btn.setOnClickListener{
            val intent = Intent(this, pg5forgotpass::class.java)
            startActivity(intent)
        }
    }
}