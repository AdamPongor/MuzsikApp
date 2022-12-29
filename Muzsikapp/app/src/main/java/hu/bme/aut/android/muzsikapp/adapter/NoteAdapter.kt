package hu.bme.aut.android.muzsikapp.adapter

import android.graphics.Color
import android.graphics.Color.parseColor
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.muzsikapp.R
import hu.bme.aut.android.muzsikapp.data.Note
import hu.bme.aut.android.muzsikapp.logic.NoteConverter
import hu.bme.aut.android.muzsikapp.databinding.NoteButtonBinding
import hu.bme.aut.android.muzsikapp.logic.SearchManager

class NoteAdapter() : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private val notes: MutableList<Note> = ArrayList()
    private val buttons: MutableList<Button> = ArrayList()
    private lateinit var sa: StringAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NoteViewHolder(
        NoteButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        buttons.add(holder.binding.btnNote)
        holder.binding.btnNote.text = note.note

        holder.binding.btnNote.setOnClickListener{
            if (note.Selected){
                if (note.Root){
                    holder.binding.btnNote.setBackgroundColor(ContextCompat.getColor(holder.binding.btnNote.context, R.color.RootColor))
                } else if (note.inScale){
                    holder.binding.btnNote.setBackgroundColor(ContextCompat.getColor(holder.binding.btnNote.context, R.color.ScaleColor))
                } else {
                    holder.binding.btnNote.setBackgroundColor(ContextCompat.getColor(holder.binding.btnNote.context, R.color.DefaultColor))
                }
                SearchManager.RemoveNote(NoteConverter.ToNumber(note.note))
                note.Selected = false
            } else {
                holder.binding.btnNote.setBackgroundColor(ContextCompat.getColor(holder.binding.btnNote.context, R.color.SelectedColor))
                SearchManager.AddNote(NoteConverter.ToNumber(note.note))
                note.Selected = true
            }
        }

        holder.binding.btnNote.setOnLongClickListener {
            if (note.Selected){
                if (note.Root){
                    sa.colorAllNotes(ContextCompat.getColor(holder.binding.btnNote.context, R.color.RootColor), note.note, false)
                } else if (note.inScale){
                    sa.colorAllNotes(ContextCompat.getColor(holder.binding.btnNote.context, R.color.ScaleColor), note.note, false)
                } else {
                    sa.colorAllNotes(ContextCompat.getColor(holder.binding.btnNote.context, R.color.DefaultColor), note.note, false)
                }
                SearchManager.RemoveNote(NoteConverter.ToNumber(note.note))
            } else {
                sa.colorAllNotes(ContextCompat.getColor(holder.binding.btnNote.context, R.color.SelectedColor), note.note, true)
                SearchManager.AddNote(NoteConverter.ToNumber(note.note))
            }
            return@setOnLongClickListener true
        }
    }

    fun SetStringAdapter(sa: StringAdapter){
        this.sa = sa
    }

    fun getPosition(note: Note?) : Int {
        return notes.indexOf(note)
    }

    fun getNote(pos: Int) : Note {
        return notes.get(pos)
    }

    override fun getItemCount(): Int = notes.size

    fun addNote(newNote: Note) {
        notes.add(newNote)
        notifyItemInserted(notes.size - 1)
    }

    fun removeNote(position: Int) {
        notes.removeAt(position)
        notifyItemRemoved(position)
        if (position < notes.size) {
            notifyItemRangeChanged(position, notes.size - position)
        }
    }

    inner class NoteViewHolder(val binding: NoteButtonBinding) : RecyclerView.ViewHolder(binding.root){

    }
}