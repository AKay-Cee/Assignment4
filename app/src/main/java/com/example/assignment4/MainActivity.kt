package com.example.assignment4

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    private val userList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signInButton = findViewById<Button>(R.id.signInButton)
        val emailET = findViewById<EditText>(R.id.emailET)
        val passwordET=findViewById<EditText>(R.id.passwordET)

        userList.add(User("Anand", "KC", "kcanand@gmail.com", "apple"))
        userList.add(User("Sagar", "Basnet", "sagar@gmail.com", "ball"))
        userList.add(User("Ram", "Pandit", "ram@gmail.com", "cat"))
        userList.add(User("Hari", "Sharma", "hari@gmail.com", "dog"))
        userList.add(User("Sujan", "Shrestha", "sujan@gmail.com", "eye"))

        signInButton.setOnClickListener {
            val email = emailET.editableText.toString()
            val password = passwordET.editableText.toString()

            if (email.trim().isEmpty()) Toast.makeText(
                this,
                "Enter a valid username",
                Toast.LENGTH_SHORT
            ).show()
            else if (password.trim().isEmpty()) Toast.makeText(
                this,
                "Enter a valid password",
                Toast.LENGTH_SHORT
            ).show()
            else {
                val user = User("", "", email, password)
                var found = false
                userList.forEach {
                    if (it == user) {
                        found = true
                        startActivity(Intent(
                            this,
                            Category::class.java
                        ).apply {
                            putExtra("username", email)
                        })
                    }
                }

                if (!found) Toast.makeText(
                    this,
                    "Invalid User. Please Try again",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        val createAccountButton=findViewById<Button>(R.id.createAccountButton)
        createAccountButton.setOnClickListener {
            resultLauncher.launch(Intent(this, RegisterActivity::class.java))
        }

        val forgotPasswordTV=findViewById<TextView>(R.id.forgotPasswordTV)
        forgotPasswordTV.setOnClickListener {
            val email = emailET.editableText.toString().trim()
            if (email.isEmpty()) {
                Toast.makeText(this, "Enter username to change password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var password: String? = ""
            userList.forEach {
                if (it.userName == email) {
                    password = it.password
                    return@forEach
                }
            }

            if (password!!.isEmpty()) {
                Toast.makeText(this, "Username not found!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val mailto = "mailto:$email" +
                    "?cc=" + "" +
                    "&subject=" + Uri.encode("Change your Password") +
                    "&body=" + Uri.encode("Your password is: $password")

            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse(mailto)

            try {
                startActivity(emailIntent)
            } catch (e: ActivityNotFoundException) {

            }
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: User = result.data?.extras?.get("user") as User
                userList.add(data)
            }
        }
}