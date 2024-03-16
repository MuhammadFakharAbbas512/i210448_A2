package com.muhammadfakharabbas.i210448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class pg17photo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg17photo)

        var close_btn = findViewById<ImageButton>(R.id.close_btn)
        var video_txt = findViewById<TextView>(R.id.video_txt)
        var video_btn = findViewById<ImageView>(R.id.video_btn)


        close_btn.setOnClickListener(){
            val intent = Intent(this, pg7home::class.java)
            startActivity(intent)
        }
        video_txt.setOnClickListener(){
            val intent = Intent(this, pg18video::class.java)
            startActivity(intent)
        }
        video_btn.setOnClickListener(){
            val intent = Intent(this, pg18video::class.java)
            startActivity(intent)
        }

    }
}