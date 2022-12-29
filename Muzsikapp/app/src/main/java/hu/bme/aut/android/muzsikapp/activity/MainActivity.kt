package hu.bme.aut.android.muzsikapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.muzsikapp.adapter.TuningAdapter
import hu.bme.aut.android.muzsikapp.data.Tuning
import hu.bme.aut.android.muzsikapp.data.TuningDatabase
import hu.bme.aut.android.muzsikapp.databinding.ActivityMainBinding
import hu.bme.aut.android.muzsikapp.fragments.NewTuningDialogFragment
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), TuningAdapter.TuningClickListener, NewTuningDialogFragment.NewTuningDialogListener{
    private lateinit var binding: ActivityMainBinding

    private lateinit var database: TuningDatabase
    private lateinit var adapter: TuningAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        database = TuningDatabase.getDatabase(applicationContext)

        binding.fab.setOnClickListener {
            NewTuningDialogFragment().show(
                supportFragmentManager,
                NewTuningDialogFragment.TAG
            )
        }

        initRecyclerView()
    }

    override fun onTuningCreated(newItem: Tuning) {
        thread {
            val insertId = database.tuningDao().insert(newItem)
            newItem.id = insertId
            runOnUiThread {
                adapter.addItem(newItem)
            }
        }
    }

    override fun onTuningChanged(item: Tuning) {
        thread {
            database.tuningDao().update(item)
            val items = database.tuningDao().getAll()
            runOnUiThread {
                adapter.update(items)
            }
        }
    }

    override fun onItemChanged(item: Tuning) {
        thread {
            database.tuningDao().update(item)
        }
    }

    override fun onItemDeleted(item: Tuning) {
        thread {
            database.tuningDao().deleteItem(item)
            adapter.removeItem(item)

        }
    }

    override fun onItemEdit(item: Tuning) {
        var bundle = bundleOf(
            "strings" to item.strings,
            "name" to item.name,
            "instrument" to item.category.ordinal,
            "id" to item.id
        )

        var EditTuningFragment = NewTuningDialogFragment()
        EditTuningFragment.arguments = bundle

        EditTuningFragment.show(
            supportFragmentManager,
            NewTuningDialogFragment.TAG
        )

    }

    override fun onItemSelected(item: Tuning) {
        val showFretBoardIntent = Intent()
        showFretBoardIntent.setClass(this@MainActivity, FretBoardActivity::class.java)
        showFretBoardIntent.putExtra(FretBoardActivity.EXTRA_TUNING_NAME, item.strings)
        startActivity(showFretBoardIntent)
    }

    private fun initRecyclerView() {
        adapter = TuningAdapter(this)
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adapter
        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val items = database.tuningDao().getAll()
            runOnUiThread {
                adapter.update(items)
            }
        }
    }
}