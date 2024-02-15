package com.example.foodplanner.FavoriteList.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.DataBase.MealLocalDataSourceImpl;
import com.example.foodplanner.FavoriteList.Presenter.FavouritePresenterImpl;
import com.example.foodplanner.Network.Ingredients.IngredientsRemoteDataSourceImpl;
import com.example.foodplanner.Network.Random.RandomRemoteDataSourceImpl;
import com.example.foodplanner.Network.category.CategoryRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;

public class FavouriteFragment extends Fragment implements IFavouriteFragment, OnRemoveClick {

    RecyclerView recyclerFavouriteList;

    GridLayoutManager gridLayoutManager;

    FavouriteAdapter adapter;

    FavouritePresenterImpl presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerFavouriteList = view.findViewById(R.id.rv_favourite_list);
        gridLayoutManager = new GridLayoutManager(getContext(),2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerFavouriteList.setLayoutManager(gridLayoutManager);
        adapter = new FavouriteAdapter(getContext(),new ArrayList<>(),this);
        recyclerFavouriteList.setAdapter(adapter);
        presenter = new FavouritePresenterImpl(this, HomeRepository.getInstance(
                CategoryRemoteDataSourceImpl.getInstance(), RandomRemoteDataSourceImpl.getInstance()
                , IngredientsRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext())
        ));
        presenter.getLocalData();

    }

    @Override
    public void onRemoveClick(Meal meal){
        presenter.removeItem(meal);
    }

    @Override
    public void setData(Flowable<List<Meal>> meals){

        meals.observeOn(AndroidSchedulers.mainThread())
                .subscribe(items -> adapter.setList(items));
    }
}