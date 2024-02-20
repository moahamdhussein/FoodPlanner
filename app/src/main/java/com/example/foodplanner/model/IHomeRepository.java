package com.example.foodplanner.model;

import com.example.foodplanner.Network.NetworkCallback;
import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface IHomeRepository {
    void getRemoteData(NetworkCallback callback);

    void getRandomMean(NetworkCallback callback);

    void getAllIngredient(NetworkCallback callback);

     void getMealWithName(NetworkCallback callback,String name);

     void searchForAMeal(NetworkCallback callback,String name,String type);

     void getMeals(NetworkCallback callback , String name,String type);

    void insertMeal(Meal meal);

    void deleteMeal(Meal meal);

    Flowable<List<Meal>> getStoredMeals();

    Flowable<List<Meal>> getAllSavedMeal();

    void getAllContinues(NetworkCallback callback);

    void backup();

    void downloadBackup();
}
