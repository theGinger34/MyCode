package com.example.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.helloworld.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }//onCreate

    fun toastMe(view: View){
        val myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT)
        myToast.show()
    }//toastMe

    fun countMe(view: View){
        //get text view
        //val showCountText = findViewById<TextView>(R.id.textView)
        //get val of text view
        //val countString = showCountText.text.toString()
        val countString = binding.textView.text.toString()
        //convert to number
        var count: Int = Integer.parseInt(countString)
        count++
        //display in text view
        //showCountText.text = count.toString()
        binding.textView.text = count.toString()
    }//countMe

    fun randomMe(view: View){
        //create intent to start secondActivity
        val randomIntent = Intent(this,SecondActivity::class.java)
        //get current val of textview
        val countString = binding.textView.text.toString()
        //convert to int
        val count = Integer.parseInt(countString)
        //add count to extras for intent
        randomIntent.putExtra(SecondActivity.TOTAL_COUNT, count)
        //start new activity
        startActivity(randomIntent)
    }//randomMe
}//Activity