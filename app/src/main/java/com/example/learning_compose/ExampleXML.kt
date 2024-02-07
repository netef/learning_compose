package com.example.learning_compose

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ExampleXML : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_xml)
        val editText = findViewById<EditText>(R.id.edit_text_id)
        val btn = findViewById<Button>(R.id.btn)
        val title = findViewById<TextView>(R.id.title)
    }
}