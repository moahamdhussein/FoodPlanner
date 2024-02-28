package com.example.foodplanner.FavoriteList.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.DataBase.MealLocalDataSourceImpl;
import com.example.foodplanner.FavoriteList.Presenter.FavouritePresenterImpl;
import com.example.foodplanner.FavoriteList.Presenter.IFavouritePresenter;
import com.example.foodplanner.Network.Random.RemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.model.pojos.Meal;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;

public class FavouriteFragment extends Fragment implements IFavouriteFragment, OnRemoveClick ,OnFavouriteItemClick{

    RecyclerView recyclerFavouriteList;

    GridLayoutManager gridLayoutManager;

    FavouriteAdapter adapter;

    IFavouritePresenter presenter;

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
        adapter = new FavouriteAdapter(getContext(),new ArrayList<>(),this,this);
        recyclerFavouriteList.setAdapter(adapter);
        presenter = new FavouritePresenterImpl(this, HomeRepository.getInstance(
                 RemoteDataSourceImpl.getInstance(getContext())
                , MealLocalDataSourceImpl.getInstance(getContext())
        ));
        presenter.getLocalData();

    }

    @Override
    public void onRemoveClick(Meal meal){
        presenter.removeItem(meal);
    }
    @Override
    public void setData(List<Meal> meals){
         adapter.setList(meals);
    }

    @Override
    public void onFavItemClick(View view, Meal meal) {
        Navigation.findNavController(view).
                navigate(FavouriteFragmentDirections.actionFavouriteFragmentToInfoFragment(meal.getStrMeal()));
    }
}