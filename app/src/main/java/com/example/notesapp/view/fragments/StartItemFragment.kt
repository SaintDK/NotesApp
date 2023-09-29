package com.example.notesapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notesapp.view.adapters.NotesRecyclerViewAdapter
import com.example.notesapp.R
import com.example.notesapp.model.dao.NotesDao
import com.example.notesapp.model.database.NotesDatabase
import com.example.notesapp.model.entities.EntityNotes
import com.example.notesapp.model.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class StartItemFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
//                val entityNotesList = PlaceholderContent.ITEMS.map { placeholderItem ->
//                    EntityNotes(
////                        id = placeholderItem.id,
//                        tag = placeholderItem.name,
//                        description = placeholderItem.content
//                    )
//                }
                val notesDatabase = NotesDatabase.getDatabase(requireContext())
                val notesDao = notesDatabase.getNotesDao()
                Thread{
                    val entityNotesList = notesDao.getAllNotes().map { note ->
                        EntityNotes(
                            id = note.id,
                            tag = note.tag,
//                            description = note.description
                            description = ""
                        )
                    }
                    adapter = NotesRecyclerViewAdapter(entityNotesList)
                }.start()
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            StartItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}