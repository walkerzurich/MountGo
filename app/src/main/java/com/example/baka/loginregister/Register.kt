package com.example.baka.loginregister

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.google.android.material.textfield.TextInputEditText
import android.widget.Button
import android.widget.AutoCompleteTextView
import android.widget.ArrayAdapter
import com.example.baka.R
import java.util.*

class Register : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inisialisasi Firebase
        database = FirebaseDatabase.getInstance("https://mountgo-baka-default-rtdb.asia-southeast1.firebasedatabase.app").reference

        // Deklarasi Input dari XML
        val idNumber = findViewById<TextInputEditText>(R.id.id_number)
        val nomorHp = findViewById<TextInputEditText>(R.id.nomorHP)
        val fullName = findViewById<TextInputEditText>(R.id.full_name)
        val province = findViewById<TextInputEditText>(R.id.province)
        val city = findViewById<TextInputEditText>(R.id.city)
        val address = findViewById<TextInputEditText>(R.id.address)
        val birthDate = findViewById<TextInputEditText>(R.id.birth_date)
        val gender = findViewById<AutoCompleteTextView>(R.id.gender)
        val email = findViewById<TextInputEditText>(R.id.email)
        val emergencyContactName = findViewById<TextInputEditText>(R.id.emergency_contact_name)
        val emergencyPhoneNumber = findViewById<TextInputEditText>(R.id.nomorHPDarurat)
        val password = findViewById<TextInputEditText>(R.id.password)
        val confirmPassword = findViewById<TextInputEditText>(R.id.confirm_password)
        val registerButton = findViewById<Button>(R.id.register_button)

        // Dropdown Gender
        val genderOptions = arrayOf("Laki-laki", "Perempuan")
        val genderAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, genderOptions)
        gender.setAdapter(genderAdapter)

        gender.setOnClickListener { gender.showDropDown() }

        // Tanggal Lahir (Date Picker)
        birthDate.setOnClickListener { showDatePicker(birthDate) }

        // Tombol Daftar
        registerButton.setOnClickListener {
            // Validasi Input
            if (idNumber.text.isNullOrEmpty() || nomorHp.text.isNullOrEmpty() ||
                fullName.text.isNullOrEmpty() || province.text.isNullOrEmpty() ||
                city.text.isNullOrEmpty() || address.text.isNullOrEmpty() ||
                birthDate.text.isNullOrEmpty() || gender.text.isNullOrEmpty() ||
                email.text.isNullOrEmpty() || emergencyContactName.text.isNullOrEmpty() ||
                emergencyPhoneNumber.text.isNullOrEmpty() || password.text.isNullOrEmpty() ||
                confirmPassword.text.isNullOrEmpty()
            ) {
                Toast.makeText(this, "Semua kolom wajib diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validasi Password
            if (password.text.toString() != confirmPassword.text.toString()) {
                Toast.makeText(this, "Password dan Konfirmasi Password tidak cocok", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Penomoran Otomatis User di Firebase
            database.child("user").get().addOnSuccessListener { snapshot ->
                val userCount = (snapshot.childrenCount + 1).toString().padStart(2, '0')

                // Data User
                val user = User(
                    idNumber.text.toString(),
                    nomorHp.text.toString(),
                    fullName.text.toString(),
                    province.text.toString(),
                    city.text.toString(),
                    address.text.toString(),
                    birthDate.text.toString(),
                    gender.text.toString(),
                    email.text.toString(),
                    nomorHp.text.toString(),
                    emergencyContactName.text.toString(),
                    emergencyPhoneNumber.text.toString(),
                    password.text.toString()
                )

                // Simpan ke Firebase
                database.child("user").child("user$userCount").setValue(user)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, loginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Registrasi Gagal: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    // Fungsi untuk menampilkan DatePickerDialog
    private fun showDatePicker(inputField: TextInputEditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDate = String.format("%02d-%02d-%04d", selectedDay, selectedMonth + 1, selectedYear)
            inputField.setText(formattedDate)
        }, year, month, day)

        datePickerDialog.show()
    }
}
