package com.shellinglaptop.adapter;

import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.LaptopViewHolder>{

    private List<Laptop> laptops;
    private RecyclerViewLaptopClickListener recyclerViewLaptopClickListener;
    private List<Laptop> searchLaptop;

    public void setRecyclerViewLaptopClickListener(RecyclerViewLaptopClickListener recyclerViewLaptopClickListener) {
        this.recyclerViewLaptopClickListener = recyclerViewLaptopClickListener;
    }

    public LaptopAdapter(List<Laptop> laptops) {
        searchLaptop = new ArrayList<>();
        this.laptops = laptops;
    }

    public void setLaptops(List<Laptop> laptops){
        this.laptops = laptops;
        searchLaptop.addAll(laptops);
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
        laptops.get(position).setPriceStr(laptops.get(position).getPrice().toString());
        String setupMoney = PriceUtils.setupPrice(laptops.get(position).getPriceStr());
        laptops.get(position).setPriceStr(setupMoney +" VND");
        holder.itemLaptopBinding.setLaptop(laptops.get(position));
        holder.itemLaptopBinding.getRoot().setOnClickListener(v ->
                holder.recyclerViewLaptopClickListener.RecyclerViewLaptopItemClick(laptops.get(position)));
    }

    @Override
    public int getItemCount() {
        return laptops.size();
    }

    public void filter(String text){
        String myText = text.toLowerCase(Locale.getDefault());
        laptops.clear();
        if(myText.isEmpty()){
            laptops.addAll(searchLaptop);
        }else {
            for(Laptop laptop : searchLaptop){
                if(laptop.getName().toLowerCase(Locale.getDefault()).contains(myText)){
                    laptops.add(laptop);
                }
            }
        }

        notifyDataSetChanged();
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
