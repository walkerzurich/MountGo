package com.example.baka.riwayat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baka.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        /*val eventList = listOf(
            Event("Camping Ceria", "Gn. Merbabu, Boyolali, Jawa Tengah", "05 November 2024\n17.00 PM"),
            Event("Mendaki Bersama", "Gn. Merbabu, Boyolali, Jawa Tengah", "05 November 2024\n17.00 PM"),
            Event("Mendaki Bersama", "Gn. Merbabu, Boyolali, Jawa Tengah", "05 November 2024\n17.00 PM"),
        )*/

        //recyclerView.adapter = EventAdapter(eventList)
    }
}
