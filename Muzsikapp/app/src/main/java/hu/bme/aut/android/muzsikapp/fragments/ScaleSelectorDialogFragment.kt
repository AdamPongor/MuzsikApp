package hu.bme.aut.android.muzsikapp.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.appcompat.R
import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.muzsikapp.data.ScaleList
import hu.bme.aut.android.muzsikapp.databinding.SelectScaleBinding
import hu.bme.aut.android.muzsikapp.logic.NoteConverter
import hu.bme.aut.android.muzsikapp.logic.ScaleConverter
import hu.bme.aut.android.muzsikapp.logic.SearchManager

class ScaleSelectorDialogFragment : DialogFragment(), OnItemSelectedListener {
    interface ScaleSelectedListener {
        fun onScaleSelected(pickedScale: String, mode: Int)
    }

    private lateinit var listener: ScaleSelectedListener

    private lateinit var binding: SelectScaleBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? ScaleSelectedListener
            ?: throw RuntimeException("Activity must implement the ScaleSelectedListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = SelectScaleBinding.inflate(LayoutInflater.from(context))
        binding.spNote.adapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(hu.bme.aut.android.muzsikapp.R.array.notes)
        )
        binding.spScale.adapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            ScaleList.GetNameArray()
        )

        binding.spScale.onItemSelectedListener = this

        return AlertDialog.Builder(requireContext())
            .setTitle("Select Scale")
            .setView(binding.root)
            .setPositiveButton(hu.bme.aut.android.muzsikapp.R.string.button_ok) { dialogInterface, i ->
                    listener.onScaleSelected(getScale(), binding.spMode.selectedItemPosition)
            }
            .setNegativeButton(hu.bme.aut.android.muzsikapp.R.string.button_cancel, null)
            .create()
    }


    private fun getScale(): String {
        var ScaleString =  NoteConverter.ToNote(binding.spNote.selectedItemPosition) + " " + ScaleConverter.toScale(binding.spScale.selectedItemPosition).modes[binding.spMode.selectedItemPosition]
        var SelectedScale = ScaleConverter.ToScale(ScaleString)
        return NoteConverter.ToNote(SelectedScale.offset) + " " + SelectedScale.name
    }

    companion object {
        const val TAG = "ScaleSelectorDialogFragment"
    }

    override fun onItemSelected(parent: AdapterView<*>?, v: View?, position: Int, id: Long) {
        var listAdapter = ArrayAdapter<String>(this.requireContext(),R.layout.support_simple_spinner_dropdown_item,ScaleList.GetModes(parent?.getItemAtPosition(position).toString()))
        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spMode.adapter =listAdapter
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}
}