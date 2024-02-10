package com.example.foodplanner.Network.category;




import com.example.foodplanner.MainScreen.model.ParentCategories;
import com.example.foodplanner.Network.ApiServices;
import com.example.foodplanner.Network.NetworkCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryRemoteDataSourceImpl implements CategoryRemoteDataSource{
    private static final String TAG = "CategoryRemoteDataSourc";

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    private ApiServices service ;

    private static CategoryRemoteDataSourceImpl client = null;

    private CategoryRemoteDataSourceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiServices.class);
    }
    public static CategoryRemoteDataSourceImpl getInstance() {
        if (client == null) {
            client = new CategoryRemoteDataSourceImpl();
        }
        return client;
    }

    @Override
    public void makeNetworkCallback(NetworkCallback callback) {
        Call<ParentCategories> call = service.getAllCategories();
        call.enqueue(new Callback<ParentCategories>() {
            @Override
            public void onResponse(Call<ParentCategories> call, Response<ParentCategories> response) {
                callback.onSuccessResults(response.body().getCategories());
            }

            @Override
            public void onFailure(Call<ParentCategories> call, Throwable t) {
                callback.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });

    }
}
