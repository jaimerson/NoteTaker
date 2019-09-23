package com.example.notetaker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.note_form.*

class CreateNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        btn_cancel_add_note.setOnClickListener {
            goToMainActivity()
        }

        btn_confirm_add_note.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val input = EditText(this)
            builder.setTitle("Create note")
            builder.setPositiveButton("OK") { dialog, _ ->
                val title = input.text.toString()
                if (title.isNotEmpty()){
                    val note = Note(title, note_body.text.toString(), -1)
                    NoteRepository.addNote(note)
                    goToMainActivity()
                } else {
                    dialog.cancel()
                }
            }
            builder.setNegativeButton("Cancel") { dialog, _ ->
               dialog.cancel()
            }
            builder.setView(input)
            builder.create().show()
        }
    }

    private fun goToMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
