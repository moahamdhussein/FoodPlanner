package com.example.foodplanner.model;

import android.util.Log;

import com.example.foodplanner.DataBase.MealLocalDataSourceImpl;
import com.example.foodplanner.Network.Random.RemoteDataSource;
import com.example.foodplanner.Network.NetworkCallback;
import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;

public class HomeRepository implements IHomeRepository {


    private RemoteDataSource randomRemoteDataSource;
    private MealLocalDataSourceImpl localDataSource;


    private static final String TAG = "HomeRepository";
    private static HomeRepository repository = null;


    public static HomeRepository getInstance(RemoteDataSource randomRemoteDataSource, MealLocalDataSourceImpl localDataSource) {
        if (repository == null) {
            repository = new HomeRepository(randomRemoteDataSource, localDataSource);
        }
        return repository;
    }

    private HomeRepository(RemoteDataSource randomRemoteDataSource, MealLocalDataSourceImpl localDataSource) {

        this.randomRemoteDataSource = randomRemoteDataSource;
        this.localDataSource = localDataSource;

    }

    @Override
    public void getRemoteData(NetworkCallback callback) {
        randomRemoteDataSource.getAllCategory(callback);
    }

    @Override
    public void getRandomMean(NetworkCallback callback) {
        randomRemoteDataSource.makeNetworkCallback(callback);
    }

    @Override
    public void getAllIngredient(NetworkCallback callback) {

        randomRemoteDataSource.getAllIngredients(callback);
    }

    @Override
    public void getMealWithName(NetworkCallback callback, String name) {
        randomRemoteDataSource.getMealWithName(callback, name);
    }

    @Override

    public void searchForAMeal(NetworkCallback callback, String name, String type) {
        randomRemoteDataSource.searchForAMealWithName(callback, name, type);
    }

    @Override
    public void getMeals(NetworkCallback callback, String name, String type) {
        randomRemoteDataSource.getMeals(callback, name, type);
    }

    @Override
    public void insertMeal(Meal meal) {
        localDataSource.insertFavouriteMeal(meal);
    }

    @Override
    public void deleteMeal(Meal meal) {
        localDataSource.deleteFromFavourite(meal);
    }

    @Override
    public Flowable<List<Meal>> getStoredMeals() {
        return localDataSource.getStoredMeal();
    }

    @Override
    public Flowable<List<Meal>> getAllSavedMeal() {
        return localDataSource.getAllSavedMeal();
    }

    @Override
    public void getAllContinues(NetworkCallback callback) {
        randomRemoteDataSource.getAllCountries(callback);
    }

    @Override
    public void backup() {
        Log.i(TAG, "backup: ");
        localDataSource.getAllMeals().observeOn(AndroidSchedulers.mainThread()).subscribe(meals ->
                        randomRemoteDataSource.backup(meals),
                throwable -> Log.i(TAG, "backup: ")
        );

    }

    @Override
    public void downloadBackup() {
        randomRemoteDataSource.getDataFromFireBase();
    }
}
