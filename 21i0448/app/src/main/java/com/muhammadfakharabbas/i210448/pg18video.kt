package com.muhammadfakharabbas.i210448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class pg18video : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg18video)

        var cam_txt = findViewById<TextView>(R.id.photo_txt)
        var cam_btn = findViewById<ImageView>(R.id.cam_btn)
        var close_btn = findViewById<ImageButton>(R.id.close_btn)

        close_btn.setOnClickListener(){
            val intent = Intent(this, pg7home::class.java)
            startActivity(intent)
        }
        cam_txt.setOnClickListener(){
            val intent = Intent(this, pg17photo::class.java)
            startActivity(intent)
        }
        cam_btn.setOnClickListener(){
            val intent = Intent(this, pg17photo::class.java)
            startActivity(intent)
        }
    }
}