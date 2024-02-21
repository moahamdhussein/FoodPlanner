package com.example.foodplanner.model;

import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.model.pojos.ParentArea;
import com.example.foodplanner.model.pojos.ParentCategories;
import com.example.foodplanner.model.pojos.ParentIngredients;
import com.example.foodplanner.model.pojos.ParentMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface IHomeRepository {
    Single<ParentCategories> getRemoteData();

    Single<ParentMeal> getRandomMeal();

    Single<ParentIngredients> getAllIngredient();

    Single<ParentMeal> getMealWithName(String name);

     Maybe<ParentMeal> searchForAMeal(String name, String type);

    Maybe<ParentMeal> getMeals(String name, String type);

    void insertMeal(Meal meal);

    void deleteMeal(Meal meal);

    Flowable<List<Meal>> getStoredMeals();

    Flowable<List<Meal>> getAllSavedMeal();

    Single<ParentArea> getAllContinues();

    void backup();

    void downloadBackup();
}
