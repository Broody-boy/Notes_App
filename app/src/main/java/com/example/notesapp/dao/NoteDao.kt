package com.example.notesapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notesapp.entities.Notes

@Dao
interface NoteDao {

    @get : Query("SELECT * FROM notes ORDER BY id DESC")
    val allNotes : List<Notes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(note:Notes) //add suspend keyword if you want to use in coroutine scope

    @Delete
    suspend fun deleteNote(note : Notes) //add suspend keyword if you want to use in coroutine scope
}