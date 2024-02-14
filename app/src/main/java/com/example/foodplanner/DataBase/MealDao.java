package com.example.foodplanner.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.foodplanner.MainScreen.model.Meal;

import java.util.List;

@Dao
public interface MealDao {

    @Query("SELECT * FROM  favourite_meal")
    LiveData<List<Meal>> getAllFavouriteMeals();
}
