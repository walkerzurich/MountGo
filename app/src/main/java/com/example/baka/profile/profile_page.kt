package com.example.baka.profile

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.baka.R
import com.example.baka.gunung.explore
import com.example.baka.home.home
import com.example.baka.loginregister.loginActivity
import com.example.baka.riwayat.daftarTransaksi
import com.example.baka.utils.SessionManager

class profile_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Inisialisasi SessionManager
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

        // Tampilkan data pengguna di UI
        findViewById<TextView>(R.id.user_name).text = userName

        // Log Out
        findViewById<LinearLayout>(R.id.logout).setOnClickListener {
            // Hapus sesi dan pindah ke halaman login
            sessionManager.logout()
            startActivity(Intent(this, loginActivity::class.java))
            finish()
        }

        // Pindah ke halaman Home
        findViewById<TextView>(R.id.homeButton).setOnClickListener {
            val intent = Intent(this, home::class.java)
            startActivity(intent)
        }

        // Pindah ke halaman Explore
        findViewById<TextView>(R.id.exploreButton).setOnClickListener {
            val intent = Intent(this, explore::class.java)
            startActivity(intent)
        }

        // Pindah ke halaman Riwayat
        findViewById<TextView>(R.id.eventButton).setOnClickListener {
            val intent = Intent(this, daftarTransaksi::class.java)
            startActivity(intent)
        }

        // Pindah ke halaman Profile
        findViewById<TextView>(R.id.profileButton).setOnClickListener {
            val intent = Intent(this, profile_page::class.java)
            startActivity(intent)
        }
    }
}
