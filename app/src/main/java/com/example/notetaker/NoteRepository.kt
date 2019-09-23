package com.example.notetaker

import android.app.Application
import android.content.Context
import android.database.Cursor

class NoteRepository {
    companion object {
        private var databaseHelper : DatabaseHelper? = null
        var notesList: ArrayList<Note> = ArrayList()

        fun initialize(context: Context){
            this.databaseHelper = DatabaseHelper(context)
            refresh()
        }

        fun refresh(){
            this.notesList = retrieveAll()
        }

        fun retrieveAll(): ArrayList<Note> {
            val list = ArrayList<Note>()
            val connection = databaseHelper!!.readableDatabase
            val cursor: Cursor = connection.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_NAME} ORDER BY id ASC", null)
            while(cursor.moveToNext()){
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val title = cursor.getString(cursor.getColumnIndex(("title")))
                val body = cursor.getString(cursor.getColumnIndex(("body")))
                list.add(Note(title, body, id))
            }
            cursor.close()
            connection.close()

            return list
        }

        fun addNote(note: Note) {
            val connection = databaseHelper!!.writableDatabase
            connection.execSQL("""
                INSERT INTO ${DatabaseHelper.TABLE_NAME} (title, body)
                VALUES ("${note.title}", "${note.body}");
            """.trimIndent())

            connection.close()
            refresh()
        }

        fun removeNote(note: Note) {
            this.notesList.remove(note)
            val connection = databaseHelper!!.writableDatabase
            connection.execSQL("""
                DELETE FROM "${DatabaseHelper.TABLE_NAME}"
                WHERE id = ${note.id};
            """.trimIndent())

            connection.close()
        }

        fun updateNote(note: Note) {
            val connection = databaseHelper!!.writableDatabase
            connection.execSQL("""
                UPDATE ${DatabaseHelper.TABLE_NAME}
                SET title = "${note.title}", body = "${note.body}"
                WHERE id = ${note.id};
            """.trimIndent())

            connection.close()
            refresh()
        }
    }
}