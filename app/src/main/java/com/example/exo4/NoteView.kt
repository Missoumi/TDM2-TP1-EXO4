package com.example.exo4

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import java.util.*

class NoteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0,
    var title: String ,
    var content : String,
    var date : Date,
    var couleur: String

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
    }
}