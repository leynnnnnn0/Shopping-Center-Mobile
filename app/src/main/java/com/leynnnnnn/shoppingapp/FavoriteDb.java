package com.leynnnnnn.shoppingapp;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public interface FavoriteDb {
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Favorites");
    ArrayList<ItemInfo> favorites = new ArrayList<>();

    static void addToFavorite(ItemInfo itemInfo, Context context) {
        dbRef.push().setValue(itemInfo).addOnSuccessListener(unused -> Toast.makeText(context, "Added to favorites.", Toast.LENGTH_SHORT).show());
    }

    static void getFavorites() {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                favorites.clear();
                for(DataSnapshot snap : snapshot.getChildren()) {
                    Integer id = snap.child("id").getValue(Integer.class);
                    Integer image = snap.child("image").getValue(Integer.class);
                    String name = snap.child("name").getValue(String.class);
                    Double price = snap.child("price").getValue(Double.class);
                    String category = snap.child("category").getValue(String.class);
                    String description = snap.child("description").getValue(String.class);

                    favorites.add(new ItemInfo(id, image, name, price, category, description));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}