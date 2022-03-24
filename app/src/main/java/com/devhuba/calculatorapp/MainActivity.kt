package com.devhuba.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.devhuba.calculatorapp.databinding.ActivityMainBinding
import java.lang.ArithmeticException
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private var isLastANumber: Boolean = false
    private var isLastADot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //View binding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Declare variable for easy use
        val tvInput = binding.tvInput

        //Numbers
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

        //Operators
        binding.btnAdd.setOnClickListener {
            onOperator(binding.btnAdd, tvInput.text.toString(), binding)
        }
        binding.btnDivide.setOnClickListener {
            onOperator(binding.btnDivide, tvInput.text.toString(), binding)
        }
        binding.btnMultiply.setOnClickListener {
            onOperator(binding.btnMultiply, tvInput.text.toString(), binding)
        }
        binding.btnSubtract.setOnClickListener {
            onOperator(binding.btnSubtract, tvInput.text.toString(), binding)
        }

        //Clear
        binding.btnClear.setOnClickListener { binding.tvInput.text = "" }

        //Dot
        binding.btnDot.setOnClickListener {
            if (isLastANumber && !isLastADot) {
                binding.tvInput.append(".")
                isLastANumber = false
                isLastADot = true
            }
        }

        //Equals
        binding.btnEqual.setOnClickListener {
            if (isLastANumber) {
                var tvValue = tvInput.text.toString()
                var prefix = ""

                try {
                    //Fix bug with two minuses
                    if (tvValue.startsWith("-")) {
                        prefix = "-"
                        tvValue = tvValue.substring(1)
                    }

                    //Calculation logic
                    if (tvValue.contains("-")) {
                        val calcMethod = "-"
                        tvInput.text = calculate(prefix, calcMethod,tvValue)

                    } else if (tvValue.contains("+")) {
                        val calcMethod = "+"
                        tvInput.text = calculate(prefix,calcMethod,tvValue)

                    } else if (tvValue.contains("/")) {
                        val calcMethod = "/"
                        tvInput.text = calculate(prefix,calcMethod,tvValue)

                    } else if (tvValue.contains("*")) {
                        val calcMethod = "*"
                        tvInput.text = calculate(prefix,calcMethod,tvValue)
                    }

                } catch (e: ArithmeticException) {
                    e.printStackTrace()
                }
            }
        }


    }

    //Check is last number or dot
    private fun lastNum() {
        isLastANumber = true
        isLastADot = false
    }

    //Remove dot and zero if we use Int
    private fun removeZeroAfterDot(value: String): String {
        var result = ""
        result = if (value.contains(".0")) value.substring(0, value.length - 2) else {
            //Fix double values in 4 digits after dot
            val round = (value.toDouble() * 10000.0).roundToInt() / 10000.0
            round.toString()
        }
        return result
    }

    //Add operator
    private fun onOperator(view: View, boundText: String, binding: ActivityMainBinding) {
        if (isLastANumber && !isOperatorAdded(boundText) && view is Button) {
            binding.tvInput.append(view.text)
            isLastANumber = false
            isLastADot = false
        }
    }

    //Check is operator added or not
    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }

    //Calculation
    private fun calculate(
        prefix: String,
        calcMethod: String,
        tvValue: String
    ): String {
        val splitValue = tvValue.split(calcMethod)
        var firstNumber = splitValue[0]
        val secondNumber = splitValue[1]
        var formattedResult = ""

//        Check for -number inserted
        if (prefix.isNotEmpty()) {
            firstNumber = prefix + firstNumber
        }

        //Check for calculation method and calculate
        if (calcMethod == "-") {
            formattedResult = removeZeroAfterDot(
                ((firstNumber.toDouble() - secondNumber.toDouble())
                    .toString())
            )
        } else if (calcMethod == "+") {
            formattedResult = removeZeroAfterDot(
                ((firstNumber.toDouble() + secondNumber.toDouble())
                    .toString())
            )
        } else if (calcMethod == "/") {
            formattedResult = removeZeroAfterDot(
                ((firstNumber.toDouble() / secondNumber.toDouble())
                    .toString())
            )
        } else if (calcMethod == "*") {
            formattedResult = removeZeroAfterDot(
                ((firstNumber.toDouble() * secondNumber.toDouble())
                    .toString())
            )
        }
        //Assign output
        return formattedResult
    }
}








