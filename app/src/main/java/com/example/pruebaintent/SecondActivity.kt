package com.example.pruebaintent

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent

        val suma : Int

        val num1 = intent.getIntExtra("num1", 0)
        val num2 = intent.getIntExtra("num2", 0)
        val num3 = intent.getIntExtra("num3", 0)

        val numeros = intent.getIntArrayExtra("numeros")
        var sumaNum = 0
        if (numeros != null) {
            for (i in 0..numeros.size-1) {
                sumaNum += numeros[i]
            }
            suma = sumaNum
        } else {
            suma = num1 + num2 + num3
        }

        intent.putExtra("suma", suma)

        setResult(Activity.RESULT_OK, intent)

        finish()

    }
}