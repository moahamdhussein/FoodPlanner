package com.example.foodplanner.Network;


import com.example.foodplanner.model.pojos.ParentArea;
import com.example.foodplanner.model.pojos.ParentCategories;
import com.example.foodplanner.model.pojos.ParentIngredients;
import com.example.foodplanner.model.pojos.ParentMeal;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("categories.php")
    Observable<ParentCategories> getAllCategories();
    @GET("random.php")
    Observable<ParentMeal> getRandomMeal();

    @GET("list.php?i=list")
    Observable<ParentIngredients> getAllIngredients();

    @GET("search.php?s=")
    Observable<ParentMeal> getMealUsingName(@Query("s") String name);

    @GET("filter.php?c=")
    Observable<ParentMeal> getMealsFilteredBasedOnCategory(@Query(value = "c") String name);

    @GET("filter.php?i=")
    Observable<ParentMeal> getMealsFilteredBasedOnIngredient(@Query(value = "i") String name);
    @GET("search.php?s=")
    Observable<ParentMeal> searchForAMealWithName(@Query("s") String name);

    @GET("filter.php?a=")
    Observable<ParentMeal> getMealsFilteredBasedOnArea(@Query("a") String name);

    @GET("list.php?a=list")
    Observable<ParentArea> getAllCountries();




}
