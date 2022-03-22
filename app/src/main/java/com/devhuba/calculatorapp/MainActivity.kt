package com.devhuba.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.devhuba.calculatorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var tvInput = binding.tvInput
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //View binding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnZero.setOnClickListener {
            tvInput.append(binding.btnZero.text)
            lastNum()
        }
        binding.btnOne.setOnClickListener {
            tvInput.append(binding.btnOne.text)
            lastNum()
        }
        binding.btnTwo.setOnClickListener {
            tvInput.append(binding.btnTwo.text)
            lastNum()
        }
        binding.btnThree.setOnClickListener {
            tvInput.append(binding.btnThree.text)
            lastNum()
        }
        binding.btnFour.setOnClickListener {
            tvInput.append(binding.btnFour.text)
            lastNum()
        }
        binding.btnFive.setOnClickListener {
            tvInput.append(binding.btnFive.text)
            lastNum()
        }
        binding.btnSix.setOnClickListener {
            tvInput.append(binding.btnSix.text)
            lastNum()
        }
        binding.btnSeven.setOnClickListener {
            tvInput.append(binding.btnSeven.text)
            lastNum()
        }
        binding.btnEight.setOnClickListener {
            tvInput.append(binding.btnEight.text)
            lastNum()
        }
        binding.btnNine.setOnClickListener {
            tvInput.append(binding.btnNine.text)
            lastNum()
        }

        binding.btnClear.setOnClickListener { binding.tvInput.text = "" }
        binding.btnDot.setOnClickListener {
            if (lastNumeric && !lastDot) {
                binding.tvInput.append(".")
                lastNumeric = false
                lastDot = true
            }
        }
        binding.btnAdd.setOnClickListener {
            onOperator(binding.btnAdd)
        }
        binding.btnDivide.setOnClickListener {
            onOperator(binding.btnDivide)
        }
        binding.btnMultiply.setOnClickListener {
            onOperator(binding.btnMultiply)
        }
        binding.btnSubtract.setOnClickListener {
            onOperator(binding.btnSubtract)
        }

    }

    private fun lastNum() {
        lastNumeric = true
        lastDot = false
    }

    private fun onOperator (view: View) {
        if (lastNumeric && !isOperatorAdded(tvInput.text.toString())) {
            if (view is Button) {
                tvInput.append(view.text)
            }
        }
    }

    private fun isOperatorAdded (value:String) : Boolean {
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}






