package com.muhammadfakharabbas.i210448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class pg13calendar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg13calander)

        var back_btn = findViewById<Button>(R.id.back_btn)
        var msg_btn = findViewById<ImageButton>(R.id.msg_btn)
        var call_btn = findViewById<ImageButton>(R.id.call_btn)
        var videocall_btn = findViewById<ImageButton>(R.id.videocall_btn)
        var bookappointment_btn = findViewById<Button>(R.id.bookappointment_btn)

        back_btn.setOnClickListener{
            val intent = Intent(this, pg13calendar::class.java)
            startActivity(intent)
        }
        msg_btn.setOnClickListener{
            val intent = Intent(this, pg15chat_person::class.java)
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
            val intent = Intent(this, pg7home::class.java)
            Toast.makeText(this,"Appointment Booked", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }

    }
}