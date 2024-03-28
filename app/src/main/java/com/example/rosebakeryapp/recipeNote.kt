package com.example.rosebakeryapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

class RecipeNote : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val notes = listOf(
                Note("1", "Note 1", "Content of note 1"),
                Note("2", "Note 2", "Content of note 2"),
                Note("3", "Note 3", "Content of note 3")
            )
            MyApp(notes)
        }
    }
}

data class Note(val id: String, val title: String, val content: String)

@Composable
fun NoteItem(note: Note, onDeleteClick: (String) -> Unit, onEditClick: (Note) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier

                .padding(16.dp)
        ) { // Row to display edit and delete icons
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = { onEditClick(note) },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                }
                IconButton(
                    onClick = { onDeleteClick(note.id) },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
            Text(text = note.title, style = MaterialTheme.typography.headlineMedium)
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )


        }
    }
}

@Composable
fun NoteList(
    notes: List<Note>,
    onDeleteNoteClick: (String) -> Unit,
    onEditNoteClick: (Note) -> Unit
) {
    Column {
        Spacer(modifier = Modifier.height(56.dp)) // Spacer to leave space for the TopAppBar
        LazyColumn {
            items(notes) { note ->
                NoteItem(
                    note = note,
                    onDeleteClick = onDeleteNoteClick,
                    onEditClick = onEditNoteClick
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp(initialNotes: List<Note>) {
    var notes by remember { mutableStateOf(initialNotes) }
    var isAddDialogOpen by remember { mutableStateOf(false) }
    var isEditDialogOpen by remember { mutableStateOf(false) }
    var newNoteTitle by remember { mutableStateOf("") }
    var newNoteContent by remember { mutableStateOf("") }
    var editNoteId by remember { mutableStateOf("") }
    var editNoteTitle by remember { mutableStateOf("") }
    var editNoteContent by remember { mutableStateOf("") }
    var isDeleteDialogOpen by remember { mutableStateOf(false) }
    var deletingNoteId by remember { mutableStateOf("") }

    fun onAddNote() {
        // Create a new list containing the previous elements plus the new element
        val newId = (notes.maxByOrNull { it.id.toIntOrNull() ?: 0 }?.id?.toIntOrNull() ?: 0) + 1
        val newNotes = notes.toMutableList() // Convert the original list to mutable
        newNotes.add(
            Note(
                id = newId.toString(),
                title = newNoteTitle,
                content = newNoteContent
            )
        ) // Add the new element
        notes = newNotes.toList() // Assign the new list to the notes variable
        newNoteTitle = ""
        newNoteContent = ""
        isAddDialogOpen = false // Set it to false here at the end of the function
    }

    fun onDeleteNoteWithConfirmation(id: String) {
        // Show the confirmation dialog before deleting the note
        deletingNoteId = id
        isDeleteDialogOpen = true
    }

    fun onDeleteConfirmed() {
        // Delete the note after confirmation
        notes = notes.filter { it.id != deletingNoteId }
        isDeleteDialogOpen = false
    }

    fun onEditNoteClick(note: Note) {
        // Show the note edit dialog with the values of the selected note
        editNoteId = note.id
        editNoteTitle = note.title
        editNoteContent = note.content
        isEditDialogOpen = true
    }

    fun onEditNoteSave() {
        // Update the note in the list with the new values
        val updatedNotes = notes.map {
            if (it.id == editNoteId) {
                it.copy(title = editNoteTitle, content = editNoteContent)
            } else {
                it
            }
        }
        notes = updatedNotes
        isEditDialogOpen = false
    }

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("My Notes") },
                        navigationIcon = {
                            IconButton(
                                onClick = { /* Handle navigation */ }
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.roselogoround),
                                    contentDescription = "Logo",
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                        },

                        actions = {
                            IconButton(onClick = { isAddDialogOpen = true }) {
                                Icon(Icons.Default.Add, contentDescription = "Add Note")
                            }
                        }
                    )
                }
            ) {
                Column {
                    NoteList(
                        notes = notes,
                        onDeleteNoteClick = { id -> onDeleteNoteWithConfirmation(id) },
                        onEditNoteClick = { note -> onEditNoteClick(note) }
                    )

            if (isAddDialogOpen) {
                // Dialog to add a new note
                AlertDialog(
                    onDismissRequest = { isAddDialogOpen = false },
                    properties = DialogProperties(dismissOnBackPress = true)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        TextField(
                            value = newNoteTitle,
                            onValueChange = { newNoteTitle = it },
                            label = { Text("Title") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        TextField(
                            value = newNoteContent,
                            onValueChange = { newNoteContent = it },
                            label = { Text("Content") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Button(
                            onClick = { onAddNote() },
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .align(Alignment.End)
                        ) {
                            Text("Add")
                        }
                    }
                }
            }

            if (isEditDialogOpen) {
                AlertDialog(
                    onDismissRequest = { isEditDialogOpen = false },
                    properties = DialogProperties(dismissOnBackPress = true)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        TextField(
                            value = editNoteTitle,
                            onValueChange = { editNoteTitle = it },
                            label = { Text("Title") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        TextField(
                            value = editNoteContent,
                            onValueChange = { editNoteContent = it },
                            label = { Text("Content") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Button(
                            onClick = { onEditNoteSave() },
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .align(Alignment.End)
                        ) {
                            Text("Save")
                        }
                    }
                }
            }

            if (isDeleteDialogOpen) {
                // Note deletion confirmation dialog
                AlertDialog(
                    onDismissRequest = { isDeleteDialogOpen = false },
                    properties = DialogProperties(dismissOnBackPress = true)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .background(color = Color(0xFFFFFFFF)),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Text("Are you sure you want to delete this note?")
                        Row(
                            modifier = Modifier.padding(top = 16.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(onClick = { onDeleteConfirmed() }) {
                                Text("Yes")
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Button(onClick = { isDeleteDialogOpen = false }) {
                                Text("Cancel")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainApp(notes: List<Note>) {
    MaterialTheme {
        MyApp(notes)
    }
}
@Preview
@Composable
fun PreviewNoteList() {
    val notes = listOf(
        Note("1", "Note 1", "Content of note 1"),
        Note("2", "Note 2", "Content of note 2"),
        Note("3", "Note 3", "Content of note 3")
    )
    MainApp(notes)
}