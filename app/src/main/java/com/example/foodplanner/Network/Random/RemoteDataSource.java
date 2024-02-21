package com.example.foodplanner.Network.Random;

import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.model.pojos.ParentArea;
import com.example.foodplanner.model.pojos.ParentCategories;
import com.example.foodplanner.model.pojos.ParentIngredients;
import com.example.foodplanner.model.pojos.ParentMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface RemoteDataSource {
    Single<ParentMeal> getRandomMeal();

    Single<ParentMeal> getMealWithName(String name);

    Maybe<ParentMeal> getMeals(String name, String type);

    Maybe<ParentMeal> searchForAMealWithName(String name, String type);

    Single<ParentArea> getAllCountries();

     void backup(List<Meal> meals);

     void getDataFromFireBase();

    Single<ParentCategories> getAllCategory();

    Single<ParentIngredients> getAllIngredients();


}
