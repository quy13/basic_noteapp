package com.example.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.NoteDataBase
import com.example.noteapp.repository.NoteRepository
import com.example.noteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData : LiveData<List<Note>>
     private val repository  : NoteRepository

    //always goes first when UserViewModel is Called
    init {
         val noteDao = NoteDataBase.getDatabase(application).noteDao()
         repository  = NoteRepository(noteDao)
         readAllData = repository.readAllData
     }

    fun addNote(note: Note){
        // viewModelScope is a part of kotlin coroutine, It is better to have DataBase Jobs in background thread
        // launch(Dispatchers.IO) make this run in the background thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }
    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }
    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }
    fun deleteAllNote(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllNote()
        }
    }

}