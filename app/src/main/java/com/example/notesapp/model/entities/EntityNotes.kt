package com.example.notesapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes-table")
data class EntityNotes(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "tags")
    var tag: String,
    @ColumnInfo(name = "descriptions")
    var description: String,

)
