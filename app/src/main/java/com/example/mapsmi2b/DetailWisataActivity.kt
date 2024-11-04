package com.example.mapsmi2b

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailWisataActivity : AppCompatActivity() {
    private  lateinit var btngas : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_wisata)

        val Image = intent.getIntExtra("imagewisata",0)
        val nama = intent.getStringExtra("namaWisata")
        val deskripsi = intent.getStringExtra("deskripsi")
        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude", 0.0)



        val txtnama = findViewById<TextView>(R.id.txtnama)
        val imgwisata = findViewById<ImageView>(R.id.imgwisata)
        val txtdeskripsi = findViewById<TextView>(R.id.txtdeskripsi)

        txtdeskripsi.text = deskripsi
        txtnama.text = nama
        imgwisata.setImageResource(Image)

        btngas = findViewById(R.id.btngas)

        btngas.setOnClickListener(){
            val intent = Intent(this,MapsActivity::class.java).apply{
                putExtra("latitude", latitude)
                putExtra("longitude", longitude)
                putExtra("namaWisata",nama)
            }
            startActivity(intent)

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}