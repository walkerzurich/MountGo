package com.example.baka.transaksi

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.baka.R
import com.example.baka.loginregister.loginActivity
import com.example.baka.utils.SessionManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class formTransaksi : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_transaksi)

        val sessionManager = SessionManager(this)

        // Cek apakah pengguna sudah login
        if (!sessionManager.isLoggedIn()) {
            startActivity(Intent(this, loginActivity::class.java))
            finish()
        }

        // Ambil detail pengguna dari sesi
        val userDetails = sessionManager.getUserDetails()
        val userId = userDetails["user_id"]

        // Inisialisasi elemen UI
        val namaText: TextView = findViewById(R.id.namaText)
        val nikText: TextView = findViewById(R.id.nikText)
        val alamatText: TextView = findViewById(R.id.alamatText)
        val teleponText: TextView = findViewById(R.id.teleponText)
        val daruratText: TextView = findViewById(R.id.daruratText)
        val emailText: TextView = findViewById(R.id.emailText)

        val bookingDateEditText: EditText = findViewById(R.id.bookingDate)
        val bayarButton: Button = findViewById(R.id.bayarButton)

        // Tambahkan listener untuk membuka DatePickerDialog
        bookingDateEditText.setOnClickListener {
            showDatePickerDialog(bookingDateEditText)
        }

        // Ambil data pengguna dari database Firebase
        if (!userId.isNullOrEmpty()) {
            val database = FirebaseDatabase.getInstance().getReference("user/$userId")
            database.get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val user = snapshot.value as Map<String, String>
                    namaText.text = user["fullName"] ?: "-"
                    nikText.text = user["idNumber"] ?: "-"
                    alamatText.text = user["address"] ?: "-"
                    teleponText.text = user["phoneNumber"] ?: "-"
                    daruratText.text = user["emergencyPhoneNumber"] ?: "-"
                    emailText.text = user["email"] ?: "-"
                } else {
                    Toast.makeText(this, "Data pengguna tidak ditemukan!", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Gagal mengambil data pengguna: ${it.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "User ID tidak valid!", Toast.LENGTH_SHORT).show()
        }

        // Setup Dropdown untuk Pendakian Via
        val dropdownPendakianVia: AutoCompleteTextView = findViewById(R.id.dropdownPendakianVia)
        val viaOptions = listOf("Via A", "Via B", "Via C")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, viaOptions)
        dropdownPendakianVia.setAdapter(adapter)

        // Setup tombol metode pembayaran
        val paymentButtons = listOf(
            findViewById<Button>(R.id.qrisButton),
            findViewById<Button>(R.id.gopayButton),
            findViewById<Button>(R.id.ovoButton),
            findViewById<Button>(R.id.danaButton),
            findViewById<Button>(R.id.bankTransferButton)
        )

        var selectedPaymentMethod = "" // Variabel untuk menyimpan pilihan pembayaran

        paymentButtons.forEach { button ->
            button.setOnClickListener {
                // Reset warna semua tombol
                paymentButtons.forEach { it.setBackgroundColor(resources.getColor(android.R.color.darker_gray)) }

                // Ubah warna tombol yang ditekan menjadi hijau
                button.setBackgroundColor(resources.getColor(R.color.green))

                // Simpan nama metode pembayaran sesuai ID tombol
                selectedPaymentMethod = when (button.id) {
                    R.id.qrisButton -> "QRIS"
                    R.id.gopayButton -> "GoPay"
                    R.id.ovoButton -> "OVO"
                    R.id.danaButton -> "DANA"
                    R.id.bankTransferButton -> "Bank Transfer"
                    else -> ""
                }

            }
        }

        // Tombol Bayar
        bayarButton.setOnClickListener {
            if (bookingDateEditText.text.isNullOrEmpty()) {
                Toast.makeText(this, "Tanggal booking harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (selectedPaymentMethod.isEmpty()) {
                Toast.makeText(this, "Pilih metode pembayaran!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Buat data transaksi
            val transactionId = "TX-${UUID.randomUUID()}"
            val transactionData = mapOf(
                "transactionId" to transactionId,
                "name" to namaText.text.toString(),
                "nik" to nikText.text.toString(),
                "address" to alamatText.text.toString(),
                "phone" to teleponText.text.toString(),
                "emergencyContact" to daruratText.text.toString(),
                "email" to emailText.text.toString(),
                "via" to dropdownPendakianVia.text.toString(),
                "date" to bookingDateEditText.text.toString(),
                "paymentMethod" to selectedPaymentMethod
            )

            // Simpan ke database Firebase
            if (!userId.isNullOrEmpty()) {
                val databaseRef = FirebaseDatabase.getInstance().getReference("transactions/$userId/$transactionId")
                databaseRef.setValue(transactionData).addOnSuccessListener {
                    Toast.makeText(this, "Transaksi berhasil disimpan!", Toast.LENGTH_SHORT).show()

                    // Pindah ke halaman detail transaksi (opsional)
                    val intent = Intent(this, transaksiSelesai::class.java)
                    intent.putExtra("transactionData", HashMap(transactionData))
                    startActivity(intent)
                }.addOnFailureListener {
                    Toast.makeText(this, "Gagal menyimpan transaksi: ${it.message}", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "User ID tidak valid!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
                editText.setText(formattedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}
