package com.duytran.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    var tvA : EditText ? = null
    var tvB : EditText ? = null
    var tvC : EditText? = null
    var btn_button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tvA = findViewById(R.id.tvA)
        tvB = findViewById(R.id.tvB)
        tvC = findViewById(R.id.tvC)
        btn_button = findViewById(R.id.btn_button)

        btn_button!!.setOnClickListener(View.OnClickListener {
            var i = Intent(this@MainActivity,
                MainActivity2::class.java)
            i.putExtra("a", tvA!!.text.toString())
            i.putExtra("b", tvB!!.text.toString())
            i.putExtra("c", tvC!!.text.toString())
            startActivity(i)
        })
    }
}