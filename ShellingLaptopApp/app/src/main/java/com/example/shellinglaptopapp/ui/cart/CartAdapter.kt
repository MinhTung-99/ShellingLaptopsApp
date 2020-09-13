package com.example.shellinglaptopapp.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shellinglaptopapp.R
import com.example.shellinglaptopapp.data.model.Cart
import com.example.shellinglaptopapp.databinding.ItemCartBinding

class CartAdapter(
    private val carts: List<Cart>,
    private val  listener: RecyclerViewCartClickListener
): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(
        val itemCartBinding: ItemCartBinding
    ) : RecyclerView.ViewHolder(itemCartBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder =
        CartViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_cart,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        var reverseTotalMoney = reverseString(carts[position].totalMoney.toString())

        var newTotalMoneyStr = "" //add "."
        var count = 0
        for(element in reverseTotalMoney){

            if(count == 3){
                newTotalMoneyStr = newTotalMoneyStr.plus(".")
                count = 0
            }
            count++
            newTotalMoneyStr = newTotalMoneyStr.plus(element)
        }

        var reverseNewTotalMoneyStr = reverseString(newTotalMoneyStr)

        carts[position].totalMoneyStr = "$reverseNewTotalMoneyStr VNĐ"
        holder.itemCartBinding.cart = carts[position]

        holder.itemCartBinding.btnOrder.setOnClickListener {
            listener.orderOnClick(carts[position])
        }
        holder.itemCartBinding.btnDelete.setOnClickListener {
            listener.deleteOnClick(carts[position])
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

    override fun getItemCount(): Int = carts.size
}