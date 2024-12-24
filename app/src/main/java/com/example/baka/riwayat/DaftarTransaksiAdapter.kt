package com.example.baka.riwayat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.baka.R

class DaftarTransaksiAdapter(
    private val transaksiList: List<daftarTran>,
    private val onItemClick: (daftarTran) -> Unit
) : RecyclerView.Adapter<DaftarTransaksiAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val location: TextView = view.findViewById(R.id.location)
        val date: TextView = view.findViewById(R.id.date)
        val image: ImageView = view.findViewById(R.id.image)

        fun bind(transaksi: daftarTran, onItemClick: (daftarTran) -> Unit) {
            title.text = transaksi.title
            location.text = transaksi.location
            date.text = transaksi.date
            image.setImageResource(transaksi.imageResource)
            itemView.setOnClickListener { onItemClick(transaksi) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_daftartransaksi, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(transaksiList[position], onItemClick)
    }

    override fun getItemCount(): Int = transaksiList.size
}
