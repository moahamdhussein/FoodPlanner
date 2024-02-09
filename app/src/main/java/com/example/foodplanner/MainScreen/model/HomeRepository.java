package com.example.foodplanner.MainScreen.model;

import com.example.foodplanner.Network.Random.RandomRemoteDataSource;
import com.example.foodplanner.Network.category.CategoryRemoteDataSource;
import com.example.foodplanner.Network.NetworkCallback;

public class HomeRepository implements IHomeRepository {

    CategoryRemoteDataSource categoryRemoteDataSource;
    RandomRemoteDataSource randomRemoteDataSource;



    private static HomeRepository repository= null;

    public  static HomeRepository getInstance(CategoryRemoteDataSource remoteDataSource,RandomRemoteDataSource randomRemoteDataSource){
        if (repository ==null){
            repository = new HomeRepository(remoteDataSource,randomRemoteDataSource);
        }
        return repository;
    }

    private HomeRepository(CategoryRemoteDataSource remoteDataSource,RandomRemoteDataSource randomRemoteDataSource) {
        this.categoryRemoteDataSource = remoteDataSource;
        this.randomRemoteDataSource =randomRemoteDataSource;
    }

    @Override
    public void getRemoteData(NetworkCallback callback){
        categoryRemoteDataSource.makeNetworkCallback(callback);
    }

    @Override
    public void getRandomMean(NetworkCallback callback) {
        randomRemoteDataSource.makeNetworkCallback(callback);
    }


}
