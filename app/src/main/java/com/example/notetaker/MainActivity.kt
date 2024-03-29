package com.example.notetaker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val noteListView = findViewById<RecyclerView>(R.id.recycler_view)
        noteListView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        NoteRepository.retrieveAll(this)

        val onClickListener: (Note, Int) -> Unit = { note, position ->
            val intent = Intent(this, EditNoteActivity::class.java)
            intent.putExtra("note", note)
            intent.putExtra("position", position)
            startActivity(intent)
        }
        val onLongClickListener: (Note, Int) -> Unit = { _, position ->
            NoteRepository.removeNote(this, position)
            Toast.makeText(this, "Note removed", Toast.LENGTH_SHORT).show()
            finish()
            startActivity(intent)
        }
        val adapter = NoteAdapter(NoteRepository.notesList, onClickListener, onLongClickListener)
        noteListView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.btn_add_note -> {
                val intent = Intent(this, CreateNoteActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
