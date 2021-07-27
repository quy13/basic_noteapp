package com.example.noteapp.repository

import androidx.lifecycle.LiveData
import com.example.noteapp.data.NoteDao
import com.example.noteapp.model.Note

class NoteRepository(private val noteDao: NoteDao) {
    val readAllData:LiveData<List<Note>> = noteDao.readAllData()

    suspend fun addNote(note: Note){
        noteDao.addNote(note)
    }
    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }
    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }
    suspend fun deleteAllNote(){
        noteDao.deleteAllNote()
    }
}