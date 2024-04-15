package com.duytran.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.duytran.calculator.databinding.ActivityMainBinding
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding?= null
    private val binding: ActivityMainBinding by lazy {
        requireNotNull(_binding)
    }

    private val phepTinh = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnNb0.setOnClickListener{
                phepTinh.append("0")
                updatePhepTinh()
            }
            btnNb1.setOnClickListener{
                phepTinh.append("1")
                updatePhepTinh()

            }
            btnNb2.setOnClickListener{
                phepTinh.append("2")
                updatePhepTinh()

            }
            btnNb3.setOnClickListener{
                phepTinh.append("3")
                updatePhepTinh()

            }
            btnNb4.setOnClickListener{
                phepTinh.append("4")
                updatePhepTinh()

            }
            btnNb5.setOnClickListener{
                phepTinh.append("5")
                updatePhepTinh()

            }
            btnNb6.setOnClickListener{
                phepTinh.append("6")
                updatePhepTinh()

            }
            btnNb7.setOnClickListener{
                phepTinh.append("7")
                updatePhepTinh()

            }
            btnNb8.setOnClickListener{
                phepTinh.append("8")
                updatePhepTinh()

            }
            btnNb9.setOnClickListener{
                phepTinh.append("9")
                updatePhepTinh()

            }
            btnCong.setOnClickListener{
                phepTinh.append(" + ")
                updatePhepTinh()

            }
            btnTru.setOnClickListener{
                phepTinh.append(" - ")
                updatePhepTinh()

            }
            btnNhan.setOnClickListener{
                phepTinh.append(" x ")
                updatePhepTinh()

            }
            btnChia.setOnClickListener{
                phepTinh.append(" : ")
                updatePhepTinh()
            }
            btnAC.setOnClickListener{
                phepTinh.clear()
                updatePhepTinh()
                updateKetQua()
            }
            btnPhanTram.setOnClickListener{
                phepTinh.append(" % ")
                updatePhepTinh()

            }
            btnCham.setOnClickListener{
                phepTinh.append(" . ")
                updatePhepTinh()
            }
            btnDaubang.setOnClickListener {
                //tinhToanPhanTram()
                val phepTinh = phepTinh.toString()
                val toanTu = listOf("+", "-", "x", ":", ".")

                val numbers = mutableListOf<Double>()
                val phepToan = mutableListOf<String>()

                val doUuTien = mapOf("+" to 1, "-" to 1, "x" to 2, ":" to 2)

                val bieuThuc = mutableListOf<String>()

                var currentNumber = ""

                for (char in phepTinh) {
                    val strChar = char.toString()

                    if (strChar in toanTu) {
                        if (currentNumber.isNotEmpty()) {
                            bieuThuc.add(currentNumber.trim())
                            currentNumber = ""
                        }

                        while (phepToan.isNotEmpty() && phepToan.last() != "(" &&
                            doUuTien[strChar]!! <= doUuTien[phepToan.last()]!!
                        ) {
                            bieuThuc.add(phepToan.removeAt(phepToan.lastIndex))
                        }

                        phepToan.add(strChar)
                    } else if (strChar == "(") {
                        if (currentNumber.isNotEmpty()) {
                            // Handle cases like "3(2+1)"
                            bieuThuc.add(currentNumber.trim())
                            bieuThuc.add("*")
                            currentNumber = ""
                        }
                        phepToan.add(strChar)
                    } else if (strChar == ")") {
                        if (currentNumber.isNotEmpty()) {
                            bieuThuc.add(currentNumber.trim())
                            currentNumber = ""
                        }

                        while (phepToan.isNotEmpty() && phepToan.last() != "(") {
                            bieuThuc.add(phepToan.removeAt(phepToan.lastIndex))
                        }

                        if (phepToan.isNotEmpty() && phepToan.last() == "(") {
                            phepToan.removeAt(phepToan.lastIndex)
                        }
                    } else {
                        currentNumber += strChar
                    }
                }

                if (currentNumber.isNotEmpty()) {
                    bieuThuc.add(currentNumber.trim())
                }

                while (phepToan.isNotEmpty()) {
                    bieuThuc.add(phepToan.removeAt(phepToan.lastIndex))
                }

                for (token in bieuThuc) {
                    if (token in toanTu) {
                        val number2 = numbers.removeAt(numbers.lastIndex)
                        val number1 = numbers.removeAt(numbers.lastIndex)
                        val result = calculate(number1, number2, token)
                        numbers.add(result)
                    } else {
                        numbers.add(token.toDouble())
                    }
                }

                val result = numbers.first()
                binding.tvKetQua.text = result.toString()
            }

        }
    }

    private fun uuTien(op1: String, op2: String): Boolean {
        if ((op1 == "x" || op1 == ":") && (op2 == "+" || op2 == "-")) {
            return true
        }
        return false
    }
    private fun calculate(number1: Double, number2: Double, operator: String): Double {
        when (operator) {
            "+" -> return number1 + number2
            "-" -> return number1 - number2
            "x" -> return number1 * number2
            ":" -> return number1 / number2
        }
        return 0.0
    }
    private fun updatePhepTinh(){
        binding.tvPhepTinh.text = phepTinh.toString()
    }

    private fun updateKetQua(){
        binding.tvKetQua.text = phepTinh.toString()
    }

    fun tinhToanPhanTram() {
        val bieuThuc = phepTinh.toString()
        val parts = bieuThuc.split(" ")
        if (parts.size == 3) {
            val so1 = parts[0].toDouble()
            val phepToan = parts[1]
            val so2 = parts[2].toDouble()

            val ketQua: Double = when (phepToan) {
                "+" -> so1 + (so1 * so2 / 100) // Tính phần trăm của số1 và cộng vào số1
                "-" -> so1 - (so1 * so2 / 100) // Tính phần trăm của số1 và trừ từ số1
                else -> throw IllegalArgumentException("Phép toán không hợp lệ")
            }

            updateKetQua()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}