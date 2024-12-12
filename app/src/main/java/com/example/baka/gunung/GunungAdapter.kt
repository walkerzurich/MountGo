package com.example.baka.gunung

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.baka.R

class GunungAdapter(
    private val context: Context,
    private val gunungList: List<Gunung>
) : RecyclerView.Adapter<GunungAdapter.GunungViewHolder>() {

    class GunungViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivGunung: ImageView = itemView.findViewById(R.id.ivGunung)
        val tvNamaGunung: TextView = itemView.findViewById(R.id.tvNamaGunung)
        val tvLokasiGunung: TextView = itemView.findViewById(R.id.tvLokasiGunung)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GunungViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_gunung, parent, false)
        return GunungViewHolder(view)
    }

    override fun onBindViewHolder(holder: GunungViewHolder, position: Int) {
        val gunung = gunungList[position]
        holder.ivGunung.setImageResource(gunung.imageRes)
        holder.tvNamaGunung.text = gunung.nama
        holder.tvLokasiGunung.text = gunung.lokasi
    }

    override fun getItemCount(): Int {
        return gunungList.size
    }
}

data class Gunung(val nama: String, val lokasi: String, val imageRes: Int)
