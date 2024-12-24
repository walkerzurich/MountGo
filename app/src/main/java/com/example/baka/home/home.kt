package com.example.baka.home

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baka.R
import com.example.baka.gunung.explore
import com.example.baka.loginregister.loginActivity
import com.example.baka.profile.profile_page
import com.example.baka.riwayat.daftarTransaksi
import com.example.baka.utils.SessionManager

class home : AppCompatActivity() {
    private lateinit var recyclerViewRekomendasi: RecyclerView
    private lateinit var recyclerViewEvent: RecyclerView
    private lateinit var recyclerViewArtikel: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

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
        findViewById<TextView>(R.id.tvGreeting).text = "Selamat Datang, $userName!"

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


        // Inisialisasi RecyclerView
        recyclerViewRekomendasi = findViewById(R.id.recyclerViewRekomendasi)
        recyclerViewEvent = findViewById(R.id.recyclerViewEvent)
        recyclerViewArtikel = findViewById(R.id.recyclerViewArtikel)

        // Inisialisasi TextView untuk Animasi
        val marqueeText = findViewById<TextView>(R.id.marqueeText)

        // ObjectAnimator untuk zoom-in dan zoom-out
        val scaleUpX = ObjectAnimator.ofFloat(marqueeText, "scaleX", 1f, 1.2f).apply {
            duration = 500
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }
        val scaleUpY = ObjectAnimator.ofFloat(marqueeText, "scaleY", 1f, 1.2f).apply {
            duration = 500
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }

        // Gabungkan animasi menggunakan AnimatorSet
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleUpX, scaleUpY)
        animatorSet.interpolator = LinearInterpolator()
        animatorSet.start()

        // Data Contoh
        val listGunung = listOf(
            Gunung("Gunung Merbabu", "Jawa Tengah", R.drawable.semeru),
            Gunung("Gunung Semeru", "Jawa Timur", R.drawable.semeru),
            Gunung("Gunung Rinjani", "NTB", R.drawable.semeru)
        )

        val listEvent = listOf(
            Event("Festival Pendakian", R.drawable.semeru),
            Event("Charity Hiking", R.drawable.semeru),
            Event("Mountain Clean Up", R.drawable.semeru)
        )

        val listArtikel = listOf(
            Artikel("7 Perlengkapan Outdoor Terbaik", R.drawable.semeru),
            Artikel("Tips Aman Mendaki Gunung", R.drawable.semeru),
            Artikel("Destinasi Gunung Terpopuler", R.drawable.semeru)
        )

        // Setup RecyclerView Rekomendasi Gunung
        recyclerViewRekomendasi.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewRekomendasi.adapter = RekomendasiGunungAdapter(listGunung)

        // Setup RecyclerView Event
        recyclerViewEvent.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewEvent.adapter = EventAdapter(listEvent)

        // Setup RecyclerView Artikel
        recyclerViewArtikel.layoutManager = LinearLayoutManager(this)
        recyclerViewArtikel.adapter = ArtikelAdapter(listArtikel)
    }
}