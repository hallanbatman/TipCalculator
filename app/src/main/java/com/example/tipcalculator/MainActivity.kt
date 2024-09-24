package com.example.tipcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculator.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Increase or decrease tip percentage
        binding.btnTipPlus.setOnClickListener {
            val currentTip = binding.tieTip.text.toString().toIntOrNull() ?: 0
            binding.tieTip.setText((currentTip + 1).toString())
        }

        binding.btnTipMinus.setOnClickListener {
            val currentTip = binding.tieTip.text.toString().toIntOrNull() ?: 0
            if (currentTip > 0) {
                binding.tieTip.setText((currentTip - 1).toString())
            }
        }

        // Increase or decrease number of people
        binding.btnPeoplePlus.setOnClickListener {
            val currentPeople = binding.tieNumberPeople.text.toString().toIntOrNull() ?: 1
            binding.tieNumberPeople.setText((currentPeople + 1).toString())
        }

        binding.btnPeopleMinus.setOnClickListener {
            val currentPeople = binding.tieNumberPeople.text.toString().toIntOrNull() ?: 1
            if (currentPeople > 1) {
                binding.tieNumberPeople.setText((currentPeople - 1).toString())
            }
        }

        // Camel case using Binding
        binding.btnCalculate.setOnClickListener {
            val totalBillTemp = binding.tieBill.text
            val tipTemp = binding.tieTip.text
            val peopleTemp = binding.tieNumberPeople.text

            if (totalBillTemp.isNullOrEmpty() || tipTemp.isNullOrEmpty() || peopleTemp.isNullOrEmpty()) {

                Snackbar.make(binding.root, "Empty fields", Snackbar.LENGTH_SHORT).show()

            } else {

                val totalAmount: Float = binding.tieBill.text.toString().toFloat()
                val tip: Int = binding.tieTip.text.toString().toInt()
                val people: Float = binding.tieNumberPeople.text.toString().toFloat()

                val totalTemp = totalAmount / people
                val totalTips = totalTemp * tip / 100
                val totalWithTip = totalTemp + totalTips

                println("Clicou: Bill $" + binding.tieBill.text)
                println("Clicou: Tip %" + binding.tieTip.text)
                println("Clicou: People #" + binding.tieNumberPeople.text)
                println("Clicou: Total $" + totalTemp)
                println("Clicou: Tip $" + totalTips)
                println("Clicou: Total with tip $" + totalWithTip)

                binding.tvTotalResult.text = totalWithTip.toString()
                binding.tvTipResult.text = totalTips.toString()
            }

            binding.btnClear.setOnClickListener {

                binding.tieBill.setText("")
                binding.tieTip.setText("")
                binding.tieNumberPeople.setText("")
                binding.tvTotalResult.text = ""
                binding.tvTipResult.text = ""

                println("Clicou: Clear")
            }

        }
    }
}