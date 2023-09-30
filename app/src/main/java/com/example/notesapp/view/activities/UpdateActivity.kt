package com.example.notesapp.view.activities

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import com.example.notesapp.R
import com.example.notesapp.model.dao.NotesDao
import com.example.notesapp.model.database.NotesDatabase
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {
    private lateinit var notesDao: NotesDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)


        val itemId = intent.extras?.getString("item_id")

        val notesDatabase = NotesDatabase.getDatabase(this)

        notesDao = notesDatabase.getNotesDao()

        val editTextTag = findViewById<EditText>(R.id.update_editTextTag)
        val editTextText = findViewById<EditText>(R.id.update_editTextText)
        val linearLayout = findViewById<LinearLayout>(R.id.update_linearLayout)

        linearLayout.setOnClickListener {
            editTextText.requestFocus()
            showKeyBoard()
        }

        AsyncTask.execute {
            val note = notesDao.getNoteById(itemId)

            // есть ли заметка с данным ID в базе данных
            if (note != null) {
                // Взятие значения "tag" и "description" из заметки
                val tag = note.tag
                val description = note.description

                // Обновление значения полей EditText в основном потоке
                runOnUiThread {
                    editTextTag.setText(tag)
                    editTextText.setText(description)
                }
            }
        }
    }

    fun showKeyBoard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(update_editTextText, InputMethodManager.SHOW_IMPLICIT)
    }
}