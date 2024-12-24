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
import com.example.baka.loginregister.loginActivity
import com.example.baka.profile.profile_page
import com.example.baka.utils.SessionManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class daftarTransaksi : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DaftarTransaksiAdapter
    private val transaksiList = mutableListOf<daftarTran>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftartransaksi)

        val sessionManager = SessionManager(this)

        // Cek apakah pengguna sudah login
        if (!sessionManager.isLoggedIn()) {
            // Jika belum login, pindahkan ke halaman login
            startActivity(Intent(this, loginActivity::class.java))
            finish()
        }

        // Jika login, ambil user ID dari sesi
        val userDetails = sessionManager.getUserDetails()
        val userId = userDetails["user_id"]

        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.rvDaftarTransaksi)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = DaftarTransaksiAdapter(transaksiList) { transaksi ->
            // Arahkan ke halaman detail transaksi dengan data lengkap
            val intent = Intent(this, detail_transaksi::class.java)
            intent.putExtra("userId", userId) // Kirim userId
            intent.putExtra("transactionId", transaksi.transactionId) // Kirim transactionId
            intent.putExtra("name", transaksi.name)
            intent.putExtra("gunung", transaksi.gunung)
            intent.putExtra("via", transaksi.via)
            intent.putExtra("date", transaksi.date)
            intent.putExtra("paymentMethod", transaksi.paymentMethod)
            startActivity(intent)
        }


        recyclerView.adapter = adapter

        // Ambil data transaksi dari Firebase
        if (!userId.isNullOrEmpty()) {
            val databaseRef = FirebaseDatabase.getInstance().getReference("transactions/$userId")
            databaseRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    transaksiList.clear() // Bersihkan daftar sebelumnya
                    for (data in snapshot.children) {
                        val transaction = data.getValue(daftarTran::class.java)
                        transaction?.let { transaksiList.add(it) }
                    }
                    adapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@daftarTransaksi, "Gagal mengambil data: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this, "User ID tidak valid!", Toast.LENGTH_SHORT).show()
        }

        // Inisialisasi tombol navbar
        findViewById<Button>(R.id.homeButton).setOnClickListener {
            startActivity(Intent(this, home::class.java))
        }

        findViewById<Button>(R.id.exploreButton).setOnClickListener {
            startActivity(Intent(this, explore::class.java))
        }

        findViewById<Button>(R.id.eventButton).setOnClickListener {
            startActivity(Intent(this, daftarTransaksi::class.java))
        }

        findViewById<Button>(R.id.profileButton).setOnClickListener {
            startActivity(Intent(this, profile_page::class.java))
        }
    }
}
