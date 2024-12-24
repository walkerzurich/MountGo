package com.example.baka.riwayat

import com.example.baka.R

data class daftarTran(
    val transactionId: String = "",
    val name: String = "",
    val title: String = "",
    val gunung: String = "",
    val location: String = "",
    val via: String = "",
    val date: String = "",
    val paymentMethod: String = "",
    val imageResource: Int = R.drawable.gunung_semeru // Default image jika tidak ada
)

