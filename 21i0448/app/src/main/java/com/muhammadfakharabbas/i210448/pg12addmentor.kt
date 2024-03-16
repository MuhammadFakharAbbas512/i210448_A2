package com.muhammadfakharabbas.i210448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class pg12addmentor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg12addmentor)

        var back_btn = findViewById<Button>(R.id.back_btn)
        var upload_btn = findViewById<Button>(R.id.upload_btn)
        var video_btn = findViewById<Button>(R.id.video_btn)
        var photo_btn = findViewById<Button>(R.id.photo_btn)
        var video_btn1 = findViewById<Button>(R.id.video_btn2)
        var photo_btn1 = findViewById<Button>(R.id.photo_btn2)

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

        photo_btn.setOnClickListener{
            val intent = Intent(this, pg17photo::class.java)
            startActivity(intent)
        }
        photo_btn1.setOnClickListener{
            val intent = Intent(this,  pg17photo::class.java)
            startActivity(intent)
        }
        video_btn.setOnClickListener{
            val intent = Intent(this, pg18video::class.java)
            startActivity(intent)
        }
        video_btn1.setOnClickListener{
            val intent = Intent(this, pg18video::class.java)
            startActivity(intent)
        }
        upload_btn.setOnClickListener{
            val intent = Intent(this, pg7home::class.java)
            Toast.makeText(this,"Uploaded",Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
    }
}