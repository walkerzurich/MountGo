package com.example.baka.loginregister

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baka.R
import com.example.baka.home.home
import com.example.baka.utils.SessionManager
import com.google.firebase.database.*

class loginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerLink: TextView
    private lateinit var database: DatabaseReference
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inisialisasi SessionManager
        sessionManager = SessionManager(this)

        // Bind TextView
        val registerLink = findViewById<TextView>(R.id.registerLink)

        // Set click listener untuk berpindah ke RegisterActivity
        registerLink.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        // Bind TextView
        val lupasandiLink = findViewById<TextView>(R.id.lupasandiLink)

        // Set click listener untuk berpindah ke ForgotPasswordActivity
        lupasandiLink.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        // Inisialisasi Firebase Database
        database = FirebaseDatabase.getInstance("https://mountgo-baka-default-rtdb.asia-southeast1.firebasedatabase.app").reference

        // Inisialisasi View dari layout
        emailEditText = findViewById(R.id.inputEmail)
        passwordEditText = findViewById(R.id.inputPassword)
        loginButton = findViewById(R.id.loginButton)

        // Login Button
        loginButton.setOnClickListener {
            handleLogin()
        }
    }

    // Fungsi untuk menangani login
    private fun handleLogin() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }

        // Ambil semua child dari "user" di Firebase
        database.child("user").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var loginSuccess = false

                for (userSnapshot in snapshot.children) {
                    val userId = userSnapshot.key
                    val storedEmail = userSnapshot.child("email").getValue(String::class.java)
                    val storedPassword = userSnapshot.child("password").getValue(String::class.java)
                    val fullName = userSnapshot.child("fullName").getValue(String::class.java)

                    // Cek apakah email dan password cocok
                    if (storedEmail == email && storedPassword == password) {
                        loginSuccess = true

                        // Simpan data ke session
                        sessionManager.createLoginSession(
                            userId = userId ?: "",
                            userName = fullName ?: "",
                            email = storedEmail ?: ""
                        )

                        // Login berhasil
                        Toast.makeText(this@loginActivity, "Login Berhasil!", Toast.LENGTH_SHORT).show()

                        // Pindah ke halaman home
                        val intent = Intent(this@loginActivity, home::class.java)
                        startActivity(intent)
                        finish()
                        break
                    }
                }

                // Jika login gagal
                if (!loginSuccess) {
                    Toast.makeText(this@loginActivity, "Email atau Password salah!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@loginActivity, "Database Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
