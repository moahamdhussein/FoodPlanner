package com.example.foodplanner.model;

import com.example.foodplanner.Network.NetworkCallback;

interface IHomeRepository {
    void getRemoteData(NetworkCallback callback);

    void getRandomMean(NetworkCallback callback);

    void getAllIngredient(NetworkCallback callback);
}
