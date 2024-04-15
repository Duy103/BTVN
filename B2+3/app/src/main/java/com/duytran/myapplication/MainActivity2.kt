package com.duytran.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    var tvv2: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        tvv2 = findViewById(R.id.tv2)

        val i1 = intent
        val a = i1.extras!!.getString("a")!!.toInt()
        val b = i1.extras!!.getString("a")!!.toInt()
        val c = i1.extras!!.getString("a")!!.toInt()
        val delta = (b*b - 4*a*c).toDouble()
        if(delta < 0){
            tvv2!!.text = "Chương trình vô nghiệm"
        }else if(delta == 0.0){
            tvv2!!.text = "PT có nghiệm kép x = " + (-b)/(2*a)
        }else{
            val x1 = (-b + Math.sqrt(delta))/(2*a)
            val x2 = (-b - Math.sqrt(delta))/(2*a)
            tvv2!!.text = "PT có 2 nghiệm phân biệt x1 = $x1, và  $x2"
        }
    }
}