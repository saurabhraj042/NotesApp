package net.raj.testnoteapp.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note : Note)

    @Delete
    suspend fun  delete(note: Note)

    @Query("Select  * from note_table order by note ASC")
    fun getAll() : LiveData<List<Note>>
}