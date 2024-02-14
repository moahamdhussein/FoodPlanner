package com.example.foodplanner.DataBase;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.MainScreen.model.Meal;

import java.util.List;

interface MealLocalDataSource {
    LiveData<List<Meal>> getStoredMeal();
}
