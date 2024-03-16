package com.muhammadfakharabbas.i210448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView

class pg21profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg21myprofile)

        var back_btn = findViewById<Button>(R.id.back_btn)

        var home_btn = findViewById<ImageView>(R.id.home_img)
        var search_btn = findViewById<ImageView>(R.id.search_img)
        var add_btn = findViewById<ImageView>(R.id.add_img)
        var chat_btn = findViewById<ImageView>(R.id.chat_img)
        var profile_btn = findViewById<ImageView>(R.id.profile_img)

        var editprofilepic_btn = findViewById<ImageView>(R.id.editprofilepic_btn)
        var editprofile_btn = findViewById<ImageView>(R.id.editprofile_btn)
        var booked_sessions = findViewById<Button>(R.id.booked_sessions)
        var john_btn = findViewById<ImageButton>(R.id.john_btn)

        back_btn.setOnClickListener{
            val intent = Intent(this, pg7home::class.java)
            startActivity(intent)
        }
        editprofilepic_btn.setOnClickListener{
            val intent = Intent(this, pg22edit_profile::class.java)
            startActivity(intent)
        }
        editprofile_btn.setOnClickListener{
            val intent = Intent(this, pg22edit_profile::class.java)
            startActivity(intent)
        }
        booked_sessions.setOnClickListener{
            val intent = Intent(this, pg23bookedsession::class.java)
            startActivity(intent)
        }
        john_btn.setOnClickListener{
            val intent = Intent(this, pg10mentor::class.java)
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
    }
}