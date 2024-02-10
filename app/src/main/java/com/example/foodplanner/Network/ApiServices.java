package com.example.foodplanner.Network;


import com.example.foodplanner.MainScreen.model.ParentCategories;
import com.example.foodplanner.MainScreen.model.ParentIngredients;
import com.example.foodplanner.MainScreen.model.ParentMeal;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {
    @GET("categories.php")
    Call<ParentCategories> getAllCategories();
    @GET("random.php")
    Call<ParentMeal> getRandomMeal();

    @GET("list.php?i=list")
    Call<ParentIngredients> getAllIngredients();
}

//www.themealdb.com/api/json/v1/1/