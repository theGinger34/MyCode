package com.example.finalexamschweickhardt

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.finalexamschweickhardt.databinding.ActivityMainBinding
import com.example.finalexamschweickhardt.databinding.FragmentTempBinding
import java.lang.NumberFormatException


class TempFragment : Fragment() {

    private lateinit var binding: FragmentTempBinding
    private var currentTempature: Double = 0.0

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTempBinding.inflate(layoutInflater)
        binding.fahrenheit.setOnClickListener { toFahrenheit() }
        binding.celsius.setOnClickListener { toCelsius() }

        binding.tempNumber.addTextChangedListener(tempWatcher)
        return inflater.inflate(R.layout.fragment_temp, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    fun toCelsius (){
        if (isNumber()) {
            var temp: Double? = (5/9)*(currentTempature - 32)
            binding.isConverted.setText("${currentTempature} Converted to Celsius is : \n ${temp}")
        } else {
            val text = "Hello toast!"
            val duration = Toast.LENGTH_SHORT

            Toast.makeText(getActivity(), "Please enter a number to convert!",
                Toast.LENGTH_SHORT).show();
        }

    }//toCelsius


    fun toFahrenheit (){
        if (isNumber()) {
            var temp: Double? = (((9/5)*currentTempature)-32)

            binding.isConverted.setText("${currentTempature} Converted to Celsius is : \n ${temp}")
        } else {
            val text = "Hello toast!"
            val duration = Toast.LENGTH_SHORT

            Toast.makeText(getActivity(), "Please enter a number to convert!",
                Toast.LENGTH_SHORT).show();
        }

    }//toFahrenheit

    fun isNumber(): Boolean {
        return binding.tempNumber != null

    }//isNumber

    private val tempWatcher = object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //ignore
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            try {
                currentTempature = s.toString().toDouble()
            } catch (e: NumberFormatException) {
                currentTempature = 0.0
            }

        }

        override fun afterTextChanged(p0: Editable?) {
            //ignore
        }

    }
}