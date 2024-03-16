package com.muhammadfakharabbas.i210448

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider


class pg4Verify : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg4verifynum)

        var num = findViewById<TextView>(R.id.num)
        var optET1 = findViewById<EditText>(R.id.optET1)
        var optET2 = findViewById<EditText>(R.id.optET2)
        var optET3 = findViewById<EditText>(R.id.optET3)
        var optET4 = findViewById<EditText>(R.id.optET4)
        var optET5 = findViewById<EditText>(R.id.optET5)
        var optET6 = findViewById<EditText>(R.id.optET6)

        val otpp = optET1.text.toString() +
                optET2.text.toString() +
                optET3.text.toString() +
                optET4.text.toString() +
                optET5.text.toString() +
                optET6.text.toString()

        var otp= getIntent().getStringExtra("token")
        num.setText(getIntent().getStringExtra("number").toString())

        //
        var verify_btn = findViewById<Button>(R.id.verify_btn)
        var back_btn = findViewById<Button>(R.id.back_btn)

        verify_btn.setOnClickListener {
            if (!optET1.getText().toString().trim().isEmpty() && !optET2.getText().toString().trim()
                    .isEmpty() && !optET3.getText().toString().trim().isEmpty() && !optET4.getText()
                    .toString().trim().isEmpty() && !optET5.getText().toString().trim()
                    .isEmpty() && !optET6.getText().toString().trim().isEmpty()
            ) {
                var credential= PhoneAuthProvider.getCredential(otp!!,otpp)
                var auth= FirebaseAuth.getInstance()
                Toast.makeText(this,"Error typing...", Toast.LENGTH_LONG).show()

                auth.signInWithCredential(credential)
                    .addOnSuccessListener {
                        var i =Intent(this,Login::class.java)
                        i.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
                        Toast.makeText(this,"Successful verification", Toast.LENGTH_LONG).show()
                        startActivity(i)
                    }
                    .addOnFailureListener {
                        Toast.makeText(this,"verification failed", Toast.LENGTH_LONG).show()
                        finish()
                    }

            } else {
                Toast.makeText(this,"Input incomplete for verification", Toast.LENGTH_LONG).show()

            }
           // val intent = Intent(this, Login::class.java)
            //startActivity(intent)

        }
        back_btn.setOnClickListener{
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }

       //showOtp(optET1)

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                // Implement afterTextChanged
            }
        }
        //moveNumber()
        optET1.requestFocus()
        optET1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Implement beforeTextChanged
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank()) {
                    optET2.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Implement afterTextChanged
            }
        })
        optET2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Implement beforeTextChanged
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank()) {
                    optET3.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Implement afterTextChanged
            }
        })
        optET3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Implement beforeTextChanged
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank()) {
                    optET4.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Implement afterTextChanged
            }
        })
        optET4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Implement beforeTextChanged
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank()) {
                    optET5.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Implement afterTextChanged
            }
        })
        optET5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Implement beforeTextChanged
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank()) {
                    optET6.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Implement afterTextChanged
            }
        })

    }

    }

  /*  private fun showOtp(optET: EditText) {
        optET.requestFocus()

        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(optET, InputMethodManager.SHOW_IMPLICIT)
    }*/


