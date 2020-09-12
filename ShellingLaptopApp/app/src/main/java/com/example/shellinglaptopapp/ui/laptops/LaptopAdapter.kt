package com.example.shellinglaptopapp.ui.laptops

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shellinglaptopapp.R
import com.example.shellinglaptopapp.databinding.ItemLaptopBinding
import com.example.shellinglaptopapp.data.model.Laptop

class LaptopAdapter(
    private val laptops: List<Laptop>,
    private val listener: RecyclerViewClickListener
): RecyclerView.Adapter<LaptopAdapter.LaptopViewHolder>() {

    class LaptopViewHolder(
        val itemLaptopBinding: ItemLaptopBinding
    ) : RecyclerView.ViewHolder(itemLaptopBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaptopViewHolder =
        LaptopViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_laptop,
                parent, false)
        )

    override fun onBindViewHolder(holder: LaptopViewHolder, position: Int) {

        var reversePrice = reverseString(laptops[position].price.toString())
        var newPriceStr = "" //add "."
        var count = 0
        for(element in reversePrice){

            if(count == 3){
                newPriceStr = newPriceStr.plus(".")
                count = 0
            }
            count++
            newPriceStr = newPriceStr.plus(element)
        }

        var reverseNewTotalMoneyStr = reverseString(newPriceStr)
        laptops[position].priceStr = "$reverseNewTotalMoneyStr VNĐ"
        holder.itemLaptopBinding.laptop = laptops[position]

        holder.itemLaptopBinding.root.setOnClickListener {
            listener.onRecyclerViewItemClick(laptops[position])
        }
    }

    private fun reverseString(str: String): String{
        var position = str.length-1
        var strReverse = ""
        for(e in str){
            strReverse = strReverse.plus(str[position])
            position--
        }

        return strReverse
    }

    override fun getItemCount(): Int = laptops.size
}