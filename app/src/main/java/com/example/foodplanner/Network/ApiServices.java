package com.example.foodplanner.Network;

import com.example.foodplanner.MainScreen.model.ParentCategories;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {
    @GET("categories.php")
    Call<ParentCategories> getAllCategories();
}
//www.themealdb.com/api/json/v1/1/