package com.example.baka.riwayat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baka.R
import com.example.baka.loginregister.loginActivity
import com.example.baka.utils.SessionManager

class detail_transaksi : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_transaksi)

        val sessionManager = SessionManager(this)

        // Cek apakah pengguna sudah login
        if (!sessionManager.isLoggedIn()) {
            // Jika belum login, pindahkan ke halaman login
            startActivity(Intent(this, loginActivity::class.java))
            finish()
        }

        // Jika login, ambil detail pengguna
        val userDetails = sessionManager.getUserDetails()
        val userName = userDetails["user_name"]
        val email = userDetails["email"]

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
