package com.muhammadfakharabbas.i210448

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class pg14chat : AppCompatActivity() {
    private lateinit var userAdapter: UserAdapter
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userList: ArrayList<User>
    private lateinit var currentUser: FirebaseUser
    private lateinit var databaseReference: DatabaseReference
    private lateinit var imgProfile: ImageView
    private lateinit var username: TextView
    private var firebaseUser: FirebaseUser? = null
    private lateinit var userId: String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg14chat)

        userRecyclerView = findViewById(R.id.userRecyclerView)

        userList = ArrayList()
        userAdapter = UserAdapter(this, userList)
        userRecyclerView.adapter = userAdapter
        userRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        currentUser = FirebaseAuth.getInstance().currentUser!!
        databaseReference = FirebaseDatabase.getInstance().getReference("users")

        getUsersList()

        username =  findViewById<TextView>(R.id.john_chat_txt)
        var john_community_btn = findViewById<ImageView>(R.id.john_community_btn)
        var john_chat_btn = findViewById<ImageView>(R.id.john_chat_btn)
        var john_chat_txt = findViewById<TextView>(R.id.john_chat_txt)

        var back_btn = findViewById<Button>(R.id.back_btn)
        var home_btn = findViewById<ImageView>(R.id.home_img)
        var search_btn = findViewById<ImageView>(R.id.search_img)
        var add_btn = findViewById<ImageView>(R.id.add_img)
        var chat_btn = findViewById<ImageView>(R.id.chat_img)
        var profile_btn = findViewById<ImageView>(R.id.profile_img)
        imgProfile = findViewById(R.id.used)

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

    private fun getUsersList() {
        val firebase: FirebaseUser = FirebaseAuth.getInstance().currentUser!!

        val userid = firebase.uid
      //  FirebaseMessaging.getInstance().subscribeToTopic("/topics/$userid")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                //
                Toast.makeText(this@pg14chat, "database error: error.message", Toast.LENGTH_SHORT).show()
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                if (snapshot.exists()) {
                    val userName = snapshot.child("name").value.toString()
                    userId = snapshot.child("userId").value.toString()

                    // Set user name in the UI
                    username.text = userName

                }
                val currentUser = snapshot.getValue(User::class.java)
                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val user = dataSnapShot.getValue(User::class.java)
                    user?.let {
                        userList.add(user)
                        //  Toast.makeText(this@pg14chat, "User added", Toast.LENGTH_SHORT).show()
                    }
                    //   if (user != null && !user.userId.equals(currentUser.userId)) {
                    //  userList.add(user)
                    //   Toast.makeText(this@pg14chat, "User added", Toast.LENGTH_SHORT).show()}

                    Toast.makeText(this@pg14chat,"users list added message", Toast.LENGTH_SHORT).show()
                }
                if (currentUser!!.profileImage == ""){
                    Toast.makeText(this@pg14chat, "Set profile message", Toast.LENGTH_SHORT).show()
                   // imgProfile.setImageResource(R.drawable.profile_image)

                    }else{
                    Glide.with(this@pg14chat).load(currentUser.profileImage).into(imgProfile)

                }

               // userAdapter.notifyDataSetChanged()
                userAdapter = UserAdapter(this@pg14chat, userList)
                userRecyclerView.adapter = userAdapter
            }
        })
    }
}
