package com.example.baka.loginregister

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.baka.R

class loginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerLink: TextView  // Tambahkan variabel untuk link registrasi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inisialisasi TextView untuk "Lupa kata sandi"
        val forgotPasswordText: TextView = findViewById(R.id.lupasandiLink)

        // Ketika "Lupa kata sandi" diklik, pindah ke halaman ForgotPasswordActivity
        forgotPasswordText.setOnClickListener {
            val intent = Intent(this@loginActivity, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        // Inisialisasi View dari layout
        emailEditText = findViewById(R.id.inputEmail)
        passwordEditText = findViewById(R.id.inputPassword)
        loginButton = findViewById(R.id.loginButton)

        // Mengatur tombol "Back"
        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            // Kembali ke activity sebelumnya
            onBackPressed()
        }

        // Inisialisasi TextView untuk pindah ke halaman registrasi
        registerLink = findViewById(R.id.registerLink)  // Pastikan ID sesuai dengan XML

        // Ketika "Daftar" atau "Masuk" diklik, pindah ke halaman RegisterActivity
        registerLink.setOnClickListener {
            val intent = Intent(this@loginActivity, Register::class.java)
            startActivity(intent)
        }

        // Handle login button click
        loginButton.setOnClickListener {
            handleLogin()
        }
    }

    // Fungsi untuk menangani login (logika login bisa diimplementasikan di sini)
    private fun handleLogin() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        // Logika login sederhana (validasi atau aksi lainnya bisa ditambahkan di sini)
        if (email.isEmpty() || password.isEmpty()) {
            // Tampilkan pesan error (ini hanya contoh sederhana, Anda bisa mengganti dengan Toast atau SnackBar)
            emailEditText.error = "Email tidak boleh kosong"
            passwordEditText.error = "Kata sandi tidak boleh kosong"
        } else {
            // Lakukan aksi login, misalnya cek ke database atau autentikasi
        }
    }
}
