package com.example.notetaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val noteListView = findViewById<RecyclerView>(R.id.recycler_view)
        noteListView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val notesList = ArrayList<Note>()
        notesList.add(Note("Note 1", "Dolorem ipsum dolor sit amet consectetur"))
        notesList.add(Note("Note 2", "Dolorem ipsum dolor sit amet consectetur"))
        notesList.add(Note("Note 3", "Dolorem ipsum dolor sit amet consectetur"))
        notesList.add(Note("Note 4", "Dolorem ipsum dolor sit amet consectetur"))
        notesList.add(Note("Note 5", "Dolorem ipsum dolor sit amet consectetur"))

        val adapter = NoteAdapter(notesList)
        noteListView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
}
