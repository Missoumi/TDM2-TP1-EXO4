package com.example.exo4

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

}

@Entity
class Note {
    @PrimaryKey
    var id: Int = 0

    var title : String = ""
    var date: Date = Date()
    var couleur : String = ""
    var content : String = ""

}
