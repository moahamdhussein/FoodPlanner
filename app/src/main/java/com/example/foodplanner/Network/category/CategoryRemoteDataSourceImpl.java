package com.example.foodplanner.Network.category;

import static com.facebook.FacebookSdk.getCacheDir;

import android.content.Context;

import com.example.foodplanner.model.ParentCategories;
import com.example.foodplanner.Network.ApiServices;
import com.example.foodplanner.Network.NetworkCallback;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryRemoteDataSourceImpl implements CategoryRemoteDataSource {
    private static final String TAG = "CategoryRemoteDataSourc";

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    private ApiServices service;

    private static CategoryRemoteDataSourceImpl client = null;

    private CategoryRemoteDataSourceImpl(Context context) {
        int size = 10*1024*1024;
        Cache cache = new Cache(context.getCacheDir(),size);
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
    }

    public static CategoryRemoteDataSourceImpl getInstance(Context context) {
        if (client == null) {
            client = new CategoryRemoteDataSourceImpl(context);
        }
        return client;
    }

    @Override
    public void makeNetworkCallback(NetworkCallback callback) {
        Observable<ParentCategories> observable = service.getAllCategories();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        parentCategories -> callback.onSuccessResults(parentCategories.getCategories()),
                        throwable -> callback.onFailureResult("Error on observe"),
                        () -> {}
                );


    }
}
