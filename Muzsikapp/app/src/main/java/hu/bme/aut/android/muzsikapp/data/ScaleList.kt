package hu.bme.aut.android.muzsikapp.data

object ScaleList {
    var major = Scale(listOf<Int>(0,2,4,5,7,9,11), "Major", 0,
        listOf<String>("Ionian (Major)", "Dorian", "Phrygian", "Lydian", "Mixolydian", "Aeolian (Minor)","Locrian"),
        listOf<String>("I","ii","iii","IV","V","vi","vii°"),
        listOf<Chord>(ChordList.major,ChordList.minor,ChordList.minor,ChordList.major,ChordList.major,ChordList.minor,ChordList.diminished),
        listOf<Chord>(ChordList.major7th,ChordList.minor7th,ChordList.minor7th,ChordList.major7th,ChordList.dominant7th,ChordList.minor7th,ChordList.halfdiminished7th))

    var harmonicMinor = Scale(listOf<Int>(0,2,3,5,7,8,11), "Harmonic Minor", 0,
        listOf<String>("Harmonic Minor", "Locrian ♮6", "Ionian ♯5", "Altered Dorian", "Phrygian Dominant", "Lydian ♯2","Super Locrian ♭♭7"),
        listOf<String>("i","ii°","III+","iv","V","VI","vii°"),
        listOf<Chord>(ChordList.minor,ChordList.diminished,ChordList.augmented,ChordList.minor,ChordList.major,ChordList.major,ChordList.diminished),
        listOf<Chord>(ChordList.minormajor7th,ChordList.halfdiminished7th,ChordList.augmentedmajor7th,ChordList.minor7th,ChordList.dominant7th,ChordList.major7th,ChordList.diminished7th))

    var harmonicMajor = Scale(listOf<Int>(0,1,4,5,7,8,10), "Harmonic Major", 0,
        listOf<String>("Harmonic Major", "Dorian ♭5", "Phrygian ♭4", "Lydian ♭3", "Mixolydian ♭2", "Lydian Augmented ♯2","Locrian ♭♭7"),
        listOf<String>("I","ii°","iii","iv","V","VI+","vii°"),
        listOf<Chord>(ChordList.major,ChordList.diminished,ChordList.minor,ChordList.minor,ChordList.major,ChordList.augmented,ChordList.diminished),
        listOf<Chord>(ChordList.major7th,ChordList.halfdiminished7th,ChordList.minor7th,ChordList.major7th,ChordList.dominant7th,ChordList.augmentedmajor7th,ChordList.diminished7th))

    var melodicMinor = Scale(listOf<Int>(0,2,3,5,7,9,11), "Melodic Minor", 0,
        listOf<String>("Ascending Melodic Minor","Phrygian ♯6","Lydian ♯5","Mixolydian ♯4","Melodic major","Locrian ♯2","Super Locrian"),
        listOf<String>("i","ii","III+","IV","V","vi°","vii°"),
        listOf<Chord>(ChordList.minor,ChordList.minor,ChordList.augmented,ChordList.major,ChordList.major,ChordList.diminished,ChordList.diminished),
        listOf<Chord>(ChordList.minormajor7th,ChordList.minor7th,ChordList.augmentedmajor7th,ChordList.dominant7th,ChordList.dominant7th,ChordList.halfdiminished7th,ChordList.halfdiminished7th))

    var minorPentatonic = Scale(listOf<Int>(0,3,5,7,10), "Minor Pentatonic", 0,
        listOf<String>("Minor Pentatonic", "Major Pentatonic", "Suspended Pentatonic","Blues Minor","Blues Major"),
        listOf<String>("i","III","iv","v","VII"),
        listOf<Chord>(ChordList.minor,ChordList.major,ChordList.minor,ChordList.minor,ChordList.major),
        listOf<Chord>(ChordList.minor7th,ChordList.major7th,ChordList.minor7th,ChordList.minor7th,ChordList.major7th))

    var doubleHarmonicMajor = Scale(listOf<Int>(0,1,4,5,7,8,11), "Double Harmonic Major", 0,
        listOf<String>("Double Harmonic Major","Lydian #2 #6","Ultraphrygian","Hungarian Minor","Oriental","Ionian ♯2 ♯5","Locrian ♭♭3 ♭♭7"),
        listOf<String>("I","II","iii","iv","V°","VI+","vii°"),
        listOf<Chord>(ChordList.major,ChordList.major,ChordList.minor,ChordList.minor,ChordList.majorflat5,ChordList.augmented,ChordList.suspended2ndflat5),
        listOf<Chord>(ChordList.major7th,ChordList.major7th,ChordList.minoradd6,ChordList.minormajor7th,ChordList.dominant7thflat5,ChordList.augmentedmajor7th,ChordList.diminished7thsuspended2nd))

