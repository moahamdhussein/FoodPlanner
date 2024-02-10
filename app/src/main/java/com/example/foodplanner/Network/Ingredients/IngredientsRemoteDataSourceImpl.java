package com.example.foodplanner.Network.Ingredients;

import android.util.Log;

import com.example.foodplanner.MainScreen.model.ParentIngredients;
import com.example.foodplanner.Network.ApiServices;
import com.example.foodplanner.Network.NetworkCallback;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IngredientsRemoteDataSourceImpl implements  IngredientsRemoteDataSource{
   private static final String TAG = "IngredientsRemoteDataSo";

   private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

   private ApiServices service ;

   private static IngredientsRemoteDataSourceImpl client = null;

   private IngredientsRemoteDataSourceImpl() {
      Retrofit retrofit = new Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .build();
      service = retrofit.create(ApiServices.class);
   }
   public static IngredientsRemoteDataSourceImpl getInstance() {
      if (client == null) {
         client = new IngredientsRemoteDataSourceImpl();
      }
      return client;
   }

   @Override
   public void makeNetworkCallback(NetworkCallback callback) {
      Call<ParentIngredients> call = service.getAllIngredients();
      call.enqueue(new Callback<ParentIngredients>() {
         @Override
         public void onResponse(Call<ParentIngredients> call, Response<ParentIngredients> response) {
            Log.i(TAG, "onResponse: "+response.body().getIngredients().size());
            callback.onSuccessResultsIngredients(response.body().getIngredients());
         }

         @Override
         public void onFailure(Call<ParentIngredients> call, Throwable t) {
            callback.onFailureResult(t.getMessage());
            t.printStackTrace();
         }
      });

   }

}
