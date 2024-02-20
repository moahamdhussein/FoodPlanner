package com.example.foodplanner.Network.Random;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;


import androidx.annotation.NonNull;

import com.example.foodplanner.DataBase.MealLocalDataSourceImpl;
import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.model.pojos.ParentArea;
import com.example.foodplanner.model.pojos.ParentCategories;
import com.example.foodplanner.model.pojos.ParentIngredients;
import com.example.foodplanner.model.pojos.ParentMeal;
import com.example.foodplanner.Network.ApiServices;
import com.example.foodplanner.Network.NetworkCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RandomRemoteDataSourceImpl implements RandomRemoteDataSource {
    private static final String TAG = "RandomRemoteDataSourceI";

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    private ApiServices service;

    private Meal meal;

    private FirebaseFirestore firestore;

    MealLocalDataSourceImpl localDataSource;


    public Meal getMeal() {
        return meal;
    }

    private static RandomRemoteDataSourceImpl client = null;

    private RandomRemoteDataSourceImpl(Context context) {
        int size = 10 * 1024 * 1024;
        Cache cache = new Cache(context.getCacheDir(), size);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        service = retrofit.create(ApiServices.class);
        FirebaseApp.initializeApp(context);
        firestore = FirebaseFirestore.getInstance();
        localDataSource = MealLocalDataSourceImpl.getInstance(context);
    }

    public static RandomRemoteDataSourceImpl getInstance(Context context) {
        if (client == null) {
            client = new RandomRemoteDataSourceImpl(context);
        }
        return client;
    }

    @Override
    public void backup(List<Meal> meals) {
        Map<String, List<Meal>> userData = new HashMap<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userData.put(user.getUid(), meals);
        firestore.collection("users").document(user.getUid()).set(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.i(TAG, "onSuccess: done");
            }
        });
    }

    @Override
    public void getDataFromFireBase() {
        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.i(TAG, "onSuccess: " + user);
        firestore.collection("users").document(user).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot snapshot) {
                if (snapshot != null && snapshot.getData() != null && snapshot.getData().get(user) != null) {
                    List<Map<String, Object>> meal1 = (List<Map<String, Object>>) snapshot.getData().get(user);
                    List<String[]> strings = new ArrayList<>();
                    for (int i = 0; i < meal1.size(); i++) {
                        Meal meal2 = new Meal();
                        meal2.setDbType(meal1.get(i).get("dbType").toString());
                        meal2.setPlanDate(meal1.get(i).get("planDate").toString());
                        meal2.setStrMeal(meal1.get(i).get("strMeal").toString());
                        meal2.setIdMeal(meal1.get(i).get("idMeal").toString());
                        meal2.setStrMealThumb(meal1.get(i).get("strMealThumb").toString());
                        meal2.setStrYoutube(meal1.get(i).get("strYoutube").toString());
                        meal2.setStrCategory(meal1.get(i).get("strCategory").toString());
                        meal2.setStrArea(meal1.get(i).get("strArea").toString());
                        meal2.setStrInstructions(meal1.get(i).get("strInstructions").toString());
                        localDataSource.insertFavouriteMeal(meal2);


                        Log.i(TAG, "onSuccess: " + meal2.getDbType());
                        Log.i(TAG, "onSuccess: " + meal2.getPlanDate());
                        Log.i(TAG, "onSuccess: " + meal2.getStrMeal());
                    }

                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i(TAG, "onFailure: " + e.getMessage());
            }
        });

