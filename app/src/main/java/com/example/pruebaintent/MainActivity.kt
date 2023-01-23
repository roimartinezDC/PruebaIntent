package com.example.pruebaintent

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val RES_UNO = 1
    val RES_DOS = 2
    val RES_TRES = 3
    val REQ_IMG_CAP = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener() {
            val intent = Intent(this, SecondActivity::class.java)

            intent.putExtra("num1", 2)
            intent.putExtra("num2", 3)
            startActivityForResult(intent, RES_UNO)

            intent.putExtra("num1", 4)
            intent.putExtra("num2", 1)
            intent.putExtra("num3", 2)
            startActivityForResult(intent, RES_DOS)

            intent.putExtra("numeros", intArrayOf(1, 2, 3))
            startActivityForResult(intent, RES_TRES)
        }

        //genera un intent que te abre la cámara del teléfono
        val btnFoto = findViewById<Button>(R.id.btn_foto)
        btnFoto.setOnClickListener() {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, REQ_IMG_CAP)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val txt1 = findViewById<TextView>(R.id.textView1)
        val txt2 = findViewById<TextView>(R.id.textView2)
        val txt3 = findViewById<TextView>(R.id.textView3)
        val imageView = findViewById<ImageView>(R.id.imageView)

        // comprueba que el reqCode sea el establecido al intent de la cámara, y recoge la foto en un image view
        if (requestCode == REQ_IMG_CAP && data != null) {
            val imageBitMap = data.extras!!.get("data") as Bitmap
            imageView.setImageBitmap(imageBitMap)
        }

        if (resultCode != Activity.RESULT_OK || data == null) return

        when (requestCode) {
            RES_UNO ->
                txt1.text = data.getIntExtra("suma", 0).toString()
            RES_DOS ->
                txt2.text = data.getIntExtra("suma", 0).toString()
            RES_TRES ->
                txt3.text = data.getIntExtra("suma", 0).toString()
        }
    }
}