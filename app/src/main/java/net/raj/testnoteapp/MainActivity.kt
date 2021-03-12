package net.raj.testnoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import net.raj.testnoteapp.Database.Note

import net.raj.testnoteapp.ViewModels.NoteViewModel
import net.raj.testnoteapp.Views.INotesRVAdapter
import net.raj.testnoteapp.Views.NotesRVadapter
import net.raj.testnoteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), INotesRVAdapter {
    private lateinit var binding: ActivityMainBinding

    lateinit var  viewModel : NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = NotesRVadapter(this,this)

        binding.recyclerView.adapter = adapter


        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer { list ->
            list?.let{
                adapter.updateList(it)
            }

        })


    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"Deleted",Toast.LENGTH_LONG).show()
    }

    fun submitData(view: View) {
        var noteText = binding.input.text.toString()
        if( noteText.isNotEmpty() ){
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this,"Inserted",Toast.LENGTH_LONG).show()
        }
    }
}