package com.example.basiclist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val edtNumber: EditText = findViewById(R.id.editNumber)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val btnShow: Button = findViewById(R.id.btnShow)
        val listView: ListView = findViewById(R.id.listView)
        val txtError: TextView = findViewById(R.id.textError)

        btnShow.setOnClickListener {
            val numberText = edtNumber.text.toString()
            if (numberText.isBlank()) {
                txtError.text = "Vui lòng nhập một số"
                txtError.visibility = TextView.VISIBLE
                return@setOnClickListener
            }

            val n = numberText.toDoubleOrNull()
            if (n == null || n <= 0 || n % 1 != 0.0) { // Kiểm tra là số nguyên dương
                txtError.text = "Vui lòng nhập số nguyên dương"
                txtError.visibility = TextView.VISIBLE
                return@setOnClickListener
            }

            txtError.visibility = TextView.GONE
            val intN = n.toInt() // Chuyển đổi về số nguyên

            // Lấy ID của RadioButton đã chọn
            val selectedId = radioGroup.checkedRadioButtonId
            val list = when (selectedId) {
                R.id.radioEven -> getEvenNumbers(intN)
                R.id.radioOdd -> getOddNumbers(intN)
                R.id.radioSquare -> getSquareNumbers(intN)
                else -> emptyList()
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
            listView.adapter = adapter
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        val list = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            list.add(i * i)
            i++
        }
        return list
    }
}