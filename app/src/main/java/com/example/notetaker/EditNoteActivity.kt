package com.example.notetaker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.core.text.set
import kotlinx.android.synthetic.main.note_form.*

class EditNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        val note = this.intent.getSerializableExtra("note") as Note
        note_body.setText(note.body)

        btn_cancel_add_note.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btn_confirm_add_note.setOnClickListener {
            note.body = with(note_body) { text.toString() }
            val position = this.intent.getIntExtra("position", -1)
            if (position != -1){
                NoteRepository.updateNote(position, note, this)
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
