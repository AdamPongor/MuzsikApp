package hu.bme.aut.android.muzsikapp.data

import hu.bme.aut.android.muzsikapp.logic.NoteConverter

class Scale(val notes: List<Int>,
            val name: String,
            var offset: Int,
            val modes: List<String>,
            val chordSignatures: List<String>,
            val chords: List<Chord>,
            val seventhChords: List<Chord>
            ) {

    fun Search(list: Set<Int>): Boolean {
        for (i in list){
            if (!this.notes.contains((i + 12 - offset) % 12)){
                return false
            }
        }
        return true
    }

    fun GetNotedModes(): List<String>{
        var list: MutableList<String> = ArrayList()
        for (i in 0..notes.size-1){
            list.add(NoteConverter.ToNote(notes[i] + offset) + " " + modes[i])
        }
        return list
    }
}