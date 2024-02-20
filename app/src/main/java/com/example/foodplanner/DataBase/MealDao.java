package com.example.foodplanner.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealDao {

    @Query("SELECT * FROM  favourite_meal where dbType like 'Favourite'")
    Flowable<List<Meal>> getAllFavouriteMeals();

    @Query("SELECT * FROM favourite_meal WHERE dbType LIKE 'Plan'")
    Flowable<List<Meal>> getAllSavedMeal();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertFavouriteMeal(Meal meal);

    @Delete
    Completable deleteFromFavourite(Meal meal);

    @Query("SELECT * FROM favourite_meal")
    Flowable<List<Meal>> getAllMeals();

    @Query("DELETE FROM favourite_meal")
    Completable deleteAllData();
}
