package com.example.exo4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.room.Room
import java.sql.Date

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var db = Room.databaseBuilder(applicationContext, AppDB::class.java, "Intervention").fallbackToDestructiveMigration().allowMainThreadQueries().build()
        var notesLayout = findViewById<LinearLayout>(R.id.notesLayout)
        var note = Note()
        note.title = "premier note"
        note.content = "premier content test content note de note nof d"
        note.couleur = "#32CD32"
        note.date = Date(100,1,12)
        note.id = 2

        Thread{
//            db.noteDao().saveNote(note)
            val notes = db.noteDao().allNotes()
            this@MainActivity.runOnUiThread(java.lang.Runnable {
                notes.forEach {note ->
                    notesLayout.addView(
                        NoteView(title = note.title,
                            content = note.content,
                            couleur = note.couleur,
                            date = note.date,
                            context = this
                        )
                    )
                }
            })

        }.start()


    }

}
