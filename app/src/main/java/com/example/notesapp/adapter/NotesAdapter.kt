package com.example.notesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.entities.Notes

class NotesAdapter(val arrList : ArrayList<Notes>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_notes, parent, false))
    }

    override fun getItemCount(): Int {
        return arrList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvTitle).text = arrList[position].title
        holder.itemView.findViewById<TextView>(R.id.tvDesc).text = arrList[position].noteText
        holder.itemView.findViewById<TextView>(R.id.tvDateTime).text = arrList[position].dateTime
    }

    class NotesViewHolder(view:View) : RecyclerView.ViewHolder(view) {

    }
}