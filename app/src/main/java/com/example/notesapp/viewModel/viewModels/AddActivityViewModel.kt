package com.example.notesapp.viewModel.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesapp.model.entities.EntityNotes
import com.example.notesapp.viewModel.repositories.NotesRepository

class AddActivityViewModel(private val repository: NotesRepository) : ViewModel() {

    val tagText = MutableLiveData<String>()
    val descriptionText = MutableLiveData<String>()

    fun saveNoteToDatabase(note: EntityNotes) {
        repository.insertNote(note)
    }
}