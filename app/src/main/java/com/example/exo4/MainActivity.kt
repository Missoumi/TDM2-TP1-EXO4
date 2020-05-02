package com.example.exo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.room.Room
import java.sql.Date

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var db = Room.databaseBuilder(applicationContext, AppDB::class.java, "Intervention").fallbackToDestructiveMigration().allowMainThreadQueries().build()
        var notesLayout = findViewById<LinearLayout>(R.id.notesLayout)
        Thread{
            val notes = db.noteDao().allNotes()
            this@MainActivity.runOnUiThread(java.lang.Runnable {
                notes.forEach {note ->
                    notesLayout.addView(
                        NoteView(title = note.title,
                            content = note.content,
                            couleur = note.couleur,
                            date = note.date,
                            context = this,
                            _id = note.id
                        )
                    )
                }
            })

        }.start()

        val create_note = findViewById<Button>(R.id.create)

        create_note.setOnClickListener {
            val intent = Intent(this, CreerNote::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        var notesLayout = findViewById<LinearLayout>(R.id.notesLayout)
        notesLayout.removeAllViews()
        var db = Room.databaseBuilder(applicationContext, AppDB::class.java, "Intervention").fallbackToDestructiveMigration().allowMainThreadQueries().build()
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
                            context = this,
                            _id = note.id
                        )
                    )
                }
            })

        }.start()
    }

}
