package com.example.firsthomework

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var workingsTextView: TextView
    private lateinit var resultTextView: TextView

    private var currentInput = ""
    private var operator = ""
    private var firstOperand = 0.0
    private var secondOperand = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workingsTextView = findViewById(R.id.workingsTV)
        resultTextView = findViewById(R.id.resultsTV)
    }

    fun numberAction(view: View) {
        val button = view as Button
        appendInput(button.text.toString())
    }

    fun operationAction(view: View) {
        val button = view as Button
        setOperator(button.text.toString())
    }

    fun equalsAction(view: View) {
        calculateResult()
    }

    fun clearAction(view: View) {
        clear()
    }

    private fun appendInput(value: String) {
        currentInput += value
        workingsTextView.text = currentInput
    }

    private fun setOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            firstOperand = currentInput.toDouble()
            operator = op
            currentInput = ""
            workingsTextView.text = "$firstOperand $operator"
        }
    }

    private fun calculateResult() {
        if (currentInput.isNotEmpty() && operator.isNotEmpty()) {
            secondOperand = currentInput.toDouble()
            val result = when (operator) {
                "+" -> firstOperand + secondOperand
                "-" -> firstOperand - secondOperand
                "*" -> firstOperand * secondOperand
                "/", "÷" -> if (secondOperand != 0.0) firstOperand / secondOperand else Double.NaN
                "%" -> firstOperand % secondOperand
                else -> Double.NaN
            }

            currentInput = if (result.isNaN()) "Error" else result.toString()
            resultTextView.text = currentInput
            workingsTextView.text = ""
            operator = ""
        }
    }

    private fun clear() {
        currentInput = ""
        operator = ""
        firstOperand = 0.0
        secondOperand = 0.0
        workingsTextView.text = ""
        resultTextView.text = "0"
    }
}