package com.muhammadfakharabbas.i210448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class pg11review : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg11review)
        var back_btn = findViewById<Button>(R.id.back_btn)
        var feedback_btn = findViewById<Button>(R.id.feedback_btn)

        back_btn.setOnClickListener{
            val intent = Intent(this, pg10mentor::class.java)
            startActivity(intent)
        }
        feedback_btn.setOnClickListener{
            val intent = Intent(this,pg7home::class.java)
            startActivity(intent)
        }

    }
}