package net.raj.testnoteapp.Repository

import androidx.lifecycle.LiveData
import net.raj.testnoteapp.Database.Note
import net.raj.testnoteapp.Database.NoteDao

class NoteRepository(private val noteDao : NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.getAll()

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }

}