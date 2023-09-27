package com.example.notesapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddActivityViewModel : ViewModel() {
    val tagText = MutableLiveData<String>()
    val descriptionText = MutableLiveData<String>()
}