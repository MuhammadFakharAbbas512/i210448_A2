package com.muhammadfakharabbas.i210448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class pg8find : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg8find)
        var home_btn = findViewById<ImageView>(R.id.home_img)
        var search_btn = findViewById<ImageView>(R.id.search_img)
        var add_btn = findViewById<ImageView>(R.id.add_img)
        var chat_btn = findViewById<ImageView>(R.id.chat_img)
        var profile_btn = findViewById<ImageView>(R.id.profile_img)

        var back_btn = findViewById<Button>(R.id.back_btn)

        var mentor1 = findViewById<TextView>(R.id.mentor1)
        var mentor2 = findViewById<TextView>(R.id.mentor2)
        var mentor3 = findViewById<TextView>(R.id.mentor3)
        var img1 = findViewById<ImageView>(R.id.img1)
        var img2 = findViewById<ImageView>(R.id.img2)
        var img3 = findViewById<ImageView>(R.id.img3)
        var img11 = findViewById<ImageView>(R.id.img11)
        var img22 = findViewById<ImageView>(R.id.img22)
        var img33 = findViewById<ImageView>(R.id.img33)

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

        mentor1.setOnClickListener{
            val intent = Intent(this, pg9searchresults::class.java)
            startActivity(intent)
        }
        mentor2.setOnClickListener{
            val intent = Intent(this, pg9searchresults::class.java)
            startActivity(intent)
        }
        mentor3.setOnClickListener{
            val intent = Intent(this, pg9searchresults::class.java)
            startActivity(intent)
        }
        img1.setOnClickListener{
            val intent = Intent(this, pg9searchresults::class.java)
            startActivity(intent)
        }
        img11.setOnClickListener{
            val intent = Intent(this, pg9searchresults::class.java)
            startActivity(intent)
        }
        img2.setOnClickListener{
            val intent = Intent(this, pg9searchresults::class.java)
            startActivity(intent)
        }
        img22.setOnClickListener{
            val intent = Intent(this, pg9searchresults::class.java)
            startActivity(intent)
        }
        img3.setOnClickListener{
            val intent = Intent(this, pg9searchresults::class.java)
            startActivity(intent)
        }
        img33.setOnClickListener{
            val intent = Intent(this, pg9searchresults::class.java)
            startActivity(intent)
        }
    }
}