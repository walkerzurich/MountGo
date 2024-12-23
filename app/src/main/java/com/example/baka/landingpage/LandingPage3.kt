package com.example.baka.landingpage

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.baka.R
import com.example.baka.loginregister.Register


class LandingPage3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_landing_page3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnNext = findViewById<ImageButton>(R.id.btnNext)

        // Tombol untuk berpindah ke halaman selanjutnya
        btnNext.setOnClickListener {
            val intent = Intent(this, Register::class.java) // Ganti dengan halaman tujuan
            startActivity(intent)
        }
    }
}