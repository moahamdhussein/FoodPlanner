package com.example.foodplanner.model;

import android.util.Log;

import com.example.foodplanner.DataBase.MealLocalDataSourceImpl;
import com.example.foodplanner.Network.Random.RemoteDataSource;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.model.pojos.ParentArea;
import com.example.foodplanner.model.pojos.ParentCategories;
import com.example.foodplanner.model.pojos.ParentIngredients;
import com.example.foodplanner.model.pojos.ParentMeal;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

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
    public Single<ParentCategories> getRemoteData() {
        return randomRemoteDataSource.getAllCategory();
    }

    @Override
    public Single<ParentMeal> getRandomMeal() {
        return randomRemoteDataSource.getRandomMeal();
    }

    @Override
    public Single<ParentIngredients> getAllIngredient() {

        return randomRemoteDataSource.getAllIngredients();
    }

    @Override
    public Single<ParentMeal> getMealWithName( String name) {
        return randomRemoteDataSource.getMealWithName( name);
    }

    @Override

    public Maybe<ParentMeal> searchForAMeal(String name, String type) {
        return randomRemoteDataSource.searchForAMealWithName(name, type);
    }

    @Override
    public Maybe<ParentMeal> getMeals(String name, String type) {
       return randomRemoteDataSource.getMeals( name, type);
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
    public Single<ParentArea> getAllContinues() {
       return randomRemoteDataSource.getAllCountries();
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
