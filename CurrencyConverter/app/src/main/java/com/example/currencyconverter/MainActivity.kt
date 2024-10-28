package com.example.currencyconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyconverter.databinding.ActivityMainBinding
import com.example.currencyconverter.data.ExchangeRates
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currencies = listOf("USD", "VND", "EUR", "JPY", "GBP")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerFrom.adapter = adapter
        binding.spinnerTo.adapter = adapter

        binding.amountInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                convertCurrencyFromInput()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                convertCurrencyFromInput()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        binding.spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                convertCurrencyFromInput()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        binding.updateRatesButton.setOnClickListener {
            updateExchangeRateDisplay()
        }

        updateExchangeRateDisplay()
    }

    private fun convertCurrencyFromInput() {
        val from = binding.spinnerFrom.selectedItem.toString()
        val to = binding.spinnerTo.selectedItem.toString()
        val amountText = binding.amountInput.text.toString()

        if (amountText.isEmpty()) {
            binding.resultText.text = "0.00"
            binding.exchangeRateText.text = ""
            return
        }

        val amount = amountText.toDoubleOrNull() ?: return
        if (amount > 0) {
            convertCurrency(from, to, amount)
        } else {
            binding.resultText.text = "0.00"
            binding.exchangeRateText.text = ""
        }
    }

    private fun convertCurrency(from: String, to: String, amount: Double) {
        val key = "${from}_$to"
        val rate = ExchangeRates.rates[key] ?: 1.00

        val convertedAmount = amount * rate

        binding.resultText.text = String.format("%.2f", convertedAmount)
        binding.exchangeRateText.text = "1 $from = $rate $to"
    }

    private fun updateExchangeRateDisplay() {
        val currentDate = SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.getDefault()).format(Date())
        binding.lastUpdatedText.text = "Updated $currentDate"
    }
}
