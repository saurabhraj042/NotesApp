package net.raj.testnoteapp.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.raj.testnoteapp.Database.Note
import net.raj.testnoteapp.Database.NoteDatabase
import net.raj.testnoteapp.Repository.NoteRepository

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes : LiveData<List<Note>>

    private val repository : NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes

    }

    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(note)
        }
    }

    fun insertNote(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(note)
        }
    }
}