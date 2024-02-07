package com.example.foodplanner.MainScreen.model;

import com.example.foodplanner.Network.NetworkCallback;

interface ICategoryRepository {
    void getRemoteData(NetworkCallback callback);
}
