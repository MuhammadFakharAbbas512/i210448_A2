package com.muhammadfakharabbas.i210448

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        val email=findViewById<EditText>(R.id.email)
        val pass=findViewById<EditText>(R.id.pass)
        var login_btn = findViewById<Button>(R.id.login_btn)
        var signup_btn = findViewById<TextView>(R.id.signup_btn)
        var forgotpass_btn = findViewById<TextView>(R.id.forgot_txt)

        var auth = FirebaseAuth.getInstance()

        login_btn.setOnClickListener{

            if( email.text.isNullOrBlank() && pass.text.isNullOrBlank()){
                Toast.makeText(this,"Field cannot be empty; Try again please", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(
                email.text.toString(),
                pass.text.toString()
            ).addOnSuccessListener {authResult->

                val currentUser = auth.currentUser
                val userId = currentUser?.uid
                val database = FirebaseDatabase.getInstance().reference

                val userRef = database.child("users").child(userId.toString())
              //  val userRef = FirebaseDatabase.getInstance().getReference("users").child(userId)
                userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // Retrieve user data from the database
                            val userName = dataSnapshot.child("name").getValue(String::class.java)

                            // Pass user name to the Home page
                            val intent = Intent(this@Login, pg7home::class.java)
                            intent.putExtra("userName", userName)
                            startActivity(intent)
                            Toast.makeText(this@Login, "Login Successful", Toast.LENGTH_LONG).show()
                            finish()
                        } else {
                            Toast.makeText(this@Login, "User data not found", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.e("LoginActivity", "Failed to read user data: ${databaseError.message}")
                        Toast.makeText(this@Login, "Failed to read user data", Toast.LENGTH_SHORT).show()
                    }
                })
            }
                .addOnFailureListener {

                    Log.e("Login_Error",it.message.toString())
                    Toast.makeText(this,"Failed To login; Try again please", Toast.LENGTH_LONG).show()
                }
        }
        signup_btn.setOnClickListener{
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }
        forgotpass_btn.setOnClickListener{
            val intent = Intent(this, pg5forgotpass::class.java)
            startActivity(intent)
        }
    }
}