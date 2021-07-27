package com.example.noteapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteapp.model.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("DELETE FROM note_data")
    suspend fun deleteAllNote()

    //fetch a list From Note entities Then Wrap it with LiveData (Ascending Order)
    @Query("Select * FROM NOTE_DATA ORDER BY id DESC")
    fun readAllData(): LiveData<List<Note>>

}