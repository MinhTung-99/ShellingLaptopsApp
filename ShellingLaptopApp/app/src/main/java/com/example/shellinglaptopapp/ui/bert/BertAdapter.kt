package com.example.shellinglaptopapp.ui.bert

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shellinglaptopapp.R
import com.example.shellinglaptopapp.databinding.ItemMessageBinding

class BertAdapter(
    private val messages: ArrayList<String>
) : RecyclerView.Adapter<BertAdapter.BertViewHolder>(){

    class BertViewHolder(
        val itemMessageBinding: ItemMessageBinding
    ) : RecyclerView.ViewHolder(itemMessageBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BertViewHolder =
        BertViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_message,
                parent, false)
        )

    override fun onBindViewHolder(holder: BertViewHolder, position: Int) {
        holder.itemMessageBinding.message = messages[position]
        holder.itemMessageBinding.parent = position % 2 != 0
    }

    override fun getItemCount(): Int = messages.size
}