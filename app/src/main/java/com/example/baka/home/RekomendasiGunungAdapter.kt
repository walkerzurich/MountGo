package com.example.baka.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.baka.R

data class Gunung(val nama: String, val lokasi: String, val imageRes: Int)

class RekomendasiGunungAdapter(private val listGunung: List<Gunung>) :
    RecyclerView.Adapter<RekomendasiGunungAdapter.GunungViewHolder>() {

    class GunungViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageGunung: ImageView = itemView.findViewById(R.id.imageGunung)
        val tvNamaGunung: TextView = itemView.findViewById(R.id.tvNamaGunung)
        val tvLokasiGunung: TextView = itemView.findViewById(R.id.tvLokasiGunung)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GunungViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rekomendasi_gunung, parent, false)
        return GunungViewHolder(view)
    }

    override fun onBindViewHolder(holder: GunungViewHolder, position: Int) {
        val gunung = listGunung[position]
        holder.imageGunung.setImageResource(gunung.imageRes)
        holder.tvNamaGunung.text = gunung.nama
        holder.tvLokasiGunung.text = gunung.lokasi
    }

    override fun getItemCount(): Int = listGunung.size
}
