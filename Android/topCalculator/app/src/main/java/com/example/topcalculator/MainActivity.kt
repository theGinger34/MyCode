package com.example.topcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import com.example.topcalculator.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    //access view elements
    private lateinit var binding: ActivityMainBinding

    //variables used for saving/restoring state
    private var currentBillTotal: Double = 0.0
    private var currentCustomPercent: Int = 0

    companion object{
        //constants when saving/restoring state
        private val BILL_TOTAL = "BILL_TOTAL"
        private val CUSTOM_PERCENT = "CUSTOM_PERCENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //check to see if app started or restored from memory
        if(savedInstanceState == null){
            //app just starting
            currentBillTotal = 0.0
            currentCustomPercent = 18
        } else {
            //app being restored
            currentBillTotal = savedInstanceState.getDouble(BILL_TOTAL)
            currentCustomPercent = savedInstanceState.getInt(CUSTOM_PERCENT)
        }

        //handle changes in text
        binding.billTotalEditText.addTextChangedListener(billEditTextWatcher)

        //handle changes in seekbar
        binding.customSeekBar.setOnSeekBarChangeListener(customSeekBarListener)


    }//onCreate

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //save our values
        outState.putDouble(BILL_TOTAL,currentBillTotal)
        outState.putInt(CUSTOM_PERCENT,currentCustomPercent)
    }

    //update 10,15,20 % tips
    private fun updateStandard() {

        //10% calc & update GUI
        val tenPercentTip = currentBillTotal *.1
        val tenPercentTotal = currentBillTotal + tenPercentTip
        binding.tipTenEditText.setText(String.format("%.02f",tenPercentTip))
        binding.totalTenEditText.setText(String.format("%.02f",tenPercentTotal))

        //15% calc & update GUI
        val fifteenPercentTip = currentBillTotal *.15
        val fifteenPercentTotal = currentBillTotal + fifteenPercentTip
        binding.tipFifteenEditText.setText(String.format("%.02f",fifteenPercentTip))
        binding.totalFifteenEditText.setText(String.format("%.02f",fifteenPercentTotal))

        //20% calc & update GUI
        val twentyPercentTip = currentBillTotal *.2
        val twentyPercentTotal = currentBillTotal + twentyPercentTip
        binding.tipTwentyEditText.setText(String.format("%.02f",twentyPercentTip))
        binding.totalTwentyEditText.setText(String.format("%.02f",twentyPercentTotal))

    }//updateStandard

    //update custom tip
    private fun updateCustom(){

        //match the seekbar value
        binding.customTextView.setText(currentCustomPercent.toString()+"%")

        //calculate tip and total abd update GUI
        val customTipAmt = currentBillTotal * currentCustomPercent.toDouble() * .01
        val customTotal = currentBillTotal + customTipAmt
        binding.tipEditText.setText(String.format("%.02f",customTipAmt))
        binding.totalEditText.setText(String.format("%.02f",customTotal))

    }//updateCustom

    private val customSeekBarListener = object: SeekBar.OnSeekBarChangeListener{
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

            currentCustomPercent = seekBar.progress //0-100
            updateCustom()
        }

        override fun onStartTrackingTouch(p0: SeekBar?) {
            //ignore
        }

        override fun onStopTrackingTouch(p0: SeekBar?) {
            //ignore
        }

    }//customSeekBarListener

    private val billEditTextWatcher = object: TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //ignore
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            try {
                currentBillTotal = s.toString().toDouble()
            } catch (e: NumberFormatException) {
                currentBillTotal = 0.0
            }

            updateStandard()
            updateCustom()
        }

        override fun afterTextChanged(p0: Editable?) {
            //ignore
        }

    }


}//Activity