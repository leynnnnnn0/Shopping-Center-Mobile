package com.leynnnnnn.shoppingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {
    Context context;
    ItemInfo[] itemInfo;
    private final HomeInterface homeInterface;

    public HomeRecyclerViewAdapter(Context context, ItemInfo[] itemInfo, HomeInterface homeInterface) {
        this.context = context;
        this.itemInfo = itemInfo;
        this.homeInterface = homeInterface;
    }

    @NonNull
    @Override
    public HomeRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_boxes_layout, parent, false);
        return new ViewHolder(view, homeInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.image.setImageResource(itemInfo[position].getImage());
        holder.itemName.setText(itemInfo[position].getName());
        holder.itemCategory.setText(itemInfo[position].getCategory());
    }

    @Override
    public int getItemCount() {
        return itemInfo.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView itemName, itemCategory;

        public ViewHolder(@NonNull View itemView, HomeInterface homeInterface) {
            super(itemView);
            image = itemView.findViewById(R.id.homeItemImage);
            itemName = itemView.findViewById(R.id.homeItemName);
            itemCategory = itemView.findViewById(R.id.homeItemCategory);

            itemView.setOnClickListener(v -> {
                if(homeInterface != null) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {
                        homeInterface.onItemClick(
                                itemInfo[pos].getId(),
                                itemInfo[pos].getImage(),
                                itemInfo[pos].getName(),
                                itemInfo[pos].getPrice(),
                                itemInfo[pos].getCategory(),
                                itemInfo[pos].getDescription()
                                );
                    }
                }
            });
        }
    }


}
