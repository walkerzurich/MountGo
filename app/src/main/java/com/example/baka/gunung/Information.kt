package com.example.baka.gunung

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.baka.R
import com.example.baka.loginregister.loginActivity
import com.example.baka.transaksi.formTransaksi
import com.example.baka.utils.SessionManager
import com.google.firebase.database.*

class Information : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        // Get Gunung ID from Intent
        val idNumber = intent.getStringExtra("GUNUNG_ID") ?: return

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

        // Initialize Firebase Database reference
        database = FirebaseDatabase.getInstance().getReference("gunung")

        // Bind views
        val imgGunung = findViewById<ImageView>(R.id.imgGunung)
        val tvNamaGunung = findViewById<TextView>(R.id.tvNamaGunung)
        val tvAlamatGunung = findViewById<TextView>(R.id.tvAlamatGunung)
        val tvDeskripsiGunung = findViewById<TextView>(R.id.tvDeskripsiGunung)
        val tvJamOperasional = findViewById<TextView>(R.id.tvJamOperasional)
        val tvMedan = findViewById<TextView>(R.id.tvMedan)
        val btnBooking = findViewById<Button>(R.id.btnBooking)

        // Fetch data from Firebase
        database.orderByChild("idNumber").equalTo(idNumber)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (data in snapshot.children) {
                        val nama = data.child("nama").getValue(String::class.java) ?: "Nama tidak tersedia"
                        val alamat = data.child("alamat").getValue(String::class.java) ?: "Alamat tidak tersedia"
                        val deskripsi = data.child("deskripsi").getValue(String::class.java) ?: "Deskripsi tidak tersedia"
                        val jamOperasional = data.child("operationalHours").getValue(String::class.java) ?: "Tidak tersedia"
                        val medan = data.child("medan").getValue(String::class.java) ?: "Tidak tersedia"

                        // Set data to views
                        tvNamaGunung.text = nama
                        tvAlamatGunung.text = alamat
                        tvDeskripsiGunung.text = deskripsi
                        tvJamOperasional.text = jamOperasional
                        tvMedan.text = medan

                        // Set static image for now
                        imgGunung.setImageResource(R.drawable.gunung_semeru)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                    tvNamaGunung.text = "Error: ${error.message}"
                }
            })

        // Handle Booking button click
        btnBooking.setOnClickListener {
            val intent = Intent(this, formTransaksi::class.java)
            intent.putExtra("GUNUNG_ID", idNumber) // Pass Gunung ID to the next activity
            startActivity(intent)
        }

        // Inisialisasi Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Aktifkan tombol Back
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    // Handle tombol Back
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
