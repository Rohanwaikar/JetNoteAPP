package com.example.jetnote.data

import com.example.jetnote.model.Note

class NotesDataSource{
    fun loadnotes(): List<Note>{
        return listOf(
 Note(title = "Hello", description = "Hello Rohan")
        )
    }
}