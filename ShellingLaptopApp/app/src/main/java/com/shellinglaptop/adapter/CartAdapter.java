package com.shellinglaptop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.shellinglaptop.R;
import com.shellinglaptop.databinding.ItemCartBinding;
import com.shellinglaptop.model.Cart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{

    private List<Cart> carts;

    public CartAdapter(List<Cart> carts) {
        this.carts = carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_cart, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.itemCartBinding.setCart(carts.get(position));
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{

        private ItemCartBinding itemCartBinding;

        public CartViewHolder(ItemCartBinding itemCartBinding) {
            super(itemCartBinding.getRoot());
            this.itemCartBinding = itemCartBinding;
        }
    }
}
