package com.example.baka.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.baka.R

data class Artikel(val judul: String, val imageRes: Int)

class ArtikelAdapter(private val listArtikel: List<Artikel>) :
    RecyclerView.Adapter<ArtikelAdapter.ArtikelViewHolder>() {

    class ArtikelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageArtikel: ImageView = itemView.findViewById(R.id.imageArtikel)
        val tvJudulArtikel: TextView = itemView.findViewById(R.id.titleArtikel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtikelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_artikel, parent, false)
        return ArtikelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtikelViewHolder, position: Int) {
        val artikel = listArtikel[position]
        holder.imageArtikel.setImageResource(artikel.imageRes)
        holder.tvJudulArtikel.text = artikel.judul
    }

    override fun getItemCount(): Int = listArtikel.size
}
