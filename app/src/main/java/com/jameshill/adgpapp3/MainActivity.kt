package com.jameshill.adgpapp3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //Declare lateinit for these variables
    private lateinit var greeting: TextView
    private lateinit var nameField: EditText
    private lateinit var nameButton: Button
    var USERNAME_KEY = "username"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize variables to resources
        greeting = findViewById(R.id.greeting)
        nameField = findViewById(R.id.name_field)
        nameButton = findViewById(R.id.name_button)

        //Create sharedPreferences file.
        val sharedPrefs = this.getSharedPreferences(
            "preferences", 0
        )
        //Create editor
        val editor = sharedPrefs.edit()

        // if the vale of the key-value pair is empty, greeting is invisible
        if (sharedPrefs.getString(USERNAME_KEY, "")?.isEmpty() == true) {
            greeting.visibility = View.GONE
        } else {//otherwise
            //access the value of the string-value pair
            val name: String? = sharedPrefs.getString(USERNAME_KEY, "")
            //use it to display this greeting
            greeting.setText("Let me ask your mom, $name")
        }
        //Set the onClickListener for the button
        nameButton.setOnClickListener {
            //if the button is clicked on an empty textEnter field
            if (nameField.getText().toString().isEmpty()) {
                Toast.makeText(
                    this, "Please enter a name you doofus.",
                    Toast.LENGTH_LONG
                ).show()
            } else {//otherwise
                //declare the name to save variable as the string contents of the enterText field
                var nameToSave: String = nameField.getText().toString()
                //save the name as the value of the string-value pair.
                editor.putString(USERNAME_KEY, nameToSave).apply()
            }
        }


    }
}