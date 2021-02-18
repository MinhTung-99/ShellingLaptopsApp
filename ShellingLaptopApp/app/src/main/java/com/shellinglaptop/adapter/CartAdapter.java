package com.shellinglaptop.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.shellinglaptop.R;
import com.shellinglaptop.databinding.ItemCartBinding;
import com.shellinglaptop.db.CartDao;
import com.shellinglaptop.db.CartDatabase;
import com.shellinglaptop.model.Cart;
import com.shellinglaptop.model.Order;
import com.shellinglaptop.utils.PriceUtils;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{

    private List<Cart> carts;
    private ButtonOnClickListener buttonOnClickListener;

    public CartAdapter(List<Cart> carts, ButtonOnClickListener buttonOnClickListener) {
        this.carts = carts;
        this.buttonOnClickListener = buttonOnClickListener;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_cart, parent, false),
                buttonOnClickListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        carts.get(position).setTotalMoneyStr(carts.get(position).getTotalMoney().toString());
        String setupTotalMoney = PriceUtils.setupPrice(carts.get(position).getTotalMoneyStr());
        carts.get(position).setTotalMoneyStr(setupTotalMoney +" VND");
        holder.itemCartBinding.setCart(carts.get(position));

        holder.itemCartBinding.btnDelete.setOnClickListener(v -> {
            buttonOnClickListener.btnDeleteOnClick(carts.get(position));
        });

        holder.itemCartBinding.btnOrder.setOnClickListener(v -> {
            buttonOnClickListener.btnOrderOnClick(carts.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{

        private ItemCartBinding itemCartBinding;
        private ButtonOnClickListener buttonOnClickListener;

        public CartViewHolder(ItemCartBinding itemCartBinding, ButtonOnClickListener buttonOnClickListener) {
            super(itemCartBinding.getRoot());
            this.itemCartBinding = itemCartBinding;
            this.buttonOnClickListener = buttonOnClickListener;
        }
    }

    public interface ButtonOnClickListener{
        void btnDeleteOnClick(Cart cart);
        void btnOrderOnClick(Cart cart);
    }
}
