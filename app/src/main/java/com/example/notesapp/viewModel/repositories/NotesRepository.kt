package com.example.notesapp.viewModel.repositories

import com.example.notesapp.model.dao.NotesDao
import com.example.notesapp.model.entities.EntityNotes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesRepository(private val notesDao: NotesDao) {

    fun insertNote(note: EntityNotes) {
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope.launch {
            notesDao.insertNote(note)
        }
    }
}