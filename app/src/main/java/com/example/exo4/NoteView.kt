package com.example.exo4

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import java.util.*

class NoteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0,
    var title: String ,
    var content : String,
    var date : Date,
    var couleur: String,
    var _id: Int

) : LinearLayout(context, attrs, defStyle, defStyleRes) {
    init {
        LayoutInflater.from(context).inflate(R.layout.note_view, this, true)
        var titleView = findViewById<TextView>(R.id.title)
        var contentView = findViewById<TextView>(R.id.content)
        var dateView = findViewById<TextView>(R.id.date)
        var card = findViewById<CardView>(R.id.mainCard)
        if (couleur != null) {
            card.setCardBackgroundColor(Color.parseColor(couleur))
        }else {
            card.setCardBackgroundColor(Color.parseColor("#FFFF00"))
        }
        titleView.text = title
        contentView.text = content
        Log.w("date", date.toString())
        dateView.text = date.toString()

        val updateButton = findViewById<Button>(R.id.update)
        updateButton.setOnClickListener {
            val intent = Intent(context, CreerNote::class.java)
            intent.putExtra("title", title)
            intent.putExtra("content", content)
            intent.putExtra("color", couleur)
            intent.putExtra("date", date.toString())
            intent.putExtra("id", _id)
            intent.putExtra("update", 1)
            context.startActivity(intent)
        }
    }
}