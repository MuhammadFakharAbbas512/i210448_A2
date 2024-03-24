package com.muhammadfakharabbas.i210448

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.util.*

class pg22edit_profile : AppCompatActivity() {

    private var dpUri: Uri? = null

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            dpUri = uri
            findViewById<ImageView>(R.id.img).setImageURI(uri)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pg22editprofile)

        val dp = findViewById<ImageView>(R.id.img)
        val name = findViewById<EditText>(R.id.name)
        val email = findViewById<EditText>(R.id.email)
        val num = findViewById<EditText>(R.id.contact)
        val country = findViewById<EditText>(R.id.country)
        val city = findViewById<EditText>(R.id.city)
        var back_btn = findViewById<Button>(R.id.back_btn)
        var upload_btn = findViewById<Button>(R.id.uploadprofile_btn)

        dp.setOnClickListener {
            pickImage.launch("image/*")
        }

        back_btn.setOnClickListener {
            val intent = Intent(this, pg21profile::class.java)
            startActivity(intent)
        }

        var auth = FirebaseAuth.getInstance()
        // retrieving current user data from database
        val currentUser = auth.currentUser
        val userId = currentUser?.uid
        val userRef = FirebaseDatabase.getInstance().getReference("users").child(userId.toString())

        upload_btn.setOnClickListener {
            if (name != null && email != null && num != null && country != null && city != null) {

            userRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val username = snapshot.child("name").getValue(String::class.java)
                    val profileImage = snapshot.child("profileImage").getValue(String::class.java)
                    Picasso.get().load(profileImage).into(dp)

                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle onCancelled
                }
            })

            // Modify user Data
            if (userId != null && dpUri != null && name != null && email != null && num != null && country != null && city != null) {
                    // Upload the image to Firebase Storage
                val storageRef = FirebaseStorage.getInstance().reference
                val imageRef = storageRef.child("images/${UUID.randomUUID()}")

                imageRef.putFile(dpUri!!)
                    .addOnSuccessListener { taskSnapshot ->
                        // Retrieve the download URL of the uploaded image
                        imageRef.downloadUrl.addOnSuccessListener { uri ->
                            // Create a reference to the user's node in the database
                            val userRef = FirebaseDatabase.getInstance().getReference("users").child(userId)
                            // Create a HashMap to store user data
                            val userData = HashMap<String, Any>()
                            userData["userId"] = userId
                            userData["name"] = name.text.toString()
                            userData["email"] = email.text.toString()
                            userData["phone"] = num.text.toString()
                            userData["country"] = country.text.toString()
                            userData["city"] = city.text.toString()
                            userData["profileImage"] = uri.toString() // Save image URL to the database

                            // Save user data to the database and User data class
                            userRef.setValue(userData)
                            val user = User()
                            user.setDataFromMap(userData)

                            // Show a success message
                            Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show()

                            // Navigate back to the profile page
                            val intent = Intent(this, pg21profile::class.java)
                            startActivity(intent)
                        }
                    }
                    .addOnFailureListener { e ->
                        // Handle any errors that occur during the image upload process
                        Toast.makeText(this, "Image upload failed: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                // Display an error message if the user or image URI is null
                Toast.makeText(this, "User not logged in or image not selected", Toast.LENGTH_SHORT).show()
            }
        }
            else{  // Handle the case where any EditText view is null
                Toast.makeText(this, "entry fields are empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }
}
