package hu.bme.aut.android.muzsikapp.logic

object NoteConverter {

    fun ToNote(n: Int) : String{
        var sToReturn = when (n % 12){
            0 -> "A"
            1 -> "A#"
            2 -> "B"
            3 -> "C"
            4 -> "C#"
            5 -> "D"
            6 -> "D#"
            7 -> "E"
            8 -> "F"
            9 -> "F#"
            10 -> "G"
            11 -> "G#"
            else -> "Na hagyjál a mikrotonális szarságaiddal"
        }
        return sToReturn
    }

    fun ToNumber(s: String) : Int{
        var nToReturn = 0
        try {
            when (s) {
                "A", "a" -> nToReturn = 0
                "A#", "a#", "Bb", "bb" -> nToReturn = 1
                "B", "b" -> nToReturn = 2
                "C", "c" -> nToReturn = 3
                "C#", "c#", "Db", "db" -> nToReturn = 4
                "D", "d" -> nToReturn = 5
                "D#", "d#", "Eb", "eb" -> nToReturn = 6
                "E", "e" -> nToReturn = 7
                "F", "f" -> nToReturn = 8
                "F#", "f#", "Gb", "gb" -> nToReturn = 9
                "G", "g" -> nToReturn = 10
                "G#", "g#", "Ab", "ab" -> nToReturn = 11
                else -> throw Exception("Valamit elszúrtál, bro")
            }
        } catch (err: java.lang.Exception){

            throw err
        }
        return nToReturn
    }
}