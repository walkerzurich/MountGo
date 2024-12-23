package com.example.baka.riwayat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baka.R
import com.example.baka.gunung.explore
import com.example.baka.home.home
import com.example.baka.profile.profile_page


class daftarTransaksi : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DaftarTransaksiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftartransaksi)

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

        // Data Dummy
        val transaksiList = listOf(
            daftarTran("Camping Ceria", "Gn. Merbabu, Boyolali, Jawa Tengah", "05 November 2024", R.drawable.gunung),
            daftarTran("Hiking Seru", "Gn. Gede, Bogor, Jawa Barat", "15 November 2024", R.drawable.gunung),
            daftarTran("Adventure Extreme", "Gn. Semeru, Lumajang, Jawa Timur", "25 November 2024", R.drawable.gunung)
        )

        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.rvDaftarTransaksi)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inisialisasi Adapter
        adapter = DaftarTransaksiAdapter(transaksiList) { transaksi ->
            // Arahkan ke halaman detail transaksi
            val intent = Intent(this, detail_transaksi::class.java)
            intent.putExtra("TITLE", transaksi.title)
            intent.putExtra("LOCATION", transaksi.location)
            intent.putExtra("DATE", transaksi.date)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
    }
}
