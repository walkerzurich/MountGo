package com.example.baka.transaksi

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.baka.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class formTransaksi : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_transaksi)

        // Inisialisasi elemen UI
        val namaText: TextView = findViewById(R.id.namaText)
        val nikText: TextView = findViewById(R.id.nikText)
        val alamatText: TextView = findViewById(R.id.alamatText)
        val teleponText: TextView = findViewById(R.id.teleponText)
        val daruratText: TextView = findViewById(R.id.daruratText)
        val emailText: TextView = findViewById(R.id.emailText)

        val dropdownPendakianVia: AutoCompleteTextView = findViewById(R.id.dropdownPendakianVia)
        val bookingDateEditText: EditText = findViewById(R.id.bookingDate)

        val paymentButtons = listOf(
            findViewById<Button>(R.id.qrisButton),
            findViewById<Button>(R.id.gopayButton),
            findViewById<Button>(R.id.ovoButton),
            findViewById<Button>(R.id.danaButton),
            findViewById<Button>(R.id.bankTransferButton)
        )

        val bayarButton: Button = findViewById(R.id.bayarButton)

        // Setup Dropdown untuk Pendakian Via
        val viaOptions = listOf("Via A", "Via B", "Via C")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, viaOptions)
        dropdownPendakianVia.setAdapter(adapter)

        // Setup Date Picker untuk Booking Date
        bookingDateEditText.setOnClickListener {
            showDatePickerDialog(bookingDateEditText)
        }

        // Ambil data dari Firebase
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val userId = currentUser?.uid

        if (userId != null) {
            val database = FirebaseDatabase.getInstance().getReference("user/$userId")
            database.get().addOnSuccessListener { snapshot ->
                val user = snapshot.value as Map<String, String>
                namaText.text = user["fullName"]
                nikText.text = user["idNumber"]
                alamatText.text = user["address"]
                teleponText.text = user["phoneNumber"]
                daruratText.text = user["emergencyPhoneNumber"]
                emailText.text = user["email"]
            }
        }

        // Setup tombol metode pembayaran
        var selectedPaymentMethod = ""
        paymentButtons.forEach { button ->
            button.setOnClickListener {
                paymentButtons.forEach { it.setBackgroundColor(resources.getColor(android.R.color.darker_gray)) }
                button.setBackgroundColor(resources.getColor(R.color.green))
                selectedPaymentMethod = button.text.toString()
            }
        }

        // Tombol "Bayar"
        bayarButton.setOnClickListener {
            if (userId == null) {
                Toast.makeText(this, "User tidak terautentikasi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validasi data sebelum pembayaran
            if (dropdownPendakianVia.text.toString().isEmpty() || bookingDateEditText.text.toString().isEmpty()) {
                Toast.makeText(this, "Harap lengkapi data pendakian!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Buat nomor transaksi unik
            val transactionId = "TX-${UUID.randomUUID()}"

            // Simpan data transaksi ke Firebase
            val transaction = mapOf(
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

            val transactionRef = FirebaseDatabase.getInstance().getReference("transactions/$userId/$transactionId")
            transactionRef.setValue(transaction).addOnSuccessListener {
                // Pindah ke halaman transaksi selesai
                val intent = Intent(this, transaksiSelesai::class.java)
                intent.putExtra("transactionData", HashMap(transaction))
                startActivity(intent)
            }.addOnFailureListener {
                Toast.makeText(this, "Gagal menyimpan transaksi!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Fungsi untuk menampilkan DatePicker
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
    // Get Gunung ID from Intent
    val gunungId = intent.getStringExtra("GUNUNG_ID")

}
