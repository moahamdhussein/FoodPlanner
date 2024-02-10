package com.example.foodplanner.MainScreen.model;

import android.util.Log;

import com.example.foodplanner.Network.Ingredients.IngredientsRemoteDataSource;
import com.example.foodplanner.Network.Random.RandomRemoteDataSource;
import com.example.foodplanner.Network.category.CategoryRemoteDataSource;
import com.example.foodplanner.Network.NetworkCallback;

public class HomeRepository implements IHomeRepository {

    CategoryRemoteDataSource categoryRemoteDataSource;
    RandomRemoteDataSource randomRemoteDataSource;

    IngredientsRemoteDataSource ingredientsRemoteDataSource;

    private static final String TAG = "HomeRepository";
    private static HomeRepository repository= null;

    public  static HomeRepository getInstance(CategoryRemoteDataSource remoteDataSource,RandomRemoteDataSource randomRemoteDataSource ,IngredientsRemoteDataSource ingredientsRemoteDataSource){
        if (repository ==null){
            repository = new HomeRepository(remoteDataSource,randomRemoteDataSource,ingredientsRemoteDataSource);
        }
        return repository;
    }

    private HomeRepository(CategoryRemoteDataSource remoteDataSource, RandomRemoteDataSource randomRemoteDataSource , IngredientsRemoteDataSource ingredientsRemoteDataSource) {
        this.categoryRemoteDataSource = remoteDataSource;
        this.randomRemoteDataSource =randomRemoteDataSource;
        this.ingredientsRemoteDataSource = ingredientsRemoteDataSource;
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


}
