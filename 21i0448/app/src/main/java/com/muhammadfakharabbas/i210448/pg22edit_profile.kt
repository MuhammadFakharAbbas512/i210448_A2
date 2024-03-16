package com.muhammadfakharabbas.i210448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class pg22edit_profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg22editprofile)

        var back_btn = findViewById<Button>(R.id.back_btn)
        var upload_btn = findViewById<Button>(R.id.uploadprofile_btn)

        back_btn.setOnClickListener{
            val intent = Intent(this, pg21profile::class.java)
            startActivity(intent)
        }
        upload_btn.setOnClickListener{
            val intent = Intent(this, pg21profile::class.java)
            Toast.makeText(this,"Uploaded", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }

    }
}