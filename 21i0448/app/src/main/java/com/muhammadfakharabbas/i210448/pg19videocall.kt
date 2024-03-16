package com.muhammadfakharabbas.i210448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView

class pg19videocall : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg19videocall)

        var close_btn = findViewById<ImageView>(R.id.close_btn)

        close_btn.setOnClickListener(){
            val intent = Intent(this, pg7home::class.java)
            startActivity(intent)
        }
    }
}