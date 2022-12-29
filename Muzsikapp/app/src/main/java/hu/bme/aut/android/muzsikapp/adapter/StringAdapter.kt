package hu.bme.aut.android.muzsikapp.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.muzsikapp.R
import hu.bme.aut.android.muzsikapp.data.GuitarString
import hu.bme.aut.android.muzsikapp.data.Note
import hu.bme.aut.android.muzsikapp.data.Scale
import hu.bme.aut.android.muzsikapp.logic.NoteConverter
import hu.bme.aut.android.muzsikapp.databinding.StringBinding
import hu.bme.aut.android.muzsikapp.logic.SearchManager

class StringAdapter() : RecyclerView.Adapter<StringAdapter.StringViewHolder>() {
    private val strings: MutableList<GuitarString> = ArrayList()
    private var noteAdapters: MutableList<NoteAdapter> = ArrayList()
    private var rv: MutableList<RecyclerView> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StringViewHolder(
            StringBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        val string = strings[position]

        var adapter = NoteAdapter()
        adapter.SetStringAdapter(this)
        for (i in 0..string.frets){
            adapter.addNote(Note(NoteConverter.ToNote(NoteConverter.ToNumber(string.openNote) + i)))
        }
        noteAdapters.add(adapter)
        rv.add(holder.binding.rvString)
        holder.binding.rvString.adapter = adapter
        holder.binding.rvString.layoutManager = LinearLayoutManager(holder.binding.root.context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun getPosition(string: GuitarString?) : Int {
        return strings.indexOf(string)
    }

    fun colorAllNotes(color: Int, note: String, selected: Boolean){
        for (rvi in rv) {
            for (i in 0..24){
                var Nbutton = rvi.findViewHolderForAdapterPosition(i)?.itemView?.findViewById<View>(R.id.btnNote) as Button
                if (note == Nbutton?.text) {
                    Nbutton?.setBackgroundColor(color)
                }
            }
        }
        for (na in noteAdapters){
            for (i in 0..24){
                if(na.getNote(i).note == note){
                    na.getNote(i).Selected = selected
                    if(selected){
                        SearchManager.AddNote(NoteConverter.ToNumber(note))
                    } else {
                        SearchManager.RemoveNote(NoteConverter.ToNumber(note))
                    }
                }
            }
        }
    }

    fun clearButtons(){
        for (rvi in rv){
            for (i in 0..24){
                var Nbutton = rvi.findViewHolderForAdapterPosition(i)?.itemView?.findViewById<View>(R.id.btnNote) as Button
                Nbutton?.setBackgroundColor(ContextCompat.getColor(Nbutton.context, R.color.DefaultColor))
            }
        }
        for (na in noteAdapters){
            for (i in 0..24){
                na.getNote(i).Selected = false
                na.getNote(i).inScale = false
                na.getNote(i).Root = false
            }
        }
    }

    fun colorButtons(s: Scale){
        var notesToColor: MutableList<String> = ArrayList()
        for (n in s.notes){
            notesToColor.add(NoteConverter.ToNote(n + s.offset))
        }
        for (rvi in rv) {
            for (i in 0..24){
                var Nbutton = rvi.findViewHolderForAdapterPosition(i)?.itemView?.findViewById<View>(R.id.btnNote) as Button
                if (notesToColor.contains(Nbutton?.text)) {
                    Nbutton?.setBackgroundColor(ContextCompat.getColor(Nbutton.context, R.color.ScaleColor))
                } else {
                    Nbutton?.setBackgroundColor(ContextCompat.getColor(Nbutton.context, R.color.DefaultColor))
                }
            }
        }
        for (na in noteAdapters) {
            for (i in 0..24) {
                na.getNote(i).Selected = false
                na.getNote(i).Root = false
                na.getNote(i).inScale = notesToColor.contains(na.getNote(i).note)
            }
        }
    }

    fun highLightRoot(s: Scale, root: String){
        var notesToColor: MutableList<String> = ArrayList()
        for (n in s.notes){
            notesToColor.add(NoteConverter.ToNote(n + s.offset))
        }
        for (rvi in rv) {
            for (i in 0..24){
                var Nbutton = rvi.findViewHolderForAdapterPosition(i)?.itemView?.findViewById<View>(R.id.btnNote) as Button
                if (notesToColor.contains(Nbutton?.text)) {
                    if (Nbutton?.text == root){
                        Nbutton?.setBackgroundColor(ContextCompat.getColor(Nbutton.context, R.color.RootColor))
                    } else {
                        Nbutton?.setBackgroundColor(ContextCompat.getColor(Nbutton.context, R.color.ScaleColor))
                    }
                }
            }
        }
        for (na in noteAdapters) {
            for (i in 0..24) {
                na.getNote(i).Selected = false
                na.getNote(i).inScale = notesToColor.contains(na.getNote(i).note)
                na.getNote(i).Root = na.getNote(i).note == root
            }
        }
    }

    override fun getItemCount(): Int = strings.size

    fun addString(newString: GuitarString) {
        strings.add(newString)
        notifyItemInserted(strings.size - 1)
    }

    fun removeString(position: Int) {
        strings.removeAt(position)
        notifyItemRemoved(position)
        if (position < strings.size) {
            notifyItemRangeChanged(position, strings.size - position)
        }
    }

    inner class StringViewHolder(val binding: StringBinding) : RecyclerView.ViewHolder(binding.root)
}