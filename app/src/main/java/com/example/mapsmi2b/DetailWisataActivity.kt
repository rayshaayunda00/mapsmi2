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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DetailWisataActivity : AppCompatActivity(), OnMapReadyCallback {
    private  lateinit var btngas : Button
    private lateinit var mMap: GoogleMap

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_wisata)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Ambil data latitude dan longitude
        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude", 0.0)
        val namawisata = intent.getStringExtra("namaWisata")
        val lokasiWisata = LatLng(latitude, longitude)

// Tambahkan marker dan pindahkan kamera ke lokasi wisata
        mMap.addMarker(MarkerOptions().position(lokasiWisata).title(namawisata))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lokasiWisata,15f))
        }
}

