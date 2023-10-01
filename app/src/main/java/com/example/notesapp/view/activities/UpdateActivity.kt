package com.example.notesapp.view.activities

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import com.example.notesapp.R
import com.example.notesapp.model.dao.NotesDao
import com.example.notesapp.model.database.NotesDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
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
        val back_Button = findViewById<FloatingActionButton>(R.id.update_back_Button)
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.update_deleteActionButton)

        linearLayout.setOnClickListener {
            editTextText.requestFocus()
            showKeyBoard()
        }

        floatingActionButton.setOnClickListener {
            if (itemId != null) {
                AsyncTask.execute {
                    val note = notesDao.getNoteById(itemId)

                    // Check if the note with the given ID exists in the database
                    if (note != null) {
                        // Delete the note from the database
                        notesDao.deleteNote(note)
                    }
                }

                // Go back to the previous activity
                finish()
            }
        }

        back_Button.setOnClickListener {
            val updatedTag = editTextTag.text.toString()
            val updatedDescription = editTextText.text.toString()

            if (itemId != null) {
                AsyncTask.execute {
                    val note = notesDao.getNoteById(itemId)

                    // Проверка наличия заметки с данным ID в базе данных
                    if (note != null) {
                        // Обновление значений полей в объекте заметки
                        note.tag = updatedTag
                        note.description = updatedDescription

                        // Вызов метода обновления записи в базе данных
                        notesDao.updateNote(note)
                    }

                    // Возвращение к предыдущей активности
                    finish()
                }
            }
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