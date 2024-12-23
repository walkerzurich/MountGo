package com.example.baka.riwayat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.baka.R

class DaftarTransaksiAdapter(
    private val transaksiList: List<daftarTran>,
    private val onMoreInfoClick: (daftarTran) -> Unit
) : RecyclerView.Adapter<DaftarTransaksiAdapter.TransaksiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransaksiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_daftartransaksi, parent, false)
        return TransaksiViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransaksiViewHolder, position: Int) {
        val transaksi = transaksiList[position]

        holder.tvTitle.text = transaksi.title
        holder.tvLocation.text = transaksi.location
        holder.tvDate.text = transaksi.date
        holder.imgEvent.setImageResource(transaksi.imageResId)

        // Klik tombol More Info
        holder.btnMoreInfo.setOnClickListener {
            onMoreInfoClick(transaksi)
        }
    }

    override fun getItemCount(): Int = transaksiList.size

    class TransaksiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvLocation: TextView = itemView.findViewById(R.id.tvLocation)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val imgEvent: ImageView = itemView.findViewById(R.id.imgEvent)
        val btnMoreInfo: Button = itemView.findViewById(R.id.btnMoreInfo)
    }
}
