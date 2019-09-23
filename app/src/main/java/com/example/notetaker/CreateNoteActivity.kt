package com.example.notetaker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_note.*
import kotlinx.android.synthetic.main.note_form.*

class CreateNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        btn_cancel_add_note.setOnClickListener {
            goToMainActivity()
        }

        btn_confirm_add_note.setOnClickListener {
            val note = Note("Some note", note_body.text.toString())
            NoteRepository.addNote(note, this)
            goToMainActivity()
        }
    }

    private fun goToMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
