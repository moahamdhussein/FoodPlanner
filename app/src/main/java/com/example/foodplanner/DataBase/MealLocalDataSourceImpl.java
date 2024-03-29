package com.example.foodplanner.DataBase;

import android.content.Context;

import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealLocalDataSourceImpl implements MealLocalDataSource {

    private MealDao dao;
    private static final String TAG = "MealLocalDataSourceImpl";
    private static MealLocalDataSourceImpl localDataSource = null;
    private Flowable<List<Meal>> storedMeal;

    private Flowable<List<Meal>> savedMeal;

    private MealLocalDataSourceImpl(Context context){
        AppDataBase appDataBase = AppDataBase.getInstance(context.getApplicationContext());
        dao = appDataBase.getMealDao();
        storedMeal = dao.getAllFavouriteMeals();
        savedMeal =dao.getAllSavedMeal();
    }

    public static MealLocalDataSourceImpl getInstance(Context context){
        if (localDataSource == null){
            localDataSource = new MealLocalDataSourceImpl(context);
        }
        return localDataSource;
    }

    @Override
    public Flowable<List<Meal>> getStoredMeal(){
        return storedMeal;
    }

    @Override
    public Flowable<List<Meal>> getAllSavedMeal() {
        return  savedMeal;
    }


    @Override
    public void insertFavouriteMeal(Meal meal) {
        dao.insertFavouriteMeal(meal).subscribeOn(Schedulers.io()).subscribe();
    }

    @Override
    public void deleteFromFavourite(Meal meal) {
        dao.deleteFromFavourite(meal).subscribeOn(Schedulers.io()).subscribe();
    }

    @Override
    public Flowable<List<Meal>> getAllMeals() {
        return dao.getAllMeals();
    }

    @Override
    public void deleteAllData() {
        dao.deleteAllData().subscribeOn(Schedulers.io()).subscribe();
    }


}
