package com.muhammadfakharabbas.i210448

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import java.util.concurrent.TimeUnit

class Signup : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        val name=findViewById<EditText>(R.id.name)
        val email=findViewById<EditText>(R.id.email)
        val pass=findViewById<EditText>(R.id.pass)
        val num=findViewById<EditText>(R.id.num)
        val country=findViewById<EditText>(R.id.country)
        val city=findViewById<EditText>(R.id.city)

        var auth = FirebaseAuth.getInstance()
        var signup_btn = findViewById<Button>(R.id.signup_btn)
        var login_btn  = findViewById<TextView>(R.id.login_btn)

        var callbacks=object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                startActivity(Intent(this@Signup,Login::class.java))
            }
            override fun onVerificationFailed(p0: com.google.firebase.FirebaseException) {
                Toast.makeText(this@Signup,"Failed To Verify",Toast.LENGTH_LONG).show()
            }
            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                var i=Intent(this@Signup,pg4Verify::class.java)
                i.putExtra("token",p0)
                i.putExtra("number",num.text.toString())
                startActivity(i)
            }
        }


        login_btn.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        signup_btn.setOnClickListener {
            if (email.text.isNullOrBlank() && (pass.text.isNullOrBlank()) && !(num.text.isNullOrBlank())) {
                //  psignup()
                var options = PhoneAuthOptions.newBuilder()
                    .setPhoneNumber(num.text.toString())
                    .setTimeout(60L, TimeUnit.SECONDS)
                    .setActivity(this)
                    .setCallbacks(callbacks)
                    .build()
                PhoneAuthProvider.verifyPhoneNumber(options)
                Toast.makeText(baseContext, "Please provide OTP code", Toast.LENGTH_LONG).show()
            }

            if (!email.text.isNullOrBlank() && !(pass.text.isNullOrBlank())) {
                // esignup()
                val userEmail = email.text.toString()
                val userPass = pass.text.toString()

                auth.createUserWithEmailAndPassword(userEmail, userPass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val currentUser = auth.currentUser
                            val userId = currentUser?.uid

                            // to store the user's additional data (name, email, phone number, country, city) in Firebase Realtime Database
                            val userRef = FirebaseDatabase.getInstance().getReference("users")
                                .child(userId.toString())
                            val userData = HashMap<String, String>()
                            userData["name"] = name.text.toString()
                            userData["email"] = userEmail
                            userData["phone"] = num.text.toString()
                            userData["country"] = country.text.toString()
                            userData["city"] = city.text.toString()
                            userRef.setValue(userData)

                            // Sign in success, navigate to phone number verification screen (pg4Verify)
                            val intent = Intent(this, Login::class.java)
                            intent.putExtra("number", num.text.toString())
                            startActivity(intent)
                            Toast.makeText(this, "Signup Successful.", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this, "Failed to sign up.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            if (email.text.isNullOrBlank() && (pass.text.isNullOrBlank()) && (num.text.isNullOrBlank())) {
            }
        }
        fun esignup(){

        }
        fun psignup() {

        }
        if(auth.currentUser!=null){
            startActivity(Intent(this,pg7home::class.java))
            finish()
        }

    }
}