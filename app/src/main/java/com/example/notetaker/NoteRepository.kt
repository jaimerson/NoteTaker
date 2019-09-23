package com.example.notetaker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import kotlin.collections.ArrayList
import kotlinx.serialization.json.JSON
import kotlinx.serialization.list

class NoteRepository {
    companion object {
        fun retrieveAll(activity: AppCompatActivity): ArrayList<Note> {
            val sharedPref = activity.getSharedPreferences("notes_repository", Context.MODE_PRIVATE)
            val rawNotes = sharedPref.getString("notes", "[]")
            return ArrayList(JSON.parse(Note.serializer().list, rawNotes!!))
        }

        fun persistAll(activity: AppCompatActivity, notes: ArrayList<Note>) {
            val rawNotes = JSON.stringify(Note.serializer().list, notes)
            val sharedPref = activity.getSharedPreferences("notes_repository", Context.MODE_PRIVATE)
            with(sharedPref.edit()){
                putString("notes", rawNotes)
                apply()
            }
        }

        fun addNote(note: Note, activity: AppCompatActivity) {
            val noteList = retrieveAll(activity)
            noteList.add(note)
            persistAll(activity, noteList)
        }
    }
}