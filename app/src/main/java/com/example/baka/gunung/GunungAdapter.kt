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
    private val gunungList: List<Gunung>,
    private val onItemClick: (Gunung) -> Unit
) : RecyclerView.Adapter<GunungAdapter.GunungViewHolder>() {

    inner class GunungViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val namaGunung: TextView = view.findViewById(R.id.tvNamaGunung)
        val alamatGunung: TextView = view.findViewById(R.id.tvLokasiGunung)
        val imageGunung: ImageView = view.findViewById(R.id.ivGunung)

        fun bind(gunung: Gunung) {
            namaGunung.text = gunung.nama
            alamatGunung.text = gunung.alamat
            imageGunung.setImageResource(gunung.imageRes)

            // Set onClickListener for each item
            itemView.setOnClickListener {
                onItemClick(gunung)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GunungViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_gunung, parent, false)
        return GunungViewHolder(view)
    }

    override fun onBindViewHolder(holder: GunungViewHolder, position: Int) {
        holder.bind(gunungList[position])
    }

    override fun getItemCount(): Int = gunungList.size
}
