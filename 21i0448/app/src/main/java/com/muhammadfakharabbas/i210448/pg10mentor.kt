package com.muhammadfakharabbas.i210448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class pg10mentor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg10mentorpg)
        var back_btn = findViewById<Button>(R.id.back_btn)
        var review_btn = findViewById<Button>(R.id.review_btn)
        var join_btn = findViewById<Button>(R.id.community_btn)
        var booksession_btn = findViewById<Button>(R.id.booksession_btn)

       /* back_btn.setOnClickListener{
            val intent = Intent(this, pg9searchresults::class.java)
            startActivity(intent)
        }*/
        back_btn.setOnClickListener{
            val intent = Intent(this, pg7home::class.java)
            startActivity(intent)
        }
        review_btn.setOnClickListener{
            val intent = Intent(this, pg11review::class.java)
            startActivity(intent)
        }
        join_btn.setOnClickListener{
            val intent = Intent(this, pg16chat_community::class.java) //pg16
            startActivity(intent)
        }
        booksession_btn.setOnClickListener{
            val intent = Intent(this, pg13calendar::class.java)
            startActivity(intent)
        }
    }
}