//        Map<String, Object> map = documentSnapshot.getData();
//        List<Map<String, Object>> mealList = (List<Map<String, Object>>) map.get(user.getUid());
//        for (int i = 0; i < mealList.size(); i++) {
//            Log.i(TAG, "onSuccess: " + i + "    " + mealList.get(i).get("dbType"));
//            meal.setDbType(String.valueOf(mealList.get(i).get("dbType")));
//            meal.setIdMeal(String.valueOf(mealList.get(i).get("idMeal")));
//            meal.setPlanDate(String.valueOf(mealList.get(i).get("planDate")));
//            meal.setStrArea(String.valueOf(mealList.get(i).get("strArea")));
//            meal.setStrCategory(String.valueOf(mealList.get(i).get("strCategory")));
//            meal.setStrInstructions(String.valueOf(mealList.get(i).get("strInstructions")));
//            meal.setStrMeal(String.valueOf(mealList.get(i).get("strMeal")));
//            meal.setStrMealThumb(String.valueOf(mealList.get(i).get("strMealThumb")));
//            meal.setStrYoutube(String.valueOf(mealList.get(i).get("strYoutube")));
//        }
//        Log.i(TAG, "onSuccess: Done");
    }


    @Override
    public void getAllCategory(NetworkCallback callback) {
        Observable<ParentCategories> observable = service.getAllCategories();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        parentCategories -> callback.onSuccessResults(parentCategories.getCategories()),
                        throwable -> callback.onFailureResult("Error on observe"),
                        () -> {
                        }
                );


    }

    @Override
    public void getAllIngredients(NetworkCallback callback) {
        Observable<ParentIngredients> observable = service.getAllIngredients();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        parentIngredients-> callback.onSuccessResultsIngredients(parentIngredients.getIngredients()),
                        throwable -> callback.onFailureResult(throwable.getMessage()),
                        ()->{}
                );
    }


    @Override
    public void makeNetworkCallback(NetworkCallback callback) {
        Observable<ParentMeal> observable = service.getRandomMeal();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        parentMeal -> callback.onSuccessResultsRandomMeal(parentMeal.getMeals().get(0)),
                        throwable -> callback.onFailureResult(throwable.getMessage()),
                        () -> {
                        }
                );

    }

    @Override
    public void getMealWithName(NetworkCallback callback, String name) {

        Observable<ParentMeal> observable = service.getMealUsingName(name);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        parentMeal -> callback.onSuccessResultsRandomMeal(parentMeal.getMeals().get(0)),
                        throwable -> Log.i(TAG, "getMealWithName: " + throwable.getMessage()),
                        () -> {
                        }

                );


    }

    @Override
    public void searchForAMealWithName(NetworkCallback callback, String name, String type) {
        Observable<ParentMeal> observable;
        Log.i(TAG, "searchForAMealWithName: " + type + "    name   " + name);
        if (type.equalsIgnoreCase("s")) {
            observable = service.searchForAMealWithName(name);
        } else if (type.equalsIgnoreCase("i")) {
            observable = service.getMealsFilteredBasedOnIngredient(name);
        } else if (type.equalsIgnoreCase("c")) {
            observable = service.getMealsFilteredBasedOnCategory(name);
        } else {
            observable = service.getMealsFilteredBasedOnArea(name);
        }
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        parentMeal -> callback.onSuccessResultsMealS(parentMeal.getMeals()),
                        throwable -> Log.i(TAG, "getMealWithName: " + throwable.getMessage()),
                        () -> {
                        }

                );
    }

    @Override
    public void getAllCountries(NetworkCallback callback) {
        Observable<ParentArea> observable = service.getAllCountries();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        parentArea -> callback.onSuccessAreaResult(parentArea.getCountries()),
                        throwable -> Log.i(TAG, "getAllCountries: " + throwable.getMessage())
                );
    }


    @Override
    public void getMeals(NetworkCallback callback, String name, String type) {

        Observable<ParentMeal> observable;
        if (type.equalsIgnoreCase("c")) {
            Log.i(TAG, "getMeals: " + type);
            observable = service.getMealsFilteredBasedOnCategory(name);
        } else if (type.equalsIgnoreCase("i")) {
            observable = service.getMealsFilteredBasedOnIngredient(name);
        } else {
            observable = service.getMealsFilteredBasedOnArea(name);
        }

        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(parentMeal ->
                                callback.onSuccessResultsMealS(parentMeal.getMeals()),
                        throwable -> Log.i(TAG, "getMeals: " + throwable.getMessage()),
                        () -> {
                        })
        ;

    }


}
