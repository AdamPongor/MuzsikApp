package hu.bme.aut.android.muzsikapp.logic

import hu.bme.aut.android.muzsikapp.data.Scale
import hu.bme.aut.android.muzsikapp.data.ScaleList

object ScaleConverter {
    fun ToScale(scString: String): Scale {
        val splitString = scString.split(" ")
        val startingNote = NoteConverter.ToNumber(splitString.first())
        val name = splitString.subList(1,splitString.lastIndex+1).joinToString(" ")
        lateinit var converted: Scale
        var Modeindex: Int = 0
        for (s in ScaleList.scaleList){
            if (s.name == name){
                converted = s

            } else if (s.modes.contains(name)){
                    converted = s
                    Modeindex = s.modes.indexOf(name)
            }
        }
        converted.offset = startingNote + 12 - converted.notes[Modeindex]
        return converted
    }

    fun toScale(pos: Int): Scale{
        return ScaleList.scaleList.get(pos)
    }
}