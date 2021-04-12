package com.andrewaprianto.mytestingapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnSetValue: Button
    private lateinit var tvText: TextView

    private var names = ArrayList<String>()

    private lateinit var imgPreview: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvText = findViewById(R.id.tv_test)
        //Matikan ini untuk menguji NPE
        btnSetValue = findViewById(R.id.btn_set_value)
        //Menguji nullPointerException
        //Button belum dinisialisasi sehingga menyebabkan NPE
        btnSetValue.setOnClickListener(this)

        names.add("Narenda Wicaksono")
        names.add("Kevin")
        names.add("Yoza")

        imgPreview = findViewById(R.id.img_preview)
        //Menguji OutOfMemoryException
        //Ukuran Gambar yang dimuat melebihi memori yang tersedia untuk menjalankan aplikasi.
        //Aktifkan ini untuk menguji OutOfMemoryException
//        imgPreview.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fronalpstock_big))
        //Matikan ini untuk menugji OutOfMemoryException
        Glide.with(this).load(R.drawable.fronalpstock_big).into(imgPreview)


    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_set_value) {
            Log.d("MainActivity", names.toString())
            val name = StringBuilder()
            //Menguji IndexOutOfBoundException
            //Collection tidak sesuai untuk pengaksesan
            //for(i in 0..3) { //gunakan ini untuk menguji IndexOutOfBoundException
            for (i in 0..2) {
                name.append(names[i]).append("\n")
            }
            tvText.text = name.toString()
        }
    }
}