    var persian = Scale(listOf<Int>(0,1,4,5,6,8,11), "Persian", 0,
        listOf<String>("Persian","Ionian ♯2 ♯6","Ultraphrygian ♭♭3","Todi Thaat","Lydian ♯3 ♯6","Mixolydian Augmented ♯2","Chromatic Hypophrygian Inverse"),
        listOf<String>("I°","II","iiiඞ","iv","Vඞ","VI+","vii°"),
        listOf<Chord>(ChordList.majorflat5,ChordList.major,ChordList.suspended2nd,ChordList.minor,ChordList.suspended4th,ChordList.augmented,ChordList.suspended2ndflat5),
        listOf<Chord>(ChordList.major7thflat5,ChordList.major7th,ChordList.sixthsuspended2nd,ChordList.minormajor7th,ChordList.major7thsuspended4th,ChordList.augmented7th,ChordList.diminished7thsuspended2nd))

    var neopolitanMinor = Scale(listOf<Int>(0,1,3,5,7,8,11), "Neapolitan Minor", 0,
        listOf<String>("Neapolitan Minor","Lydian ♯6","Mixolydian Augmented","Romani Minor","Locrian Dominant","Ionian ♯2","Ultralocrian"),
        listOf<String>("i","II","III+","iv","V°","VI","vii°"),
        listOf<Chord>(ChordList.minor,ChordList.major,ChordList.augmented,ChordList.minor,ChordList.majorflat5,ChordList.major,ChordList.suspended2ndflat5),
        listOf<Chord>(ChordList.minormajor7th,ChordList.major7th,ChordList.augmented7th,ChordList.minor7th,ChordList.dominant7thflat5,ChordList.major7th,ChordList.diminished7thsuspended2nd))

    var neopolitanMajor = Scale(listOf<Int>(0,1,3,5,7,9,11), "Neapolitan Major", 0,
        listOf<String>("Neapolitan Major","Lydian Augmented ♯6","Lydian Augmented Dominant","Lydian Dominant ♭6","Major Locrian","Half-Diminished ♭4","Altered Dominant ♭♭3"),
        listOf<String>("i","II+","III+","IV","V°","vi°","vii°"),
        listOf<Chord>(ChordList.minor,ChordList.augmented,ChordList.augmented,ChordList.major,ChordList.majorflat5,ChordList.diminished,ChordList.suspended2ndflat5),
        listOf<Chord>(ChordList.minormajor7th,ChordList.augmentedmajor7th,ChordList.augmented7th,ChordList.dominant7th,ChordList.dominant7thflat5,ChordList.halfdiminished7th,ChordList.halfdiminished7thsuspended2nd))

    var hirajoshi = Scale(listOf<Int>(0,2,3,7,8), "Hirajoshi", 0,
        listOf<String>("Hirajoshi","Iwato","Raga Bhinna Shadja","In","Raga Amritavarshini"),
        listOf<String>("","","","",""),
        listOf<Chord>(),
        listOf<Chord>())

    var wholeTone = Scale(listOf<Int>(0,2,4,6,8,10), "Whole-Tone", 0,
        listOf<String>("Whole-Tone","Whole-Tone","Whole-Tone","Whole-Tone","Whole-Tone","Whole-Tone"),
        listOf<String>("","","","","",""),
        listOf<Chord>(),
        listOf<Chord>())

    var octatonic = Scale(listOf<Int>(0,2,3,5,6,8,9,11), "Octatonic", 0,
        listOf<String>("Whole-Tone Half-Tone","Half-Tone Whole-Tone","Whole-Tone Half-Tone","Half-Tone Whole-Tone","Whole-Tone Half-Tone","Half-Tone Whole-Tone","Whole-Tone Half-Tone", "Half-Tone Whole-Tone"),
        listOf<String>("","","","","","",""),
        listOf<Chord>(),
        listOf<Chord>())

    /*var name = Scale(listOf<Int>(0,,,,,,), "", 0,
        listOf<String>("","","","","","",""),
        listOf<String>("","","","","","",""),
        listOf<Chord>("","","","","","",""),
        listOf<Chord>("","","","","","",""))*/

    var scaleList = mutableListOf<Scale>(major, harmonicMinor, harmonicMajor, melodicMinor, minorPentatonic, doubleHarmonicMajor, persian, neopolitanMinor, neopolitanMajor, hirajoshi,
        wholeTone, octatonic)

    fun GetNameArray(): Array<String>{
        var scales = mutableListOf<String>()
        for (s in scaleList){
            scales.add(s.name)
        }
        return scales.toTypedArray()
    }

    fun GetModes(scaleName: String): Array<String>{
        for (s in scaleList){
            if (s.name == scaleName){
                return s.modes.toTypedArray()
            }
        }
        return arrayOf()
    }
}