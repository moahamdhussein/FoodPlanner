package com.example.foodplanner.Network;


import com.example.foodplanner.model.pojos.ParentArea;
import com.example.foodplanner.model.pojos.ParentCategories;
import com.example.foodplanner.model.pojos.ParentIngredients;
import com.example.foodplanner.model.pojos.ParentMeal;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("categories.php")
    Single<ParentCategories> getAllCategories();
    @GET("random.php")
    Single<ParentMeal> getRandomMeal();

    @GET("list.php?i=list")
    Single<ParentIngredients> getAllIngredients();

    @GET("search.php?s=")
    Single<ParentMeal> getMealUsingName(@Query("s") String name);

    @GET("filter.php?c=")
    Maybe<ParentMeal> getMealsFilteredBasedOnCategory(@Query(value = "c") String name);

    @GET("filter.php?i=")
    Maybe<ParentMeal> getMealsFilteredBasedOnIngredient(@Query(value = "i") String name);
    @GET("search.php?s=")
    Maybe<ParentMeal> searchForAMealWithName(@Query("s") String name);

    @GET("filter.php?a=")
    Maybe<ParentMeal> getMealsFilteredBasedOnArea(@Query("a") String name);

    @GET("list.php?a=list")
    Single<ParentArea> getAllCountries();




}
