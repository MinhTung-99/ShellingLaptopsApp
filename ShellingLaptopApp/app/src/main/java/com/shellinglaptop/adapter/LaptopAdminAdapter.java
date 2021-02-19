package com.shellinglaptop.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.shellinglaptop.R;
import com.shellinglaptop.databinding.ItemAdminLaptopBinding;
import com.shellinglaptop.model.Laptop;
import com.shellinglaptop.utils.ClickUtils;

import java.util.ArrayList;
import java.util.List;

public class LaptopAdminAdapter extends RecyclerView.Adapter<LaptopAdminAdapter.LaptopAdminViewHolder>{

    private List<Laptop> laptops;
    private List<Laptop> laptopArr;
    private ClickUtils.IRecyclerViewClickListener recyclerViewClickListener;

    public LaptopAdminAdapter(List<Laptop> laptops, ClickUtils.IRecyclerViewClickListener recyclerViewClickListener) {
        this.laptops = laptops;
        this.recyclerViewClickListener = recyclerViewClickListener;
        laptopArr = new ArrayList<>();
    }

    @NonNull
    @Override
    public LaptopAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LaptopAdminViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_admin_laptop, parent, false),
                recyclerViewClickListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull LaptopAdminViewHolder holder, int position) {
        holder.itemAdminLaptopBinding.setLaptop(laptops.get(position));
        holder.itemAdminLaptopBinding.getRoot().setOnLongClickListener(v -> {
            holder.recyclerViewClickListener.recyclerViewItemClick(laptops.get(position));
            return false;
        });

        holder.itemAdminLaptopBinding.imgDelete.setOnClickListener(v ->{
            holder.recyclerViewClickListener.btnDeleteItemClick(laptops.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return laptops.size();
    }

    class LaptopAdminViewHolder extends RecyclerView.ViewHolder {

        private ItemAdminLaptopBinding itemAdminLaptopBinding;
        private ClickUtils.IRecyclerViewClickListener recyclerViewClickListener;

        public LaptopAdminViewHolder(ItemAdminLaptopBinding itemAdminLaptopBinding, ClickUtils.IRecyclerViewClickListener recyclerViewClickListener) {
            super(itemAdminLaptopBinding.getRoot());
            this.itemAdminLaptopBinding = itemAdminLaptopBinding;
            this.recyclerViewClickListener = recyclerViewClickListener;
        }
    }

    public void setLaptops(List<Laptop> laptops){
        this.laptops = laptops;
        laptopArr.addAll(laptops);
        notifyDataSetChanged();
    }
}
