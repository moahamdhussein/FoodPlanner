package com.example.foodplanner.Network.Ingredients;

import com.example.foodplanner.model.ParentIngredients;
import com.example.foodplanner.Network.ApiServices;
import com.example.foodplanner.Network.NetworkCallback;


import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
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
              .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
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

      Observable<ParentIngredients> observable = service.getAllIngredients();
      observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
              .subscribe(
                      parentIngredients-> callback.onSuccessResultsIngredients(parentIngredients.getIngredients()),
                      throwable -> callback.onFailureResult(throwable.getMessage()),
                      ()->{}
      );


   }

}
