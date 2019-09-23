package com.example.notetaker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class NoteAdapter(val noteList: ArrayList<Note>, val listener: (Note, Int) -> Unit) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note: Note = noteList[position]
        holder.bind(note, position, listener)
        holder.titleTextView.text = note.title
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView = itemView.findViewById(R.id.note_title) as TextView

        fun bind(item: Note, position: Int, listener: (Note, Int) -> Unit) = with(itemView){
            val noteView = itemView.findViewById<CardView>(R.id.card_view)
            noteView.setOnClickListener{
                listener(item, position)
            }
        }
    }
}