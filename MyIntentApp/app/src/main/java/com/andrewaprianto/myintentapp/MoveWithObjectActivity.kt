package com.andrewaprianto.myintentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MoveWithObjectActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_PERSON = "extra_person"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_object)

        val tvObject: TextView = findViewById(R.id.tv_object_received)

        var text = ""

        val persons = intent.getParcelableArrayListExtra<Person>(EXTRA_PERSON) as ArrayList<Person>
        for(person in persons){
             text += "Name: ${person.name.toString()}, \nEmail: ${person.email.toString()}, \nLocation: ${person.city.toString()} \n\n\n"
        }

        tvObject.text = text
    }
}