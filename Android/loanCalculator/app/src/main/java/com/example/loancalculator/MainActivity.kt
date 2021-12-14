package com.example.loancalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import com.example.loancalculator.databinding.ActivityMainBinding
import java.lang.NumberFormatException
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    //access view elements
    private lateinit var binding: ActivityMainBinding

    //variables used for saving/restoring state
    private var currentLoanAmount: Double = 0.0
    private var currentInterestRate: Int = 0

    companion object{
        //constants when saving/restoring state
        private val LOAN_AMOUNT = "LOAN_AMOUNT"
        private val INTEREST_RATE = "INTEREST_RATE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //check to see if app started or restored from memory
        if(savedInstanceState == null){
            //app just starting
            currentLoanAmount = 0.00
            currentInterestRate = 15
        } else {
            //app being restored
            currentLoanAmount = savedInstanceState.getDouble(LOAN_AMOUNT)
            currentInterestRate = savedInstanceState.getInt(INTEREST_RATE)
        }

        //handle changes in text
        binding.loanAmount.addTextChangedListener(loanAmountWatcher)

        //handle changes in seekbar
        binding.interestSeekBar.setOnSeekBarChangeListener(interestSeekBarListener)
    }//onCreate

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //save our values
        outState.putDouble(LOAN_AMOUNT,currentLoanAmount)
        outState.putInt(INTEREST_RATE,currentInterestRate)
    }

    //update loan amount
    private fun updateAmount() {

        val r = ((currentInterestRate.toDouble())/1200)
        val p = currentLoanAmount
        var n: Int

        //5 year emi and total
        n = 5*12
        val emi5 = ((p*r*((1+r).pow(n)))/(((1+r).pow(n))-1))
        val at5 = emi5 * n
        binding.emi5.setText(String.format("%.02f",emi5))
        binding.totalAmount5.setText(String.format("%.02f",at5))

        //10 year emi and total
        n = 10*12
        val emi10 = ((p*r*((1+r).pow(n)))/(((1+r).pow(n))-1))
        val at10 = emi10 * n
        binding.emi10.setText(String.format("%.02f",emi10))
        binding.totalAmount10.setText(String.format("%.02f",at10))

        //15 year emi and total
        n = 15*12
        val emi15 = ((p*r*((1+r).pow(n)))/(((1+r).pow(n))-1))
        val at15 = emi15 * n
        binding.emi15.setText(String.format("%.02f",emi15))
        binding.totalAmount15.setText(String.format("%.02f",at15))

        //20 year emi and total
        n = 20*12
        val emi20 = ((p*r*((1+r).pow(n)))/(((1+r).pow(n))-1))
        val at20 = emi20 * n
        binding.emi20.setText(String.format("%.02f",emi20))
        binding.totalAmount20.setText(String.format("%.02f",at20))

        //25 year emi and total
        n = 25*12
        val emi25 = ((p*r*((1+r).pow(n)))/(((1+r).pow(n))-1))
        val at25 = emi25 * n
        binding.emi25.setText(String.format("%.02f",emi25))
        binding.totalAmount25.setText(String.format("%.02f",at25))

        //30 year emi and total
        n = 30*12
        val emi30 = ((p*r*((1+r).pow(n)))/(((1+r).pow(n))-1))
        val at30 = emi30 * n
        binding.emi30.setText(String.format("%.02f",emi30))
        binding.totalAmount30.setText(String.format("%.02f",at30))

    }//updateAmount


    private val interestSeekBarListener = object: SeekBar.OnSeekBarChangeListener{
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

            currentInterestRate = seekBar.progress //0-100
            updateAmount()
            binding.interestTextView.setText(currentInterestRate.toString()+"%")
        }

        override fun onStartTrackingTouch(p0: SeekBar?) {
            //ignore
        }

        override fun onStopTrackingTouch(p0: SeekBar?) {
            //ignore
        }

    }//customSeekBarListener

    private val loanAmountWatcher = object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //ignore
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            try {
                currentLoanAmount = s.toString().toDouble()
            } catch (e: NumberFormatException) {
                currentLoanAmount = 0.0
            }

            updateAmount()
        }

        override fun afterTextChanged(p0: Editable?) {
            //ignore
        }

    }
}//Activity