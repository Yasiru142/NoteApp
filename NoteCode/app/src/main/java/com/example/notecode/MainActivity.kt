package com.example.notecode

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notecode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: Notedatabase
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Notedatabase(this)
        noteAdapter = NoteAdapter(db.getAllNotes(), this)

        binding.noteRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.noteRecyclerView.adapter = noteAdapter

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddNote::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        noteAdapter.refreshData(db.getAllNotes())
    }
}
