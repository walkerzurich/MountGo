package com.example.baka.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.baka.R
import com.example.baka.gunung.explore
import com.example.baka.home.home
import com.example.baka.riwayat.daftarTransaksi

class profile_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        // Inisialisasi tombol navbar
        val homeButton = findViewById<Button>(R.id.homeButton)
        val exploreButton = findViewById<Button>(R.id.exploreButton)
        val eventButton = findViewById<Button>(R.id.eventButton)
        val profileButton = findViewById<Button>(R.id.profileButton)

        // Pindah ke halaman Home
        homeButton.setOnClickListener {
            val intent = Intent(this, home::class.java)
            startActivity(intent)
        }

        // Pindah ke halaman Explore
        exploreButton.setOnClickListener {
            val intent = Intent(this, explore::class.java)
            startActivity(intent)
        }

        // Pindah ke halaman Event
        eventButton.setOnClickListener {
            val intent = Intent(this, daftarTransaksi::class.java)
            startActivity(intent)
        }

        // Pindah ke halaman Profile
        profileButton.setOnClickListener {
            val intent = Intent(this, profile_page::class.java)
            startActivity(intent)
        }
    }

}