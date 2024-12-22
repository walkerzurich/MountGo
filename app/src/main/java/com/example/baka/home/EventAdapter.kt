package com.example.baka.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.baka.R

data class Event(val nama: String, val imageRes: Int)

class EventAdapter(private val listEvent: List<Event>) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageEvent: ImageView = itemView.findViewById(R.id.imageEvent)
        val tvNamaEvent: TextView = itemView.findViewById(R.id.tvNamaEvent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = listEvent[position]
        holder.imageEvent.setImageResource(event.imageRes)
        holder.tvNamaEvent.text = event.nama
    }

    override fun getItemCount(): Int = listEvent.size
}
