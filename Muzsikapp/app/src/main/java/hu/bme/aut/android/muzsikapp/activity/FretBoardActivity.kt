package hu.bme.aut.android.muzsikapp.activity

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.R
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.muzsikapp.adapter.StringAdapter
import hu.bme.aut.android.muzsikapp.data.GuitarString
import hu.bme.aut.android.muzsikapp.databinding.ActivityFretboardBinding
import hu.bme.aut.android.muzsikapp.fragments.ScaleSelectorDialogFragment
import hu.bme.aut.android.muzsikapp.logic.NoteConverter
import hu.bme.aut.android.muzsikapp.logic.ScaleConverter
import hu.bme.aut.android.muzsikapp.logic.SearchManager

class FretBoardActivity : AppCompatActivity(), ScaleSelectorDialogFragment.ScaleSelectedListener {

    private lateinit var binding: ActivityFretboardBinding
    private var strings: String? = null
    private lateinit var adapter: StringAdapter
    var ModeIndex: Int = 0

    companion object {
        private const val TAG = "FretBoardActivity"
        const val EXTRA_TUNING_NAME = "extra.tuning_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFretboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        strings = intent.getStringExtra(EXTRA_TUNING_NAME)

        initRecyclerView()
        initButtons()

        Reset()
    }

    private fun initButtons(){
        binding.searchButton.setOnClickListener{
            SearchManager.Search()
            var listAdapter = ArrayAdapter(this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,SearchManager.GetScales())
            listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.scalePicker.adapter = listAdapter

            if (SearchManager.acceptedScales.isEmpty()){
                var listAdapter = ArrayAdapter(this@FretBoardActivity,R.layout.support_simple_spinner_dropdown_item, arrayOf<String>() )
                listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.modePicker.adapter = listAdapter
            }
        }

        binding.selectButton.setOnClickListener{
            ScaleSelectorDialogFragment().show(
                supportFragmentManager,
                ScaleSelectorDialogFragment.TAG
            )
        }

        binding.deletButton.setOnClickListener {
            Reset()
        }

        binding.chordButton.setOnClickListener{
            if (SearchManager.selectedScale != null) {
                if(SearchManager.selectedScale!!.notes.size == 7){
                    val showChordsIntent = Intent()
                    showChordsIntent.setClass(this@FretBoardActivity, ChordsActivity::class.java)
                    showChordsIntent.putExtra(ChordsActivity.EXTRA_SCALE_NAME, SearchManager.selectedScale!!.name)
                    showChordsIntent.putExtra(
                        ChordsActivity.EXTRA_BASENOTE_NAME, NoteConverter.ToNote(
                            SearchManager.selectedScale!!.offset))
                    startActivity(showChordsIntent)
                } else {
                    val toast = Toast.makeText(applicationContext, "Only for 7 note scales...", Toast.LENGTH_SHORT)
                    toast.show()
                }
            } else {
                val toast = Toast.makeText(applicationContext, "Select a scale!", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    private fun initRecyclerView() {
        var noteList = strings?.split(" ")


        binding.scalePicker.onItemSelectedListener = this.ScaleSelectorListener()
        binding.modePicker.onItemSelectedListener = this.ModeSelectorListener()

        binding.rvStrings.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = StringAdapter()
        if (noteList != null) {
            for (s in noteList.reversed()){
                adapter.addString(GuitarString(s,24));
            }
        }
        binding.rvStrings.adapter = adapter
    }

    fun Reset(){
        SearchManager.selectedScale = null
        SearchManager.acceptedScales.clear()
        SearchManager.notes.clear()
        var listAdapter = ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, arrayOf<String>() )
        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.scalePicker.adapter = listAdapter
        binding.modePicker.adapter = listAdapter
        adapter.clearButtons()
    }

    override fun onScaleSelected(scale: String, mode: Int) {
        ModeIndex = mode

        var listAdapter = ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, arrayOf<String>(scale) )
        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.scalePicker.adapter = listAdapter
    }

    inner class ScaleSelectorListener: OnItemSelectedListener{


        override fun onNothingSelected(p0: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, v: View?, position: Int, id: Long) {
            SearchManager.selectedScale = ScaleConverter.ToScale(parent?.getItemAtPosition(position).toString())
            adapter.colorButtons(SearchManager.selectedScale!!)
            SearchManager.notes.clear()

            var listAdapter = ArrayAdapter(this@FretBoardActivity,R.layout.support_simple_spinner_dropdown_item,SearchManager.selectedScale!!.GetNotedModes().toTypedArray())
            listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.modePicker.adapter = listAdapter

            binding.modePicker.setSelection(ModeIndex)
            ModeIndex = 0
        }
    }

    inner class ModeSelectorListener: OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, v: View?, position: Int, id: Long) {

            adapter.highLightRoot(SearchManager.selectedScale!!, parent?.getItemAtPosition(position).toString().split(" ")[0])

        }
    }
}