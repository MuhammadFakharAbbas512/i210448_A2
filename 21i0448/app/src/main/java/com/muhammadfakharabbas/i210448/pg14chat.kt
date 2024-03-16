package com.muhammadfakharabbas.i210448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class pg14chat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg14chat)

        var john_community_btn = findViewById<ImageView>(R.id.john_community_btn)
        var john_chat_btn = findViewById<ImageView>(R.id.john_chat_btn)
        var john_chat_txt = findViewById<TextView>(R.id.john_chat_txt)

        var back_btn = findViewById<Button>(R.id.back_btn)
        var home_btn = findViewById<ImageView>(R.id.home_img)
        var search_btn = findViewById<ImageView>(R.id.search_img)
        var add_btn = findViewById<ImageView>(R.id.add_img)
        var chat_btn = findViewById<ImageView>(R.id.chat_img)
        var profile_btn = findViewById<ImageView>(R.id.profile_img)


        back_btn.setOnClickListener{
            val intent = Intent(this, pg7home::class.java)
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
        john_community_btn.setOnClickListener{
            val intent = Intent(this, pg16chat_community::class.java)
            startActivity(intent)
        }
        john_chat_btn.setOnClickListener{
            val intent = Intent(this, pg15chat_person::class.java)
            startActivity(intent)
        }
        john_chat_txt.setOnClickListener{
            val intent = Intent(this, pg15chat_person::class.java)
            startActivity(intent)
        }
    }
}