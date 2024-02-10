package com.example.foodplanner.MainScreen.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.foodplanner.MainScreen.model.Category;
import com.example.foodplanner.MainScreen.model.HomeRepository;
import com.example.foodplanner.MainScreen.model.Meal;
import com.example.foodplanner.MainScreen.presenter.HomePresenter;
import com.example.foodplanner.Network.Random.RandomRemoteDataSourceImpl;
import com.example.foodplanner.Network.category.CategoryRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IHome{


    RecyclerView recyclerView;

    HomeAdapter adapter;
    LinearLayoutManager layoutManager;
    ImageView ivRandomMeal;
    TextView tvRandomMealTitle, tv_RandomMealCategory;

    private static final String TAG = "HomeFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.my_recycler_view);
        ivRandomMeal = view.findViewById(R.id.iv_random_meal);
        tvRandomMealTitle =view.findViewById(R.id.tv_random_meal_title);
        tv_RandomMealCategory =view.findViewById(R.id.tv_random_meal_category);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomeAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(adapter);
        HomePresenter presenter = new HomePresenter(this,
                HomeRepository.getInstance(CategoryRemoteDataSourceImpl.getInstance(),
                        RandomRemoteDataSourceImpl.getInstance()));
        presenter.getCategory();
        presenter.getRandomMeal();
    }

    @Override
    public void showData(List<Category> categories) {
        adapter.setList(categories);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String error) {

    }

    @Override
    public void setRandomMeal(Meal meal) {
        tvRandomMealTitle.setText(meal.getStrMeal());
        tv_RandomMealCategory.setText("Category : "+meal.getStrCategory());
        Picasso.get().load(meal.getStrMealThumb()).into(ivRandomMeal);
        Log.i(TAG, "setRandomMeal: "+meal.getStrMealThumb());
    }
}