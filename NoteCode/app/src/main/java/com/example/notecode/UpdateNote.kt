package com.example.notecode

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notecode.databinding.ActivityAddNoteBinding

class UpdateNote : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: Notedatabase
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Notedatabase(this)
        noteId = intent.getIntExtra("note_id", -1)

        if (noteId == -1) {
            finish()
            return
        }

        val note = db.getNoteById(noteId)
        binding.titleEditText.setText(note.title)
        binding.ContentEditText.setText(note.content)

        binding.saveButton.setOnClickListener {
            val newTitle = binding.titleEditText.text.toString()
            val newContent = binding.ContentEditText.text.toString()
            val updatedNote = Note(noteId, newTitle, newContent)
            db.updateNote(updatedNote)
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
        }
    }
}
