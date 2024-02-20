package com.example.foodplanner.DataBase;


import com.example.foodplanner.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


public interface MealLocalDataSource {
    Flowable<List<Meal>> getStoredMeal();

    Flowable<List<Meal>> getAllSavedMeal();

    void insertFavouriteMeal(Meal meal);

    void deleteFromFavourite(Meal meal);

    Flowable<List<Meal>> getAllMeals();

    void deleteAllData();


}
