package com.example.waitersfriend

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import com.example.waitersfriend.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var definedPercentageTextView: TextView
    private lateinit var purchaseValueTextView: TextView
    private lateinit var tipValueTextView : TextView
    private lateinit var finalValueTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initializeTextView()
        assignPercentage()
    }

    private fun initializeTextView() = with(binding) {
        definedPercentageTextView = definedPercentage
        purchaseValueTextView = purchaseValue
        tipValueTextView = tipValue
        finalValueTextView = finalValue
    }

    private fun round(x: Double): Int {
        return (x * 100.0).roundToInt() / 100
    }

    fun calculateTipValue(valuePercentage: Int): Int {
        val valuePurchase: Double = purchaseValueTextView.text.toString().toDouble()
        return round(valuePurchase * valuePercentage * 0.01)
    }
    fun calculateFinalValue(valuePercentage: Int): Int {
        val valuePurchase: Double = purchaseValueTextView.text.toString().toDouble()
        return round(valuePurchase + valuePurchase * valuePercentage * 0.01)
    }

    private fun assignPercentage() {
        val tipPercentage = binding.tipPercentage
        tipPercentage.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, percentage: Int, p2: Boolean) {
                definedPercentageTextView.text = "$percentage%"
                if (purchaseValueTextView.text.toString().isNotEmpty()) {
                    tipValueTextView.text = calculateTipValue(percentage).toString()
                    finalValueTextView.text = calculateFinalValue(percentage).toString()
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
    }

}


