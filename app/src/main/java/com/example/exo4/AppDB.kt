package com.example.exo4

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database (entities = [(Note::class)], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDB : RoomDatabase() {
    abstract fun noteDao(): NoteDAO
}