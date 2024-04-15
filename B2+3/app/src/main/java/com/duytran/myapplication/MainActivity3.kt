package com.duytran.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.File

class MainActivity3 : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var editTextDOB: EditText
    private lateinit var editTextGender: EditText
    private lateinit var textViewStudentList: TextView

    private val studentList = ArrayList<String>()
    private val fileName = "student_list.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        editTextName = findViewById(R.id.editTextName)
        editTextAge = findViewById(R.id.editTextAge)
        editTextDOB = findViewById(R.id.editTextNS)
        editTextGender = findViewById(R.id.editTextGT)
        textViewStudentList = findViewById(R.id.tvStudentList)

        val buttonCreate: Button = findViewById(R.id.buttonCreate)
        buttonCreate.setOnClickListener {
            val name = editTextName.text.toString()
            val age = editTextAge.text.toString()
            val dob = editTextDOB.text.toString()
            val gender = editTextGender.text.toString()

            val studentInfo = "Tên: $name, Tuổi: $age, Ngày sinh: $dob, Giới tính: $gender"
            studentList.add(0, studentInfo)
            saveStudentListToFile()

            clearInputFields()
        }
        val buttonLoad: Button = findViewById(R.id.buttonLoad)
        buttonLoad.setOnClickListener {
            loadStudentListFromFile()
            val studentInfoText = studentList.joinToString("\n")
            textViewStudentList.text = studentInfoText
        }
    }
    private fun saveStudentListToFile() {
        val file = File(filesDir, fileName)
        file.writeText(studentList.joinToString("\n"))
    }

    private fun loadStudentListFromFile() {
        val file = File(filesDir, fileName)
        if (file.exists()) {
            val fileContent = file.readText()
            studentList.clear()
            studentList.addAll(fileContent.split("\n"))
        }
    }

    private fun clearInputFields() {
        editTextName.text.clear()
        editTextAge.text.clear()
        editTextDOB.text.clear()
        editTextGender.text.clear()
    }
}