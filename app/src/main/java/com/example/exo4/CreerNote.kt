package com.example.exo4

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import java.sql.Date
import java.time.LocalDate

class CreerNote : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creer_note)
        var db = Room.databaseBuilder(applicationContext, AppDB::class.java, "Intervention").fallbackToDestructiveMigration().allowMainThreadQueries().build()

        var title = intent.getStringExtra("title")
        var content = intent.getStringExtra("content")
        var color = intent.getStringExtra("color")
        val date = intent.getStringExtra("date")
        val id = intent.getIntExtra("id",db.noteDao().countNote()+4)
        val update = intent.getIntExtra("update", 0)
        val titleInput = findViewById<EditText>(R.id.title)
        titleInput.setText(title)
        val contentInput = findViewById<EditText>(R.id.content)
        contentInput.setText(content)
        val colorInput = findViewById<Button>(R.id.color)
        val createButton = findViewById<Button>(R.id.create)
        titleInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                title = s.toString()
            }
        })
        contentInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                content = s.toString()
            }
        })
        colorInput.setOnClickListener {
            MaterialColorPickerDialog
                .Builder(this)        			// Pass Activity Instance
                .setColorShape(ColorShape.SQAURE) // Pass Default Color
                .setColorListener { col, colorHex ->
                    color = colorHex.toString()
                }
                .show()
        }
        createButton.setOnClickListener {
            var note = Note()
            note.title = title
            note.content = content
            note.couleur = color
            note.date = Date(120, 5, 5)
            note.id = id
            if (update == 1){
                db.noteDao().updateNote(note)
            }else {
                db.noteDao().saveNote(note)
            }

            finish()
        }
    }
}
