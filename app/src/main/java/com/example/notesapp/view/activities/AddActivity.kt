package com.example.notesapp.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.R
import com.example.notesapp.model.database.NotesDatabase
import com.example.notesapp.model.entities.EntityNotes
import com.example.notesapp.viewModel.AddActivityViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {

    private lateinit var editTextActivityAdd: EditText
    private lateinit var linearLayoutActivityAdd: LinearLayout
    private lateinit var back_Button: FloatingActionButton
    private lateinit var viewModel: AddActivityViewModel
    private lateinit var db: NotesDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        db = NotesDatabase.getDatabase(applicationContext)

        editTextActivityAdd = findViewById(R.id.editTextText)
        linearLayoutActivityAdd = findViewById(R.id.linearLayoutActivityAdd)
        back_Button = findViewById(R.id.back_Button)

        viewModel = ViewModelProvider(this).get(AddActivityViewModel::class.java)

        editTextTag.addTextChangedListener {
            viewModel.tagText.value = it.toString()
        }

        editTextText.addTextChangedListener {
            viewModel.descriptionText.value = it.toString()
        }

        linearLayoutActivityAdd.setOnClickListener {
            editTextActivityAdd.requestFocus()
            showKeyBoard()
        }

        back_Button.setOnClickListener {
            saveTextIfNotEmpty()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun showKeyBoard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editTextActivityAdd, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun saveTextIfNotEmpty() {
        val tag = viewModel.tagText.value
        val description = viewModel.descriptionText.value

        if (!tag.isNullOrEmpty() || !description.isNullOrEmpty()) {
            val note = EntityNotes(null, tag = tag.orEmpty(), description = description.orEmpty())
            val coroutineScope = CoroutineScope(Dispatchers.IO)
            coroutineScope.launch {
                db.getNotesDao().insertNote(note)
                Log.d("tag123123", "$tag $description")
                Log.d("tag123123", "$note")
            }
        }
    }
}