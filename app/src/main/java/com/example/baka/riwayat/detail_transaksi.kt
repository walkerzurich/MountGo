package com.example.baka.riwayat

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baka.R

class detail_transaksi : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_transaksi)

        // Bind views
        val btnCancel = findViewById<Button>(R.id.btnCancel)
        val tvNoPesanan = findViewById<TextView>(R.id.tvNoPesananValue)
        val tvNama = findViewById<TextView>(R.id.tvNamaValue)
        val tvGunung = findViewById<TextView>(R.id.tvGunungValue)
        val tvJalur = findViewById<TextView>(R.id.tvJalurValue)
        val tvWaktu = findViewById<TextView>(R.id.tvWaktuValue)
        val tvPembayaran = findViewById<TextView>(R.id.tvPembayaranValue)

        // Dummy data
        tvNoPesanan.text = "18273821789379231"
        tvNama.text = "Franz Simon"
        tvGunung.text = "Merbabu"
        tvJalur.text = "Selo"
        tvWaktu.text = "Rabu, 27 November 2024"
        tvPembayaran.text = "QRIS"

        // Button click listener
        btnCancel.setOnClickListener {
            Toast.makeText(this, "Pesanan dibatalkan", Toast.LENGTH_SHORT).show()
        }
    }
}
