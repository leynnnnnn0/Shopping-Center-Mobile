package com.leynnnnnn.shoppingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProductDetails extends AppCompatActivity implements FavoriteDb {
    ImageView image;
    ImageButton addToFavorite;
    TextView name, price, details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_details);

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

        addToFavorite.setOnClickListener(v -> {
            FavoriteDb.addToFavorite(itemInfo, getApplicationContext());
            addToFavorite.setImageResource(R.drawable.added_favorite);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}