package com.example.notesapp.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.model.dao.NotesDao
import com.example.notesapp.model.entities.EntityNotes

@Database(entities = [EntityNotes::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

    companion object{
        fun getDatabase(context: Context): NotesDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                NotesDatabase::class.java,
                "notes-database.db"
            ).build()
        }
    }

}
