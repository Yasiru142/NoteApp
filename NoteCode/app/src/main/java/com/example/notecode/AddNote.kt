package com.example.notecode

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notecode.databinding.ActivityAddNoteBinding

class AddNote : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: Notedatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Notedatabase(this)

        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.ContentEditText.text.toString()  // Fixed the EditText ID
            if (title.isNotEmpty() && content.isNotEmpty()) {
                val note = Note(
                    id = 0,  // Assuming 0 is used for auto-generated IDs
                    title = title,
                    content = content
                )
                db.insertNote(note)
                Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()  // Corrected Toast.LENGTH_SHORT
                finish()
            } else {
                Toast.makeText(this, "Please enter both title and content", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
