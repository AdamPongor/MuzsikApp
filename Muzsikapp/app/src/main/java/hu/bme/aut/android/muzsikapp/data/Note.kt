package hu.bme.aut.android.muzsikapp.data

data class Note (
    var note: String = "A",
    var inScale: Boolean = false,
    var Selected: Boolean = false,
    var Root: Boolean = false
)