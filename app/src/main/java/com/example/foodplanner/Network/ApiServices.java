package com.example.foodplanner.Network;


import com.example.foodplanner.MainScreen.model.ParentCategories;
import com.example.foodplanner.MainScreen.model.ParentIngredients;
import com.example.foodplanner.MainScreen.model.ParentMeal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("categories.php")
    Call<ParentCategories> getAllCategories();
    @GET("random.php")
    Call<ParentMeal> getRandomMeal();

    @GET("list.php?i=list")
    Call<ParentIngredients> getAllIngredients();

    @GET("search.php?s=")
    Call<ParentMeal> getMealUsingName(@Query("s") String name);

    @GET("filter.php?c=")
    Call<ParentMeal> getMealsFilteredBasedOnCategory(@Query(value = "c") String name);

    @GET("filter.php?i=")
    Call<ParentMeal> getMealsFilteredBasedOnIngredient(@Query(value = "i") String name);

}

//www.themealdb.com/api/json/v1/1/search.php?s=