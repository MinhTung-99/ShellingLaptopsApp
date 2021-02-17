package com.shellinglaptop.utils;

import com.shellinglaptop.model.Laptop;

public class ClickUtils {
    public interface IRecyclerViewClickListener{
        void recyclerViewItemClick(Laptop laptop);
        void btnDeleteItemClick(Laptop laptop);
    }
}
