package com.example.foodplanner.model;

import android.util.Log;

import com.example.foodplanner.DataBase.MealLocalDataSourceImpl;
import com.example.foodplanner.Network.Ingredients.IngredientsRemoteDataSource;
import com.example.foodplanner.Network.Random.RandomRemoteDataSource;
import com.example.foodplanner.Network.category.CategoryRemoteDataSource;
import com.example.foodplanner.Network.NetworkCallback;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlinx.coroutines.flow.Flow;

public class HomeRepository implements IHomeRepository {

    CategoryRemoteDataSource categoryRemoteDataSource;
    RandomRemoteDataSource randomRemoteDataSource;

    MealLocalDataSourceImpl localDataSource;

    IngredientsRemoteDataSource ingredientsRemoteDataSource;



    private static final String TAG = "HomeRepository";
    private static HomeRepository repository= null;



    public  static HomeRepository getInstance(CategoryRemoteDataSource remoteDataSource,RandomRemoteDataSource randomRemoteDataSource ,IngredientsRemoteDataSource ingredientsRemoteDataSource,MealLocalDataSourceImpl localDataSource){
        if (repository ==null){
            repository = new HomeRepository(remoteDataSource,randomRemoteDataSource,ingredientsRemoteDataSource,localDataSource);
        }
        return repository;
    }

    private HomeRepository(CategoryRemoteDataSource remoteDataSource, RandomRemoteDataSource randomRemoteDataSource , IngredientsRemoteDataSource ingredientsRemoteDataSource,MealLocalDataSourceImpl localDataSource) {
        this.categoryRemoteDataSource = remoteDataSource;
        this.randomRemoteDataSource =randomRemoteDataSource;
        this.ingredientsRemoteDataSource = ingredientsRemoteDataSource;
        this.localDataSource = localDataSource;

    }

    @Override
    public void getRemoteData(NetworkCallback callback){
        categoryRemoteDataSource.makeNetworkCallback(callback);
    }

    @Override
    public void getRandomMean(NetworkCallback callback) {
        randomRemoteDataSource.makeNetworkCallback(callback);
    }

    @Override
    public void getAllIngredient(NetworkCallback callback) {
        Log.i(TAG, "getAllIngredient: Done");
        ingredientsRemoteDataSource.makeNetworkCallback(callback);
    }
    public void getMealWithName(NetworkCallback callback,String name){
        randomRemoteDataSource.getMealWithName(callback,name);
    }

    public void searchForAMeal(NetworkCallback callback,String name,String type){
        randomRemoteDataSource.searchForAMealWithName(callback,name,type);
    }

    public void getMeals(NetworkCallback callback , String name,String type){
        randomRemoteDataSource.getMeals(callback,name,type);
    }

    public void insertMeal(Meal meal) {
        localDataSource.insertFavouriteMeal(meal);
    }

    public void deleteMeal(Meal meal){
        localDataSource.deleteFromFavourite(meal);
    }

    public Flowable<List<Meal>> getStoredMeals(){
        return localDataSource.getStoredMeal();
    }

    public Flowable<List<Meal>> getAllSavedMeal(){
        return localDataSource.getAllSavedMeal();
    }

    public void getAllContinues(NetworkCallback callback) {
        randomRemoteDataSource.getAllCountries(callback);
    }

    public void backup() {
        Log.i(TAG, "backup: ");
        localDataSource.getAllMeals().observeOn(AndroidSchedulers.mainThread()).subscribe(meals ->
            randomRemoteDataSource.backup(meals),
                throwable -> Log.i(TAG, "backup: ")
        );

    }

    public void downloadBackup() {
        randomRemoteDataSource.getDataFromFireBase();
    }
}
