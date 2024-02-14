package com.example.foodplanner.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.MainScreen.model.Meal;

@Database(entities = {Meal.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance = null;

    public abstract MealDao getMealDao();

    public static synchronized AppDataBase getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext()
            ,AppDataBase.class,"FavouriteDB").build();
        }
        return instance;
    }
}
