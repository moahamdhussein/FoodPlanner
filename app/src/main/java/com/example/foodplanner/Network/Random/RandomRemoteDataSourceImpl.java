package com.example.foodplanner.Network.Random;


import android.annotation.SuppressLint;
import android.util.Log;


import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.ParentMeal;
import com.example.foodplanner.Network.ApiServices;
import com.example.foodplanner.Network.NetworkCallback;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint("CheckResult")
public class RandomRemoteDataSourceImpl implements RandomRemoteDataSource {
    private static final String TAG = "CategoryRemoteDataSourc";

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    private ApiServices service;

    private Meal meal;

    public Meal getMeal() {
        return meal;
    }

    private static RandomRemoteDataSourceImpl client = null;

    private RandomRemoteDataSourceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        service = retrofit.create(ApiServices.class);
    }

    public static RandomRemoteDataSourceImpl getInstance() {
        if (client == null) {
            client = new RandomRemoteDataSourceImpl();
        }
        return client;
    }


    @Override
    public void makeNetworkCallback(NetworkCallback callback) {
        Observable<ParentMeal> observable = service.getRandomMeal();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        parentMeal -> callback.onSuccessResultsRandomMeal(parentMeal.getMeals().get(0)),
                        throwable -> callback.onFailureResult(throwable.getMessage()),
                        () -> {}
                );

    }

    @Override
    public void getMealWithName(NetworkCallback callback, String name) {

        Observable<ParentMeal> observable =service.getMealUsingName(name);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        parentMeal-> callback.onSuccessResultsRandomMeal(parentMeal.getMeals().get(0)),
                        throwable -> Log.i(TAG, "getMealWithName: "+throwable.getMessage()),
                        ()->{}

                );


    }


    @Override
    public void getMeals(NetworkCallback callback, String name, String type) {

        Observable<ParentMeal> observable;
        if (type.equalsIgnoreCase("c")) {
            Log.i(TAG, "getMeals: " + type);
            observable = service.getMealsFilteredBasedOnCategory(name);
        } else {
            observable = service.getMealsFilteredBasedOnIngredient(name);
        }

        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe( parentMeal->
                        callback.onSuccessResultsMealS(parentMeal.getMeals()),
                        throwable -> Log.i(TAG, "getMeals: "+throwable.getMessage()),
                        ()->{})
        ;

    }
}
