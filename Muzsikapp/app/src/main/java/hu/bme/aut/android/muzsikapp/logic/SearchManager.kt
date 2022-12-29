package hu.bme.aut.android.muzsikapp.logic

import android.util.Log
import hu.bme.aut.android.muzsikapp.data.Scale
import hu.bme.aut.android.muzsikapp.data.ScaleList

object SearchManager {
    var notes = mutableListOf<Int>()

    var acceptedScales = mutableListOf<String>()
    var selectedScale: Scale? = null

    fun Search(){
        var toSearch = notes.toSet()
        acceptedScales.clear()
        for (s in ScaleList.scaleList){
            for (o in 0..11){
                s.offset = o
                if (s.Search(toSearch)){
                    acceptedScales.add(NoteConverter.ToNote(s.offset) + " " + s.name)
                }
            }
            s.offset = 0
        }
    }

    fun GetScales(): Array<String> {
        return acceptedScales.toTypedArray()
    }

    fun AddNote(note: Int){
        notes.add(note)
        Log.d("all: ",notes.toString())
        Log.d("set: ",notes.toSet().toString())
    }

    fun RemoveNote(note: Int){
        notes.remove(note)
        Log.d("all: ",notes.toString())
        Log.d("set: ",notes.toSet().toString())}
}