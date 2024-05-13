package com.example.posapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.posapp.databinding.ItemBinding


class ItemAdapter(private val itemClick: (String) -> Unit) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var items: MutableList<String> = mutableListOf()

    fun setItems(items: List<String>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ItemViewHolder(
        private val binding: ItemBinding,
        val itemClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: String) {
            with(item) {
                itemView.setOnClickListener { itemClick(this) }
                binding.run {
                    txt.text = item
                }
            }

        }
    }

}