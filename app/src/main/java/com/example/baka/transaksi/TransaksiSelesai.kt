package com.example.baka.transaksi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.baka.R
import com.example.baka.riwayat.daftarTransaksi

class transaksiSelesai : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi_selesai)

        val transactionData = intent.getSerializableExtra("transactionData") as? HashMap<String, String> ?: return

        // Inisialisasi elemen UI
        val namaText: TextView = findViewById(R.id.namaText)
        val transactionIdText: TextView = findViewById(R.id.transactionIdText)
        val addressText: TextView = findViewById(R.id.addressText)
        val gunungText: TextView = findViewById(R.id.gunungText)
        val viaText: TextView = findViewById(R.id.viaText)
        val dateText: TextView = findViewById(R.id.dateText)
        val paymentMethodText: TextView = findViewById(R.id.paymentMethodText)

        // Set data ke UI
        namaText.text = transactionData["name"] ?: "-"
        transactionIdText.text = transactionData["transactionId"] ?: "-"
        addressText.text = transactionData["address"] ?: "-"
        gunungText.text = transactionData["gunung"] ?: "-"
        viaText.text = transactionData["via"] ?: "-"
        dateText.text = transactionData["date"] ?: "-"
        paymentMethodText.text = transactionData["paymentMethod"] ?: "-"

        // Tombol kembali
        findViewById<Button>(R.id.buttonBack).setOnClickListener {
            val intent = Intent(this, daftarTransaksi::class.java)
            startActivity(intent)
            finish()
        }

    }
}
