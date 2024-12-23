package com.example.baka.loginregister

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baka.R
import com.google.firebase.database.*

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lupasandi)

        // Inisialisasi Firebase Database
        database = FirebaseDatabase.getInstance().getReference("user")

        // Bind Views
        val inputEmail = findViewById<EditText>(R.id.inputEmail)
        val inputPassword = findViewById<EditText>(R.id.inputPassword)
        val inputPasswordNew = findViewById<EditText>(R.id.inputPasswordnew)
        val verifyButton = findViewById<Button>(R.id.verifyButton)
        val backButton = findViewById<ImageView>(R.id.backButton)

        // Tombol back untuk kembali ke halaman login
        backButton.setOnClickListener {
            onBackPressed() // Kembali ke halaman sebelumnya
        }

        // Tombol verifikasi dan ubah kata sandi
        verifyButton.setOnClickListener {
            val email = inputEmail.text.toString().trim()
            val password = inputPassword.text.toString().trim()
            val confirmPassword = inputPasswordNew.text.toString().trim()

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Kata sandi tidak cocok", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Cek email di Firebase
            database.orderByChild("email").equalTo(email)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            // Email ditemukan, ubah kata sandi
                            for (userSnapshot in snapshot.children) {
                                userSnapshot.ref.child("password").setValue(password)
                                    .addOnSuccessListener {
                                        Toast.makeText(
                                            this@ForgotPasswordActivity,
                                            "Kata sandi berhasil diubah",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        // Pindah ke halaman login
                                        val intent = Intent(
                                            this@ForgotPasswordActivity,
                                            loginActivity::class.java
                                        )
                                        startActivity(intent)
                                        finish()
                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(
                                            this@ForgotPasswordActivity,
                                            "Gagal mengubah kata sandi",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                            }
                        } else {
                            // Email tidak ditemukan
                            Toast.makeText(
                                this@ForgotPasswordActivity,
                                "Email tidak ditemukan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(
                            this@ForgotPasswordActivity,
                            "Terjadi kesalahan: ${error.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
    }
}
