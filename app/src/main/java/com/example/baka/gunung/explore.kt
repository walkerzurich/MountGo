package com.example.baka.gunung

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baka.R
import com.example.baka.home.home
import com.example.baka.loginregister.loginActivity
import com.example.baka.profile.profile_page
import com.example.baka.riwayat.daftarTransaksi
import com.example.baka.utils.SessionManager
import com.google.firebase.database.*

class explore : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var gunungList: MutableList<Gunung>
    private lateinit var adapter: GunungAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

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

        // Initialize RecyclerView
        val rvGunung = findViewById<RecyclerView>(R.id.rvGunung)
        rvGunung.layoutManager = GridLayoutManager(this, 2)

        gunungList = mutableListOf()

        // Initialize Adapter
        adapter = GunungAdapter(this, gunungList) { gunung ->
            // Pass the Gunung ID to activity_information
            val intent = Intent(this, Information::class.java)
            intent.putExtra("GUNUNG_ID", gunung.idNumber)
            startActivity(intent)
        }
        rvGunung.adapter = adapter

        // Fetch data from Firebase
        database = FirebaseDatabase.getInstance().getReference("gunung")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                gunungList.clear()
                for (data in snapshot.children) {
                    val gunung = data.getValue(Gunung::class.java)
                    if (gunung != null) {
                        gunungList.add(gunung)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}