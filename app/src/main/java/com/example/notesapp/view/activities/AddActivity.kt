package com.example.notesapp.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.widget.NestedScrollView
import com.example.notesapp.R
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    private lateinit var editTextActivityAdd: EditText
    private lateinit var linearLayoutActivityAdd: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        back_Button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        editTextActivityAdd = findViewById(R.id.editTextActivityAdd)
        linearLayoutActivityAdd = findViewById(R.id.linearLayoutActivityAdd)

        linearLayoutActivityAdd.setOnClickListener {
            editTextActivityAdd.requestFocus()
            showKeyBoard()
        }
    }
    fun showKeyBoard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editTextActivityAdd, InputMethodManager.SHOW_IMPLICIT)
    }
}