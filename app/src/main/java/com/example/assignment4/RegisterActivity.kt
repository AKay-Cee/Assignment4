package com.example.assignment4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resister)

        val fNameET=findViewById<EditText>(R.id.fNameET)
        val createAccountButton=findViewById<Button>(R.id.createAccountButton)
        val lNameET=findViewById<EditText>(R.id.lNameET)
        val emailET=findViewById<EditText>(R.id.emailET)
        val passwordET=findViewById<EditText>(R.id.passwordET)
        createAccountButton.setOnClickListener {
            val fName = fNameET.editableText.toString().trim()
            val lName = lNameET.editableText.toString().trim()
            val userName = emailET.editableText.toString().trim()
            val password = passwordET.editableText.toString().trim()

            if (fName.isEmpty() || lName.isEmpty() || userName.isEmpty() || password.isEmpty())
                Toast.makeText(this, "Fill All required Fields", Toast.LENGTH_SHORT).show()

            val returnData = Intent()
            returnData.putExtra("user", User(fName, lName, userName, password))
            setResult(RESULT_OK, returnData)
            finish()
        }
    }
}