package com.shellinglaptop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.shellinglaptop.R;
import com.shellinglaptop.databinding.ItemLaptopBinding;
import com.shellinglaptop.model.Laptop;
import com.shellinglaptop.utils.PriceUtils;

import java.util.List;

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.LaptopViewHolder>{

    private List<Laptop> laptops;
    private RecyclerViewLaptopClickListener recyclerViewLaptopClickListener;

    public void setRecyclerViewLaptopClickListener(RecyclerViewLaptopClickListener recyclerViewLaptopClickListener) {
        this.recyclerViewLaptopClickListener = recyclerViewLaptopClickListener;
    }

    public LaptopAdapter(List<Laptop> laptops) {
        this.laptops = laptops;
    }

    public void setLaptops(List<Laptop> laptops){
        this.laptops = laptops;
        notifyDataSetChanged();
    }

    @Override
    public LaptopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LaptopViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_laptop, parent, false),
            recyclerViewLaptopClickListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull LaptopViewHolder holder, int position) {
        String setupPrice = PriceUtils.setupPrice(laptops.get(position).getPrice().toString());
        laptops.get(position).setPriceStr(setupPrice + " VND");
        holder.itemLaptopBinding.setLaptop(laptops.get(position));
        holder.itemLaptopBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.recyclerViewLaptopClickListener.RecyclerViewLaptopItemClick(laptops.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return laptops.size();
    }

    class LaptopViewHolder extends RecyclerView.ViewHolder {

        private ItemLaptopBinding itemLaptopBinding;
        private RecyclerViewLaptopClickListener recyclerViewLaptopClickListener;

        public LaptopViewHolder(ItemLaptopBinding itemLaptopBinding, RecyclerViewLaptopClickListener recyclerViewLaptopClickListener) {
            super(itemLaptopBinding.getRoot());
            this.itemLaptopBinding = itemLaptopBinding;
            this.recyclerViewLaptopClickListener = recyclerViewLaptopClickListener;
        }
    }

    public interface RecyclerViewLaptopClickListener{
        void RecyclerViewLaptopItemClick(Laptop laptop);
    }
}
