package com.example.baka.transaksi

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.baka.R

class transaksiSelesai : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi_selesai)

        // Get transaction data from intent
        val transactionData = intent.getSerializableExtra("transactionData") as HashMap<String, String>

        // Bind data to UI components
        findViewById<TextView>(R.id.namaText).text = transactionData["name"]
        findViewById<TextView>(R.id.transactionIdText).text = transactionData["transactionId"]
        findViewById<TextView>(R.id.addressText).text = transactionData["address"]
        findViewById<TextView>(R.id.gunungText).text = transactionData["mountain"]
        findViewById<TextView>(R.id.viaText).text = transactionData["via"]
        findViewById<TextView>(R.id.dateText).text = transactionData["date"]
        findViewById<TextView>(R.id.paymentMethodText).text = transactionData["paymentMethod"]

        // Handle back button
        findViewById<Button>(R.id.buttonBack).setOnClickListener {
            finish()
        }
    }
}
