package com.example.notetaker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import kotlin.collections.ArrayList
import kotlinx.serialization.json.JSON
import kotlinx.serialization.list

class NoteRepository {
    companion object {
        var notesList: ArrayList<Note> = ArrayList()

        fun retrieveAll(activity: AppCompatActivity): ArrayList<Note> {
            val sharedPref = activity.getSharedPreferences("notes_repository", Context.MODE_PRIVATE)
            val rawNotes = sharedPref.getString("notes", "[]")
            this.notesList = ArrayList(JSON.parse(Note.serializer().list, rawNotes!!))
            return this.notesList
        }

        fun persistAll(activity: AppCompatActivity) {
            val rawNotes = JSON.stringify(Note.serializer().list, this.notesList)
            val sharedPref = activity.getSharedPreferences("notes_repository", Context.MODE_PRIVATE)
            with(sharedPref.edit()){
                putString("notes", rawNotes)
                apply()
            }
        }

        fun addNote(note: Note, activity: AppCompatActivity) {
            this.notesList.add(note)
            persistAll(activity)
        }

        fun removeNote(activity: AppCompatActivity, position: Int) {
            this.notesList.removeAt(position)
            persistAll(activity)
        }

        fun updateNote(position: Int, note: Note, activity: AppCompatActivity) {
            this.notesList[position] = note
            persistAll(activity)
        }
    }
}