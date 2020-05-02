package com.example.exo4

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface NoteDAO {

    @Insert
    fun saveNote(note : Note)

    @Update
    fun updateNote(note : Note)
    @Query("SELECT * FROM  Note ")
    fun allNotes(): List<Note>
    @Query("SELECT count(id) FROM Note")
    fun countNote(): Int
}