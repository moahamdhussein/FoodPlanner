package com.example.foodplanner.Network.Random;

import com.example.foodplanner.Network.NetworkCallback;

public interface RandomRemoteDataSource {
    void makeNetworkCallback(NetworkCallback callback);

    void getMealWithName(NetworkCallback callback,String name);

    void getMeals(NetworkCallback callback , String name,String type);
}
