package hu.bme.aut.android.muzsikapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.muzsikapp.data.Tuning
import hu.bme.aut.android.muzsikapp.databinding.TuningBinding
import hu.bme.aut.android.muzsikapp.fragments.NewTuningDialogFragment

class TuningAdapter(private val listener: TuningClickListener) :
    RecyclerView.Adapter<TuningAdapter.TuningViewHolder>() {

    private val items = mutableListOf<Tuning>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TuningViewHolder(
        TuningBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: TuningViewHolder, position: Int) {
        val tuning = items[position]
        holder.binding.root.setOnClickListener { listener.onItemSelected(tuning) }
        holder.binding.tvName.text = tuning.name
        holder.binding.tvStrings.text = tuning.strings
        holder.binding.tvCategory.text = tuning.category.name
        holder.binding.ibEdit.setOnClickListener {
            listener.onItemEdit(tuning)
        }
        holder.binding.ibRemove.setOnClickListener{
            listener.onItemDeleted(tuning)
            notifyDataSetChanged()
        }

    }

    fun addItem(item: Tuning) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun removeItem(item: Tuning){
        items.remove(item)
        notifyItemRemoved(items.indexOf(item))
    }

    fun updateItem(item: Tuning){
        notifyDataSetChanged()
    }

    fun update(tunings: List<Tuning>) {
        items.clear()
        items.addAll(tunings)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    interface TuningClickListener {
        fun onItemChanged(item: Tuning)
        fun onItemDeleted(item: Tuning)
        fun onItemEdit(item: Tuning)
        fun onItemSelected(item: Tuning)
    }

    inner class TuningViewHolder(val binding: TuningBinding) : RecyclerView.ViewHolder(binding.root)
}