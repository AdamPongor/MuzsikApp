package hu.bme.aut.android.muzsikapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import hu.bme.aut.android.muzsikapp.data.ChordView
import hu.bme.aut.android.muzsikapp.data.Scale
import hu.bme.aut.android.muzsikapp.databinding.ActivityChordsBinding
import hu.bme.aut.android.muzsikapp.databinding.ChordBinding
import hu.bme.aut.android.muzsikapp.logic.NoteConverter
import hu.bme.aut.android.muzsikapp.logic.ScaleConverter

class ChordsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChordsBinding
    private lateinit var selectedScale: Scale

    companion object {
        private const val TAG = "ChordsActivity"
        const val EXTRA_SCALE_NAME = "extra.scale_name"
        const val EXTRA_BASENOTE_NAME = "extra.basenote_name"
    }

    lateinit var pp: ViewTreeObserver.OnGlobalLayoutListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChordsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ezért vért izzadtam és valószínűleg nem valami szép, de működik
        pp = ViewTreeObserver.OnGlobalLayoutListener() {
            binding.llChords.viewTreeObserver.removeOnGlobalLayoutListener(this.pp)
            addChords()
        }



        binding.llChords.viewTreeObserver.addOnGlobalLayoutListener(pp)

        binding.tvScaleName.text = intent.getStringExtra(EXTRA_BASENOTE_NAME) + " " + intent.getStringExtra(
            EXTRA_SCALE_NAME
        )

        selectedScale = ScaleConverter.ToScale(intent.getStringExtra(EXTRA_BASENOTE_NAME) + " " + intent.getStringExtra(
            EXTRA_SCALE_NAME
        ))

        binding.llChords.measure(0,0)

    }

    fun addChords(){
        for (i in 0..selectedScale.notes.size-1){
            var triadNotes = ""
            for (t in 0..selectedScale.chords[i].notes.size-1){
                triadNotes += NoteConverter.ToNote(selectedScale.chords[i].notes[t] + selectedScale.notes[i]+selectedScale.offset) + "-"
            }
            var tetraNotes = ""
            for (t in 0..selectedScale.seventhChords[i].notes.size-1){
                tetraNotes += NoteConverter.ToNote(selectedScale.seventhChords[i].notes[t] + selectedScale.notes[i]+selectedScale.offset) + "-"
            }
            val pp = ChordView(NoteConverter.ToNote(selectedScale.notes[i]+selectedScale.offset), selectedScale.chordSignatures[i], selectedScale.chords[i].coolName, triadNotes.dropLast(1), selectedScale.seventhChords[i].coolName, tetraNotes.dropLast(1))
            val chordBinding = ChordBinding.inflate(layoutInflater)
            chordBinding.tvSymbol.text = pp.Symbol
            chordBinding.tvTriad.text = pp.Note + pp.Triad
            chordBinding.tvTriadNotes.text = pp.TriadNotes
            chordBinding.tvSeventh.text = pp.Note + pp.Seventh
            chordBinding.tvSeventhNotes.text = pp.SeventhNotes

            chordBinding.root.layoutParams = LinearLayout.LayoutParams(binding.root.width / selectedScale.notes.size, binding.root.height/2)
            binding.llChords.addView(chordBinding.root)
        }
    }
}