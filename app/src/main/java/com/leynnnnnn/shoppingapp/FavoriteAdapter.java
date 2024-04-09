package com.leynnnnnn.shoppingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    Context context;
    ItemInfo[] itemInfo;
    private final FavoriteInterface favoriteInterface;;

    public FavoriteAdapter(Context context, ItemInfo[] itemInfo, FavoriteInterface favoriteInterface) {
        this.context = context;
        this.itemInfo = itemInfo;
        this.favoriteInterface = favoriteInterface;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_box, parent, false);
        return new ViewHolder(view, favoriteInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        holder.image.setImageResource(itemInfo[position].getImage());
        holder.name.setText(itemInfo[position].getName());
        holder.price.setText(String.valueOf(itemInfo[position].getPrice()));

    }

    @Override
    public int getItemCount() {
        return itemInfo.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, category, price;
        ImageButton button;

        public ViewHolder(@NonNull View itemView, FavoriteInterface favoriteInterface) {
            super(itemView);
            image = itemView.findViewById(R.id.favoriteImage);
            name = itemView.findViewById(R.id.favoriteName);
            category = itemView.findViewById(R.id.favoriteCategory);
            price = itemView.findViewById(R.id.favoritePrice);
            button = itemView.findViewById(R.id.removeFromFavorite);

            button.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if(pos != RecyclerView.NO_POSITION) {
                    FavoriteDb.removeFromFavorite(itemInfo[pos].getId());
                }
            });

        }
    }
}
