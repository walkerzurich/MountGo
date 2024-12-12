package com.example.baka.loginregister

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baka.R
import com.google.android.material.textfield.TextInputEditText

class Register : AppCompatActivity() {

    // Deklarasi variabel untuk semua elemen UI
    private lateinit var nationalityInput: TextInputEditText
    private lateinit var idTypeInput: TextInputEditText
    private lateinit var idNumberInput: TextInputEditText
    private lateinit var studentCheckbox: CheckBox
    private lateinit var studentIdInput: TextInputEditText
    private lateinit var fullNameInput: TextInputEditText
    private lateinit var provinceInput: TextInputEditText
    private lateinit var cityInput: TextInputEditText
    private lateinit var addressInput: TextInputEditText
    private lateinit var birthDateInput: TextInputEditText
    private lateinit var genderInput: TextInputEditText
    private lateinit var emailInput: TextInputEditText
    private lateinit var guardianNameInput: TextInputEditText
    private lateinit var guardianPhoneInput: TextInputEditText
    private lateinit var guardianRelationshipInput: TextInputEditText
    private lateinit var emergencyContactNameInput: TextInputEditText
    private lateinit var emergencyContactPhoneInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var confirmPasswordInput: TextInputEditText
    private lateinit var registerButton: Button
    private lateinit var loginText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Menghubungkan variabel dengan elemen UI
        nationalityInput = findViewById(R.id.nationality)
        idTypeInput = findViewById(R.id.id_type)
        idNumberInput = findViewById(R.id.id_number)
        studentCheckbox = findViewById(R.id.student_checkbox)
        studentIdInput = findViewById(R.id.student_id)
        fullNameInput = findViewById(R.id.full_name)
        provinceInput = findViewById(R.id.province)
        cityInput = findViewById(R.id.city)
        addressInput = findViewById(R.id.address)
        birthDateInput = findViewById(R.id.birth_date)
        genderInput = findViewById(R.id.gender)
        emailInput = findViewById(R.id.email)
        guardianNameInput = findViewById(R.id.guardian_name)
        guardianPhoneInput = findViewById(R.id.guardian_phone)
        guardianRelationshipInput = findViewById(R.id.guardian_relationship)
        emergencyContactNameInput = findViewById(R.id.emergency_contact_name)
        emergencyContactPhoneInput = findViewById(R.id.emergency_contact_phone)
        passwordInput = findViewById(R.id.password)
        confirmPasswordInput = findViewById(R.id.confirm_password)
        registerButton = findViewById(R.id.register_button)
        loginText = findViewById(R.id.registerLink)

        // Menangani event klik tombol register
        registerButton.setOnClickListener {
            handleRegistration()
        }

        // Menangani event klik teks "Masuk" untuk berpindah ke halaman login
        loginText.setOnClickListener {
            // Pindah ke halaman LoginActivity
            val intent = Intent(this@Register, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun handleRegistration() {
        // Ambil nilai dari semua input
        val nationality = nationalityInput.text.toString().trim()
        val idType = idTypeInput.text.toString().trim()
        val idNumber = idNumberInput.text.toString().trim()
        val isStudent = studentCheckbox.isChecked
        val studentId = studentIdInput.text.toString().trim()
        val fullName = fullNameInput.text.toString().trim()
        val province = provinceInput.text.toString().trim()
        val city = cityInput.text.toString().trim()
        val address = addressInput.text.toString().trim()
        val birthDate = birthDateInput.text.toString().trim()
        val gender = genderInput.text.toString().trim()
        val email = emailInput.text.toString().trim()
        val guardianName = guardianNameInput.text.toString().trim()
        val guardianPhone = guardianPhoneInput.text.toString().trim()
        val guardianRelationship = guardianRelationshipInput.text.toString().trim()
        val emergencyContactName = emergencyContactNameInput.text.toString().trim()
        val emergencyContactPhone = emergencyContactPhoneInput.text.toString().trim()
        val password = passwordInput.text.toString().trim()
        val confirmPassword = confirmPasswordInput.text.toString().trim()

        // Validasi input (kamu bisa menambahkan lebih banyak validasi sesuai kebutuhan)
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Harap lengkapi semua data yang diperlukan", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Kata sandi dan konfirmasi kata sandi tidak cocok", Toast.LENGTH_SHORT).show()
            return
        }

        // Tampilkan pesan sukses (simulasi proses registrasi)
        Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()

        // Di sini, kamu bisa melakukan hal lain seperti mengirim data ke server atau menyimpan di database
    }
}
