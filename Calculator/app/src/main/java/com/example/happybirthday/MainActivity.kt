package com.example.happybirthday

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var textResult: TextView
    var state: Int = 1
    var op: Int = 0
    var op1: Int = 0
    var op2: Int = 0
    var isDotUsed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.textViewDisplay)

        // Set OnClickListener cho các nút
        val buttons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
            R.id.buttonAdd, R.id.buttonMinus, R.id.buttonMultiple, R.id.buttonDivide,
            R.id.buttonEqual, R.id.buttonC, R.id.buttonCE, R.id.buttonBS,
            R.id.buttonAddMinus, R.id.buttonDot
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener(this)
        }
    }

    override fun onClick(p0: View?) {
        val id = p0?.id
        when (id) {
            R.id.button0 -> addDigit(0)
            R.id.button1 -> addDigit(1)
            R.id.button2 -> addDigit(2)
            R.id.button3 -> addDigit(3)
            R.id.button4 -> addDigit(4)
            R.id.button5 -> addDigit(5)
            R.id.button6 -> addDigit(6)
            R.id.button7 -> addDigit(7)
            R.id.button8 -> addDigit(8)
            R.id.button9 -> addDigit(9)

            R.id.buttonAdd -> setOperation(1)
            R.id.buttonMinus -> setOperation(2)
            R.id.buttonMultiple -> setOperation(3)
            R.id.buttonDivide -> setOperation(4)

            R.id.buttonEqual -> calculateResult()

            R.id.buttonC -> clearC()
            R.id.buttonCE -> clearCE()
            R.id.buttonBS -> clearBS()

            R.id.buttonDot -> addDot()
            R.id.buttonAddMinus -> toggleSign()
        }
    }

    private fun addDigit(digit: Int) {
        if (state == 1) {
            op1 = op1 * 10 + digit
            textResult.text = "$op1"
        } else {
            op2 = op2 * 10 + digit
            textResult.text = "$op2"
        }
    }

    private fun setOperation(operation: Int) {
        op = operation
        state = 2 // Chuyển sang nhập số thứ 2
        isDotUsed = false // Reset lại dấu chấm
    }

    private fun calculateResult() {
        var result = 0
        when (op) {
            1 -> result = op1 + op2
            2 -> result = op1 - op2
            3 -> result = op1 * op2 // Nhân
            4 -> if (op2 != 0) result = op1 / op2 // Chia
            else textResult.text = "Error" // Chia cho 0
        }

        textResult.text = "$result"
        state = 1
        op1 = result
        op2 = 0
        op = 0
    }

    private fun clearC() {
        op1 = 0
        op2 = 0
        op = 0
        state = 1
        isDotUsed = false
        textResult.text = "0"
    }

    private fun clearCE() {
        if (state == 1) {
            op1 = 0
            textResult.text = "0"
        } else {
            op2 = 0
            textResult.text = "0"
        }
    }

    private fun clearBS() {
        if (state == 1) {
            op1 = op1 / 10
            textResult.text = "$op1"
        } else {
            op2 = op2 / 10
            textResult.text = "$op2"
        }
    }

    private fun addDot() {
        if (!isDotUsed) {
            textResult.text = if (state == 1) "$op1." else "$op2."
            isDotUsed = true
        }
    }

    private fun toggleSign() {
        if (state == 1) {
            op1 = -op1
            textResult.text = "$op1"
        } else {
            op2 = -op2
            textResult.text = "$op2"
        }
    }
}
