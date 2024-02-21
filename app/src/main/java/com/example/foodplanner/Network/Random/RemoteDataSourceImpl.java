package com.example.foodplanner.Network.Random;


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
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RemoteDataSourceImpl implements RemoteDataSource {
    private static final String TAG = "RandomRemoteDataSourceI";

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    private ApiServices service;

    private Meal meal;

    private FirebaseFirestore firestore;

    private MealLocalDataSourceImpl localDataSource;


    public Meal getMeal() {
        return meal;
    }

    private static RemoteDataSourceImpl client = null;

    private RemoteDataSourceImpl(Context context) {
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

    public static RemoteDataSourceImpl getInstance(Context context) {
        if (client == null) {
            client = new RemoteDataSourceImpl(context);
        }
        return client;
    }

    @Override
    public void backup(List<Meal> meals) {
        Map<String, List<Meal>> userData = new HashMap<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userData.put(user.getUid(), meals);
        final boolean[] isSuccess = new boolean[1];
        firestore.collection("users").document(user.getUid()).set(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
               isSuccess[0] = true;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                isSuccess[0] = false;
            }
        });
//        return isSuccess[0];
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


    }


    @Override
    public Single<ParentCategories> getAllCategory() {
       return service.getAllCategories();
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        parentCategories -> callback.onSuccessResults(parentCategories.getCategories()),
//                        throwable -> callback.onFailureResult("Error on observe"),
//                        () -> {
//                        }
//                );


    }

    @Override
    public Single<ParentIngredients> getAllIngredients() {
       return service.getAllIngredients();
//        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        parentIngredients-> callback.onSuccessResults(parentIngredients.getIngredients()),
//                        throwable -> callback.onFailureResult(throwable.getMessage()),
//                        ()->{}
//                );
//
    }


    @Override
    public Single<ParentMeal> getRandomMeal() {
        Single<ParentMeal> singleMeal = service.getRandomMeal();
        return singleMeal;
//        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//
//                        parentMeal -> callback.onSuccessResults(parentMeal.getMeals().get(0)),
//                        throwable -> callback.onFailureResult(throwable.getMessage()),
//                        () -> {
//                        }
//                );

    }

    @Override
    public Single<ParentMeal> getMealWithName( String name) {

        return   service.getMealUsingName(name);
//        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        parentMeal -> callback.onSuccessResults(parentMeal.getMeals().get(0)),
//                        throwable -> Log.i(TAG, "getMealWithName: " + throwable.getMessage()),
//                        () -> {
//                        }
//
//                );


    }

    @Override
    public Maybe<ParentMeal> searchForAMealWithName(String name, String type) {
        Maybe<ParentMeal> observable;
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
        return observable;
//        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        parentMeal -> callback.onSuccessResults(parentMeal.getMeals()),
//                        throwable -> Log.i(TAG, "getMealWithName: " + throwable.getMessage()),
//                        () -> {
//                        }
//
//                );
    }

    @Override
    public Single<ParentArea> getAllCountries() {
        return service.getAllCountries();
//        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        parentArea -> callback.onSuccessResults(parentArea.getCountries()),
//                        throwable -> Log.i(TAG, "getAllCountries: " + throwable.getMessage())
//                );
    }


    @Override
    public Maybe<ParentMeal> getMeals(String name, String type) {

        Maybe<ParentMeal> maybeMeal;
        if (type.equalsIgnoreCase("c")) {
            Log.i(TAG, "getMeals: " + type);
            maybeMeal = service.getMealsFilteredBasedOnCategory(name);
        } else if (type.equalsIgnoreCase("i")) {
            maybeMeal = service.getMealsFilteredBasedOnIngredient(name);
        } else {
            maybeMeal = service.getMealsFilteredBasedOnArea(name);
        }
        return maybeMeal;

//        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(parentMeal ->
//                                callback.onSuccessResults(parentMeal.getMeals()),
//                        throwable -> Log.i(TAG, "getMeals: " + throwable.getMessage()),
//                        () -> {
//                        })
//        ;

    }


}
