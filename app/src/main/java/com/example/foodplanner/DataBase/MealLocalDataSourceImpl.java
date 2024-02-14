package com.example.foodplanner.DataBase;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.MainScreen.model.Meal;

import java.util.List;

public class MealLocalDataSourceImpl implements MealLocalDataSource {

    private MealDao dao;

    private static MealLocalDataSource localDataSource = null;
    private LiveData<List<Meal>> storedMeal;

    private MealLocalDataSourceImpl(Context context){
        AppDataBase appDataBase = AppDataBase.getInstance(context.getApplicationContext());
        dao = appDataBase.getMealDao();
        storedMeal = dao.getAllFavouriteMeals();
    }

    public static MealLocalDataSource getInstance(Context context){
        if (localDataSource == null){
            localDataSource = new MealLocalDataSourceImpl(context);
        }
        return localDataSource;
    }

    @Override
    public LiveData<List<Meal>> getStoredMeal(){
        return storedMeal;
    }
}
