package com.muhammadfakharabbas.i210448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class pg15chat_person : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg15chat_john_person)

        var back_btn = findViewById<Button>(R.id.back_btn)
        var cam_btn = findViewById<ImageView>(R.id.cam_btn)
        var call_btn = findViewById<ImageView>(R.id.call_btn)
        var videocall_btn = findViewById<ImageView>(R.id.videocall_btn)

        var home_btn = findViewById<ImageView>(R.id.home_img)
        var search_btn = findViewById<ImageView>(R.id.search_img)
        var add_btn = findViewById<ImageView>(R.id.add_img)
        var chat_btn = findViewById<ImageView>(R.id.chat_img)
        var profile_btn = findViewById<ImageView>(R.id.profile_img)


        back_btn.setOnClickListener{
            val intent = Intent(this, pg14chat::class.java)
            startActivity(intent)
        }
        home_btn.setOnClickListener{
            val intent = Intent(this, pg7home::class.java)
            startActivity(intent)
        }
        search_btn.setOnClickListener{
            val intent = Intent(this, pg8find::class.java)
            startActivity(intent)
        }
        add_btn.setOnClickListener{
            val intent = Intent(this, pg12addmentor::class.java)
            startActivity(intent)
        }
        chat_btn.setOnClickListener{
            val intent = Intent(this, pg14chat::class.java)
            startActivity(intent)
        }
        profile_btn.setOnClickListener{
            val intent = Intent(this, pg21profile::class.java)
            startActivity(intent)
        }

        videocall_btn.setOnClickListener{
            val intent = Intent(this, pg19videocall::class.java)
            startActivity(intent)
        }
        call_btn.setOnClickListener{
            val intent = Intent(this, pg20call::class.java)
            startActivity(intent)
        }
        cam_btn.setOnClickListener{
            val intent = Intent(this, pg17photo::class.java)
            startActivity(intent)
        }
    }
}