package com.example.notesapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.notesapp.model.entities.EntityNotes

@Dao
interface NotesDao {

//    @Query("SELECT * FROM notes-table")
//    fun getAllNotes(): List<EntityNotes>
//
//    @Query("SELECT * FROM notes-table WHERE id = :id")
//    fun getNoteById(id: Int): EntityNotes?

    @Insert
    fun insertNote(note: EntityNotes)

//    @Update
//    fun updateNote(note: EntityNotes)
//
//    @Delete
//    fun deleteNote(note: EntityNotes)

}