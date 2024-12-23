package com.example.baka.home

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baka.R
import com.example.baka.gunung.explore
import com.example.baka.home.ArtikelAdapter
import com.example.baka.home.EventAdapter
import com.example.baka.home.RekomendasiGunungAdapter
import com.example.baka.profile.profile
import com.google.android.material.bottomnavigation.BottomNavigationView

class home : AppCompatActivity() {
    private lateinit var recyclerViewRekomendasi: RecyclerView
    private lateinit var recyclerViewEvent: RecyclerView
    private lateinit var recyclerViewArtikel: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Inisialisasi BottomNavigationView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Setup navigasi untuk BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // Tetap di halaman home
                    true
                }
                R.id.nav_explore -> {
                    // Navigasi ke halaman Search
                    startActivity(Intent(this, explore::class.java))
                    true
                }
                R.id.nav_profile -> {
                    // Navigasi ke halaman Profile
                    startActivity(Intent(this, profile::class.java))
                    true
                }
                else -> false
            }
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