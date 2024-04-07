package com.leynnnnnn.shoppingapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.denzcoskun.imageslider.constants.ScaleTypes;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment implements HomeInterface{
    List<SlideModel> imageList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Code for the sliding image
        // Documentation here https://github.com/denzcoskun/ImageSlideshow
        imageList.add(new SlideModel(R.drawable.card, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.card, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.card, ScaleTypes.FIT));
        ImageSlider imageSlider = rootView.findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);

        // Code for the recycler view images // Recommended Section
        ItemInfo[] recommended = new ItemInfo[]{
              new ItemInfo(1, R.drawable.empty_image, "Tang Lemon", 12, "Organic"),
                new ItemInfo(2, R.drawable.empty_image, "Jash Tea", 7.64, "Organic"),
                new ItemInfo(3, R.drawable.empty_image, "Gor Juice", 15.43, "Organic"),
        };
        RecyclerView recommendedRecyclerView = rootView.findViewById(R.id.recommendedRecyclerView);
        HomeRecyclerViewAdapter adapter = new HomeRecyclerViewAdapter(requireContext(), recommended, this);
        recommendedRecyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recommendedRecyclerView.setAdapter(adapter);

        // Code for the recycler view images // Beset Sellers Section
        ItemInfo[] bestSellers = new ItemInfo[]{
                new ItemInfo(4, R.drawable.empty_image, "Fresh Lemon", 12, "Organic"),
                new ItemInfo(5, R.drawable.empty_image, "Green Tea", 7.64, "Organic"),
                new ItemInfo(6, R.drawable.empty_image, "Mango Juice", 15.43, "Organic"),
        };
        RecyclerView bestSellersRecyclerView = rootView.findViewById(R.id.bestSellersRecyclerView);
        bestSellersRecyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        HomeRecyclerViewAdapter bestSellersAdapter = new HomeRecyclerViewAdapter(requireContext(), bestSellers, this);
        bestSellersRecyclerView.setAdapter(bestSellersAdapter);

        return rootView;
    }


    @Override
    public void onItemClick(int id, int image, String name, double price, String category, String description) {
        Intent intent = new Intent(requireContext(), ProductDetails.class);
        ItemInfo itemInfo = new ItemInfo(id, image, name, price, category, description);
        intent.putExtra("itemInfo", itemInfo);
        startActivity(intent);
    }
}