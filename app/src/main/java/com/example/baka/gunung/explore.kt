package com.example.baka.gunung

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baka.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class explore : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        val gunungList = listOf(
            Gunung("Gunung Merbabu", "Boyolali, Jawa Tengah", R.drawable.gunung_merbabu),
            Gunung("Gunung Merapi", "Magelang, Jawa Tengah", R.drawable.gunung_merapi),
            Gunung("Gunung Semeru", "Lumajang, Jawa Timur", R.drawable.gunung_semeru),
            Gunung("Gunung Bromo", "Probolinggo, Jawa Timur", R.drawable.gunung_bromo)
        )

        val rvGunung = findViewById<RecyclerView>(R.id.rvGunung)
        rvGunung.layoutManager = GridLayoutManager(this, 2)
        rvGunung.adapter = GunungAdapter(this, gunungList)

        /*var mainText: TextView = findViewById(R.id.tvExplore)*/


    }
}
