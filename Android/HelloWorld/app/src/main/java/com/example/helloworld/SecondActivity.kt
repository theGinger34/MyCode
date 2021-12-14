package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.helloworld.databinding.ActivitySecondBinding
import java.util.*

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    companion object{
        const val TOTAL_COUNT = "total_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showRandomNumber()
    }//onCreate

    fun showRandomNumber() {
        //get count from extras
        val count = intent.getIntExtra(TOTAL_COUNT,0)
        //generate random number
        val random = Random()
        var randomInt = 0
        if (count > 0){
            //add 1 because bound is exclusive
            randomInt = random.nextInt(count+1)
        }
        //display random number
        binding.textViewRandom.text = Integer.toString(randomInt)
        //add to header text
        binding.textViewLabel.text = getString(R.string.random_heading,count)
    }//showRandomNumber

}