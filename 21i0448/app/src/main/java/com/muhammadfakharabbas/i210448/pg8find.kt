package com.muhammadfakharabbas.i210448

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class pg8find : AppCompatActivity() {

    private lateinit var mentorAdapter: MentorAdapter
    private lateinit var mentorRecyclerView: RecyclerView
    private lateinit var mentorList: ArrayList<MentorModel>
    private lateinit var currentUser: FirebaseUser
    private lateinit var databaseReference: DatabaseReference
    private lateinit var msg: String
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg8find)


//        var auth = FirebaseAuth.getInstance()

        mentorRecyclerView = findViewById(R.id.search_rv)

        mentorList = ArrayList()
        mentorAdapter = MentorAdapter(this, mentorList)
        mentorRecyclerView.adapter = mentorAdapter
        mentorRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        currentUser = FirebaseAuth.getInstance().currentUser!!
        databaseReference = FirebaseDatabase.getInstance().getReference("mentors")
        getMentorsList()

        var home_btn = findViewById<ImageView>(R.id.home_img)
        var search_btn = findViewById<ImageView>(R.id.search_img)
        var add_btn = findViewById<ImageView>(R.id.add_img)
        var chat_btn = findViewById<ImageView>(R.id.chat_img)
        var profile_btn = findViewById<ImageView>(R.id.profile_img)

        var search = findViewById<ImageView>(R.id.search)
        var rv = findViewById<RecyclerView>(R.id.search_rv)
        var searchInput = findViewById<EditText>(R.id.search_edtxt)
        searchInput.requestFocus()
        var back_btn = findViewById<Button>(R.id.back_btn)

         // var mentor1 = findViewById<TextView>(R.id.mentor1)
        //var mentor2 = findViewById<TextView>(R.id.mentor2)
       // var mentor3 = findViewById<TextView>(R.id.mentor3)
        var img1 = findViewById<ImageView>(R.id.img1)
        var img2 = findViewById<ImageView>(R.id.img2)
        var img3 = findViewById<ImageView>(R.id.img3)
        var img11 = findViewById<ImageView>(R.id.img11)
        var img22 = findViewById<ImageView>(R.id.img22)
        var img33 = findViewById<ImageView>(R.id.img33)

        search.setOnClickListener{

            var str = searchInput.text.toString()
            if (str.isEmpty() || str.length <3){
                Toast.makeText(this, "Field cannot be empty/Enter more terms", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            search(str)
        }

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


        /*
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
        }*/
    }

    fun search(str:String){
       // var q = FirebaseUtil.allUserDatabaseReference().whereGreaterThanOrEqualTo("username", str)
        databaseReference = FirebaseDatabase.getInstance().getReference("mentors")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@pg8find, "Database error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                mentorList.clear()

                for (dataSnapshot in snapshot.children) {
                    val mentor = dataSnapshot.getValue(MentorModel::class.java)
                    mentor?.let {
                      //  mentorList.add(mentor)
 //   Toast.makeText(this@pg8find, msg, Toast.LENGTH_LONG).show()
                        if (str == mentor.name){
                          //  Toast.makeText(this@pg8find, msg, Toast.LENGTH_LONG).show()
                            msg = "Mentor: ${mentor.name} exists"
                            Toast.makeText(this@pg8find, msg, Toast.LENGTH_LONG).show()
                            val intent = Intent(this@pg8find, pg9searchresults::class.java)
                            intent.putExtra("mid", mentor.mid)
                            startActivity(intent)
                            finish()
                            return
                        }
                        else{
                            msg = "Mentor: ${str} doesn't exists"
                            Toast.makeText(this@pg8find, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                mentorAdapter.notifyDataSetChanged() // Notify the adapter of changes
            }
        })

    }
    private fun getMentorsList() {
        databaseReference = FirebaseDatabase.getInstance().getReference("mentors")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@pg8find, "Database error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                mentorList.clear()
                for (dataSnapshot in snapshot.children) {
                    val mentor = dataSnapshot.getValue(MentorModel::class.java)
                    mentor?.let {
                        mentorList.add(mentor)
                        val msg = "MID: ${mentor.mid}, Name: ${mentor.name}"
                        //   Toast.makeText(this@pg7home, msg, Toast.LENGTH_LONG).show()
                    }
                }
                mentorAdapter.notifyDataSetChanged() // Notify the adapter of changes
            }
        })
    }
}