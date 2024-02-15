package com.example.foodplanner.Meals.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.DataBase.MealLocalDataSourceImpl;
import com.example.foodplanner.model.HomeRepository;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.Meals.Presenter.MealsPresenterImpl;
import com.example.foodplanner.Network.Ingredients.IngredientsRemoteDataSourceImpl;
import com.example.foodplanner.Network.Random.RandomRemoteDataSourceImpl;
import com.example.foodplanner.Network.category.CategoryRemoteDataSourceImpl;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;


public class MealsFragment extends Fragment implements IMealsFragment {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    MealsAdapter mealsAdapter;

    MealsPresenterImpl presenter;
    private static final String TAG = "MealsFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.my_rv_meals);
        layoutManager = new LinearLayoutManager(getContext());


        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mealsAdapter = new MealsAdapter(getContext(),new ArrayList<>());
        recyclerView.setAdapter(mealsAdapter);

        presenter =new MealsPresenterImpl(this, HomeRepository.getInstance(CategoryRemoteDataSourceImpl.getInstance(),
                RandomRemoteDataSourceImpl.getInstance(),
                IngredientsRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext())));


        presenter.getMeals(MealsFragmentArgs.fromBundle(getArguments()).getQueryName()
                ,MealsFragmentArgs.fromBundle(getArguments()).getTypeOfSender());


    }
    @Override
    public void setMealsList(List<Meal> meals){
        mealsAdapter.setList(meals);
    }




}