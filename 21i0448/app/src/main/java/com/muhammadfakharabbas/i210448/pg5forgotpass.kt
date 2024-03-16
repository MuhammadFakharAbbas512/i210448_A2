package com.muhammadfakharabbas.i210448

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class pg5forgotpass : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg5forgotpass)
        var login_btn = findViewById<TextView>(R.id.login_btn)
        var send_btn = findViewById<Button>(R.id.send_btn)
        var back_btn = findViewById<Button>(R.id.back_btn)
        val mAuth = FirebaseAuth.getInstance()
        var email = findViewById<EditText>(R.id.email)
       // val e = findViewById<EditText>(R.id.email).text.toString()
        val database = FirebaseDatabase.getInstance().reference

//  "users" node in database where emails are stored
        val usersRef = database.child("users")

        send_btn.setOnClickListener{
            if (email.text.isNullOrBlank()){
                Toast.makeText(this, "Email field cannot be empty.", Toast.LENGTH_SHORT).show()
            }
            else{
                // to check the database if the email exists
                usersRef.orderByChild("email").equalTo(email.text.toString()).addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // Email exists in the database
                            Toast.makeText(this@pg5forgotpass, "Email exists. Reset your password.", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@pg5forgotpass, pg6resetpass::class.java)
                            intent.putExtra("email", email.text.toString())
                            //Toast.makeText(this@pg5forgotpass, "Email reset password", Toast.LENGTH_SHORT).show()
                            startActivity(intent)
                        } else {
                            // Email does not exist in the database
                            Toast.makeText(this@pg5forgotpass, "Email  does not exist", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Handle potential errors here
                    }
                })

            }
        }

        login_btn.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        back_btn.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
  /*  fun reset( context: Context){
        mAuth.sendPasswordResetEmail(email.toString())
            .addOnSuccessListener {
                var msg = "Reset password sent to your email: "+email.toString()
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(this,"Failed to Reset password", Toast.LENGTH_LONG).show()
                finish()
            }
    }



    */
}