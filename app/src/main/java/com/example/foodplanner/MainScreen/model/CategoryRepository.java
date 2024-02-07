package com.example.foodplanner.MainScreen.model;

import com.example.foodplanner.Network.CategoryRemoteDataSource;
import com.example.foodplanner.Network.NetworkCallback;

public class CategoryRepository implements ICategoryRepository {

    CategoryRemoteDataSource remoteDataSource;

    private static CategoryRepository repository= null;

    public  static CategoryRepository getInstance(CategoryRemoteDataSource remoteDataSource){
        if (repository ==null){
            repository = new CategoryRepository(remoteDataSource);
        }
        return repository;
    }

    public CategoryRepository(CategoryRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getRemoteData(NetworkCallback callback){
        remoteDataSource.makeNetworkCall(callback);
    }
}
