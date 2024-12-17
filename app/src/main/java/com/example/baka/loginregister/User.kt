package com.example.baka.loginregister

data class User(
    val idNumber: String = "",
    val studentId: String = "",
    val fullName: String = "",
    val province: String = "",
    val city: String = "",
    val address: String = "",
    val birthDate: String = "",
    val gender: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val emergencyContactName: String = "",
    val emergencyPhoneNumber: String = "",
    val password: String = ""
)
