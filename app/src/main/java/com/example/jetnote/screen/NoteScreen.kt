package com.example.jetnote.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnote.components.MyButton
import com.example.jetnote.components.MyTextField
import com.example.jetnote.data.NotesDataSource
import com.example.jetnote.model.Note
import java.time.format.DateTimeFormatter


@Composable
fun NoteScreen(
notes : List<Note>,
onAddNote: (Note) -> Unit,
onRemoveNote: (Note) -> Unit
){
    Column(modifier = Modifier.padding(6.dp))
    {
        TopAppBar(
            title = { Text(text = "JetNote") },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Icon" )
            },
            backgroundColor = Color(0xFFD8D3D3)
        //Content
        )

        Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally){
            var title by remember { mutableStateOf("") }
            var description by remember { mutableStateOf("") }
            MyTextField(
                value = title,
                onValueChange = { newValue ->
                    title = newValue
                },
                label = "Title",
                placeholder = "Type here"
            )
            MyTextField(
                value = description,
                onValueChange = { newValue ->
                    description = newValue
                },
                label = "Add a note",
                placeholder = "Type here"
            )
            MyButton(
                onClick = {
                          if (title.isNotEmpty() && description.isNotEmpty()){
                              onAddNote(Note(title = title, description = description))
                              title = ""
                              description = ""
                          }
                          },
                buttonText = "Save"
            )
        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn{
            items(notes){note ->
                 NoteRow(note = note, onNoteClicked = {
                     onRemoveNote(note)
                 })

            }
        }
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
){
    Surface(modifier = Modifier
        .padding(4.dp)
        .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
        .fillMaxWidth(),
        color = Color(0xFFA5B2BD),
        elevation = 6.dp
    ) {
        Column(
            modifier
                .clickable {
                    onNoteClicked(note)
                }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {
Text(text = note.title, style = MaterialTheme.typography.subtitle2)
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
           // Text(text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE,d MMM")),
           // style = MaterialTheme.typography.caption)
        }
    }
}



