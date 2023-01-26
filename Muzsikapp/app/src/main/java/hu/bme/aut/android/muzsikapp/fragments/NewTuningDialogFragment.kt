package hu.bme.aut.android.muzsikapp.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.muzsikapp.R
import hu.bme.aut.android.muzsikapp.data.GuitarString
import hu.bme.aut.android.muzsikapp.data.Tuning
import hu.bme.aut.android.muzsikapp.databinding.DialogNewTuningBinding
import hu.bme.aut.android.muzsikapp.logic.NoteConverter

class NewTuningDialogFragment : DialogFragment() {
    interface NewTuningDialogListener {
        fun onTuningCreated(newItem: Tuning)
        fun onTuningChanged(item: Tuning)
    }

    private lateinit var listener: NewTuningDialogListener

    private lateinit var binding: DialogNewTuningBinding

    private val strings: MutableList<String> = ArrayList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewTuningDialogListener
            ?: throw RuntimeException("Activity must implement the NewTuningDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogNewTuningBinding.inflate(LayoutInflater.from(context))
        binding.spCategory.adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.category_items)
        )

        binding.btnNoteA.setOnClickListener {
            strings.add("A")
            RefreshText()
        }
        binding.btnNoteAx.setOnClickListener {
            strings.add("A#")
            RefreshText()
        }
        binding.btnNoteAx.setOnLongClickListener {
            strings.add("Bb")
            RefreshText()
            true
        }
        binding.btnNoteB.setOnClickListener {
            strings.add("B")
            RefreshText()
        }
        binding.btnNoteC.setOnClickListener {
            strings.add("C")
            RefreshText()
        }
        binding.btnNoteCx.setOnClickListener {
            strings.add("C#")
            RefreshText()
        }
        binding.btnNoteCx.setOnLongClickListener {
            strings.add("Db")
            RefreshText()
            true
        }
        binding.btnNoteD.setOnClickListener {
            strings.add("D")
            RefreshText()
        }
        binding.btnNoteDx.setOnClickListener {
            strings.add("D#")
            RefreshText()
        }
        binding.btnNoteDx.setOnLongClickListener {
            strings.add("Eb")
            RefreshText()
            true
        }
        binding.btnNoteE.setOnClickListener {
            strings.add("E")
            RefreshText()
        }
        binding.btnNoteF.setOnClickListener {
            strings.add("F")
            RefreshText()
        }
        binding.btnNoteFx.setOnClickListener {
            strings.add("F#")
            RefreshText()
        }
        binding.btnNoteFx.setOnLongClickListener {
            strings.add("Gb")
            RefreshText()
            true
        }
        binding.btnNoteG.setOnClickListener {
            strings.add("G")
            RefreshText()
        }
        binding.btnNoteGx.setOnClickListener {
            strings.add("G#")
            RefreshText()
        }
        binding.btnNoteGx.setOnLongClickListener {
            strings.add("Ab")
            RefreshText()
            true
        }

        binding.ibBackSpace.setOnClickListener {
            if(!strings.isEmpty()){
                strings.removeLast()
            }
            RefreshText()
        }
        Log.d("péló: ", arguments?.getString("name").toString())
        if (arguments?.getString("name") == null){
            return AlertDialog.Builder(requireContext())
                .setTitle(R.string.new_tuning)
                .setView(binding.root)
                .setPositiveButton(R.string.button_ok) { dialogInterface, i ->
                    if (isValid()) {
                        listener.onTuningCreated(getTuning())
                    }
                }
                .setNegativeButton(R.string.button_cancel, null)
                .create()
        } else {
            binding.etName.setText(arguments?.getString("name"))
            val ree = arguments?.getString("strings").toString().split(" ")
            for (s: String in ree){
                strings.add(s)
            }
            RefreshText()
            binding.spCategory.setSelection(arguments?.getInt("instrument")!!)
            return AlertDialog.Builder(requireContext())
                .setTitle("Edit tuning")
                .setView(binding.root)
                .setPositiveButton(R.string.button_ok) { dialogInterface, i ->
                    if (isValid()) {
                        var t = getTuning()
                        t.id = arguments?.getLong("id")
                        listener.onTuningChanged(t)}
                }
                .setNegativeButton(R.string.button_cancel, null)
                .create()
        }
    }

    private fun RefreshText(){
        var str= ""
        for (s in strings){
            str = "$str$s "
        }
        binding.etDescription.text = str.dropLast(1)
    }

    private fun isValid(): Boolean {
        try {
            var noteList = binding.etDescription.text.split(" ")
            for (n in noteList){
                NoteConverter.ToNumber(n)
            }
        } catch (e: Exception){
            val toast = Toast.makeText(this@NewTuningDialogFragment.context, "Invalid tuning!", Toast.LENGTH_SHORT)
            toast.show()
            return false
        }
        return true
    }

    private fun getTuning() = Tuning(
        name = binding.etName.text.toString(),
        strings = binding.etDescription.text.toString(),
        category = Tuning.Category.getByOrdinal(binding.spCategory.selectedItemPosition)
            ?: Tuning.Category.Guitar
    )

    companion object {
        const val TAG = "NewTuningDialogFragment"
    }
}