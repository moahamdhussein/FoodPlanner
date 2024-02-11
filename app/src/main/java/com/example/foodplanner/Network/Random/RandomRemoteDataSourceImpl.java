package com.example.foodplanner.Network.Random;



import android.util.Log;


import com.example.foodplanner.MainScreen.model.Meal;
import com.example.foodplanner.MainScreen.model.ParentMeal;
import com.example.foodplanner.Network.ApiServices;
import com.example.foodplanner.Network.NetworkCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

 public class RandomRemoteDataSourceImpl implements RandomRemoteDataSource {
    private static final String TAG = "CategoryRemoteDataSourc";

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    private ApiServices service ;

    private Meal meal;

     public Meal getMeal() {
         return meal;
     }

     private static RandomRemoteDataSourceImpl client = null;

    private RandomRemoteDataSourceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
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
        Call<ParentMeal> call = service.getRandomMeal();
        call.enqueue(new Callback<ParentMeal>() {
            @Override
            public void onResponse(Call<ParentMeal> call, Response<ParentMeal> response) {
                meal = response.body().meals.get(0);
                callback.onSuccessResultsRandomMeal(meal);
            }

            @Override
            public void onFailure(Call<ParentMeal> call, Throwable t) {
                callback.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });

    }
}
