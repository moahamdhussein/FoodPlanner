package com.example.foodplanner.Network.Random;

import com.example.foodplanner.Network.NetworkCallback;
import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

public interface RandomRemoteDataSource {
    void makeNetworkCallback(NetworkCallback callback);

    void getMealWithName(NetworkCallback callback,String name);

    void getMeals(NetworkCallback callback , String name,String type);

    void searchForAMealWithName(NetworkCallback callback , String name,String type);

    void getAllCountries(NetworkCallback callback);

     void backup(List<Meal> meals);

     void getDataFromFireBase();

     void getAllCategory(NetworkCallback callback);

     void getAllIngredients(NetworkCallback callback);


}
