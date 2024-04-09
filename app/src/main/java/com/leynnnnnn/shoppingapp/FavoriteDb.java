package com.leynnnnnn.shoppingapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public interface FavoriteDb {
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Favorites");
    ArrayList<ItemInfo> favorites = new ArrayList<>();


    static void removeFromFavorite(int id) {
        Query query = dbRef.orderByChild("id").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()) {
                    snap.getRef().removeValue();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    static void addToFavorite(ItemInfo itemInfo, Context context) {
        dbRef.push().setValue(itemInfo).addOnSuccessListener(unused -> Toast.makeText(context, "Added to favorites.", Toast.LENGTH_SHORT).show());
    }



}