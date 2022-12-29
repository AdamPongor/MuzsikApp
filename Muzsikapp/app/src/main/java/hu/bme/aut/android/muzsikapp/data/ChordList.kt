package hu.bme.aut.android.muzsikapp.data

object ChordList {
//triads
    val major = Chord("M", "maj", " major", listOf<Int>(0,4,7))

    val minor = Chord("m", "min", " minor", listOf<Int>(0,3,7))

    val diminished = Chord("°", "dim", " diminished", listOf<Int>(0,3,6))

    val augmented = Chord("+", "aug", " augmented", listOf<Int>(0,4,8))

//stupid triads
    val majorflat5 = Chord("M♭5", "maj♭5", " major ♭5", listOf<Int>(0,4,6))

    val suspended2nd = Chord("sus2", "sus2", " suspended 2nd", listOf<Int>(0,2,7))

    val suspended4th = Chord("sus4", "sus4", " suspended 4th", listOf<Int>(0,5,7))

    val suspended2ndflat5 = Chord("sus2♭5", "sus2♭5", " suspended 2nd", listOf<Int>(0,2,6))
//tetrachords
    val major7th = Chord("M7", "maj7", " major 7th", listOf<Int>(0,4,7,11))

    val minor7th = Chord("m7", "min7", " minor 7th", listOf<Int>(0,3,7,10))

    val dominant7th = Chord("7", "7", " dominant 7th", listOf<Int>(0,4,7,10))

    val minormajor7th = Chord("mM7", "min/maj7", " minor major 7th", listOf<Int>(0,3,7,11))

    val augmentedmajor7th = Chord("+M7", "aug/maj7", " augmented major 7th", listOf<Int>(0,4,8,11))

    val augmented7th = Chord("+7", "aug7", " augmented 7th", listOf<Int>(0,4,8,10))

    val halfdiminished7th = Chord("∅7", "min7♭5", " half diminished 7th", listOf<Int>(0,3,6,10))

    val diminished7th = Chord("○7", "dim7", " diminished 7th", listOf<Int>(0,3,6,9))

    val dominant7thflat5 = Chord("7♭5", "7♭5", " dominant 7th ♭5", listOf<Int>(0,4,6,10))

//stupid tetrachords
    val major7thflat5 = Chord("M7♭5", "maj7♭5", " major 7th ♭5", listOf<Int>(0,4,6,11))

    val minoradd6 = Chord("m6", "min6", " minor add6", listOf<Int>(0,3,7,9))

    val sixthsuspended2nd = Chord("6sus2", "6sus2", " 6th suspended 2nd", listOf<Int>(0,2,7,9))

    val diminished7thsuspended2nd = Chord("○7sus2", "dim7sus2", " diminished 7th suspended 2nd", listOf<Int>(0,2,6,10))

    val major7thsuspended4th = Chord("M7sus4", "maj7sus4", " major 7th suspended 4th", listOf<Int>(0,5,7,11))

    val halfdiminished7thsuspended2nd = Chord("∅7sus2", "min7♭5sus2", " half diminished 7th suspended 2nd", listOf<Int>(0,))
/*val name = Chord("", "", "", listOf<Int>(0,))*/
}