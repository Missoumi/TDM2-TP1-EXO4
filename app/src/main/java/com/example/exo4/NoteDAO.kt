package com.example.exo4

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface NoteDAO {

    @Insert
    fun saveNote(note : Note)

    @Query("SELECT * FROM  Note ")
    fun allNotes(): List<Note>
}