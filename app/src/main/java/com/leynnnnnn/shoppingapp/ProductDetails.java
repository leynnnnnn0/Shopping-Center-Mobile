package com.leynnnnnn.shoppingapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails extends AppCompatActivity implements FavoriteDb {
    ImageView image;
    ImageButton addToFavorite;
    TextView name, price, details;
    DatabaseReference dbRef;
    List<Integer> idList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_details);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Favorites");
        image = findViewById(R.id.imageDetail);
        name = findViewById(R.id.nameDetail);
        price = findViewById(R.id.priceDetail);
        details = findViewById(R.id.detailsDetail);
        addToFavorite = findViewById(R.id.addToFavorite);

        ItemInfo itemInfo = getIntent().getParcelableExtra("itemInfo");
        assert itemInfo != null;
        image.setImageResource(itemInfo.getImage());
        name.setText(itemInfo.getName());
        price.setText("$".concat(String.valueOf(itemInfo.getPrice())));
        details.setText(itemInfo.getDescription());

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                idList.clear();
                for(DataSnapshot snap: snapshot.getChildren()) {
                    Integer id = snap.child("id").getValue(Integer.class);
                    if(id != null) {
                        idList.add(id);
                    }
                    addToFavorite.setOnClickListener(v -> {
                        if(idList.contains(itemInfo.getId())){
                            FavoriteDb.removeFromFavorite(itemInfo.getId());
                            addToFavorite.setImageResource(R.drawable.favorite_icon);
                            Toast.makeText(getApplicationContext(), "Removed From Favorites", Toast.LENGTH_SHORT).show();
                            idList.clear();
                            return;
                        };
                        FavoriteDb.addToFavorite(itemInfo, getApplicationContext());
                        addToFavorite.setImageResource(R.drawable.added_favorite);
                    });
                }
                if(idList.contains(itemInfo.getId())) {
                    addToFavorite.setImageResource(R.drawable.added_favorite);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}