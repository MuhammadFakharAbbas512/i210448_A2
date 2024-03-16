package com.muhammadfakharabbas.i210448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class pg5forgotpass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg5forgotpass)
        var login_btn = findViewById<TextView>(R.id.login_btn)
        var send_btn = findViewById<Button>(R.id.send_btn)
        var back_btn = findViewById<Button>(R.id.back_btn)

        login_btn.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        send_btn.setOnClickListener{
            val intent = Intent(this, pg6resetpass::class.java)
            startActivity(intent)
        }
        back_btn.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}