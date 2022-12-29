package hu.bme.aut.android.muzsikapp.data

data class Chord(
    var shortName: String,
    var coolName: String,
    var longName: String,
    var notes: List<Int>,
)
