package com.leynnnnnn.shoppingapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Favorite extends Fragment implements FavoriteInterface {
    DatabaseReference dbRef;
    List<ItemInfo> favorites = new ArrayList<>();
    ItemInfo[] favoritesList;
    RecyclerView recyclerView;
    FavoriteAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        dbRef = FirebaseDatabase.getInstance().getReference().child("Favorites");
        recyclerView = rootView.findViewById(R.id.favoritesRecyclerView);
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
                favoritesList = favorites.toArray(new ItemInfo[0]);
                Log.d("message", " " + favoritesList.length);
                adapter = new FavoriteAdapter(requireContext(), favoritesList, Favorite.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return rootView;
    }
    @Override
    public void onItemClick(int id) {
        Toast.makeText(requireContext(), "" + id, Toast.LENGTH_SHORT).show();

    }
}