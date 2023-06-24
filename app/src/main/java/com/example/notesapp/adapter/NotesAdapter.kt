package com.example.notesapp.adapter

import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.entities.Notes

class NotesAdapter(val arrList : List<Notes>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>(){

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

        if(arrList[position].color != null) {
            holder.itemView.findViewById<CardView>(R.id.cardView).setCardBackgroundColor(Color.parseColor(arrList[position].color))
        }
        else{
            holder.itemView.findViewById<CardView>(R.id.cardView).setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.ColorLightBlack))
        }

        if(arrList[position].imgPath != null) {
            holder.itemView.findViewById<ImageView>(R.id.imgNote).setImageBitmap(BitmapFactory.decodeFile(arrList[position].imgPath))
            holder.itemView.findViewById<ImageView>(R.id.imgNote).visibility = View.VISIBLE
        }else {
            holder.itemView.findViewById<ImageView>(R.id.imgNote).visibility = View.GONE
        }

    }

    class NotesViewHolder(view:View) : RecyclerView.ViewHolder(view) {

    }
}