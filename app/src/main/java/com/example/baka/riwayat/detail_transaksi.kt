package com.example.baka.riwayat

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baka.R
import com.google.firebase.database.FirebaseDatabase

class detail_transaksi : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_transaksi)

        // Ambil data dari Intent
        val userId = intent.getStringExtra("userId") // Ambil userId dari Intent
        val transactionId = intent.getStringExtra("transactionId")
        val name = intent.getStringExtra("name")
        val gunung = intent.getStringExtra("gunung")
        val via = intent.getStringExtra("via")
        val date = intent.getStringExtra("date")
        val paymentMethod = intent.getStringExtra("paymentMethod")

        // Inisialisasi UI
        val tvNoPesananValue: TextView = findViewById(R.id.tvNoPesananValue)
        val tvNamaValue: TextView = findViewById(R.id.tvNamaValue)
        val tvGunungValue: TextView = findViewById(R.id.tvGunungValue)
        val tvJalurValue: TextView = findViewById(R.id.tvJalurValue)
        val tvWaktuValue: TextView = findViewById(R.id.tvWaktuValue)
        val tvPembayaranValue: TextView = findViewById(R.id.tvPembayaranValue)
        val btnCancel: Button = findViewById(R.id.btnCancel)

        // Set data ke UI
        tvNoPesananValue.text = transactionId
        tvNamaValue.text = name
        tvGunungValue.text = gunung
        tvJalurValue.text = via
        tvWaktuValue.text = date
        tvPembayaranValue.text = paymentMethod

        // Logika tombol batal
        btnCancel.setOnClickListener {
            if (!userId.isNullOrEmpty() && !transactionId.isNullOrEmpty()) {
                // Path Firebase untuk transaksi
                val databaseRef = FirebaseDatabase.getInstance()
                    .getReference("transactions/$userId/$transactionId")

                // Hapus data transaksi
                databaseRef.removeValue().addOnSuccessListener {
                    Toast.makeText(this, "Transaksi dibatalkan", Toast.LENGTH_SHORT).show()
                    finish() // Kembali ke halaman sebelumnya
                }.addOnFailureListener { error ->
                    Toast.makeText(this, "Gagal menghapus transaksi: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "User ID atau Transaksi ID tidak ditemukan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
