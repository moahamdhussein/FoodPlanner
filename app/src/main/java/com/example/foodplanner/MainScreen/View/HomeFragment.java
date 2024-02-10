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
import android.widget.Toast;


import com.example.foodplanner.MainScreen.model.Category;
import com.example.foodplanner.MainScreen.model.HomeRepository;
import com.example.foodplanner.MainScreen.model.Ingredients;
import com.example.foodplanner.MainScreen.model.Meal;
import com.example.foodplanner.MainScreen.presenter.HomePresenter;
import com.example.foodplanner.Network.Ingredients.IngredientsRemoteDataSourceImpl;
import com.example.foodplanner.Network.Random.RandomRemoteDataSourceImpl;
import com.example.foodplanner.Network.category.CategoryRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IHome{


    RecyclerView categoryRecyclerView,ingredientRecyclerView;

    CategoryAdapter categoryAdapter;

    IngredientAdapter ingredientAdapter;

    LinearLayoutManager layoutManager , linearLayoutManager;
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
        categoryRecyclerView = view.findViewById(R.id.category_recycler_view);
        ivRandomMeal = view.findViewById(R.id.iv_random_meal);
        tvRandomMealTitle =view.findViewById(R.id.tv_random_meal_title);
        tv_RandomMealCategory =view.findViewById(R.id.tv_random_meal_category);
        ingredientRecyclerView =view.findViewById(R.id.my_ingredients_view);

        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);


        categoryRecyclerView.setLayoutManager(layoutManager);
        ingredientRecyclerView.setLayoutManager(linearLayoutManager);


        categoryAdapter = new CategoryAdapter(getContext(), new ArrayList<>());
        categoryRecyclerView.setAdapter(categoryAdapter);

        ingredientAdapter = new IngredientAdapter(getContext() ,new ArrayList<>());
        ingredientRecyclerView.setAdapter(ingredientAdapter);

        HomePresenter presenter = new HomePresenter(this,
                HomeRepository.getInstance(CategoryRemoteDataSourceImpl.getInstance(),
                        RandomRemoteDataSourceImpl.getInstance(),
                IngredientsRemoteDataSourceImpl.getInstance()));
        presenter.getCategory();
        presenter.getRandomMeal();
        presenter.getAllIngredient();
    }

    @Override
    public void showData(List<Category> categories) {
        categoryAdapter.setList(categories);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void setRandomMeal(Meal meal) {
        tvRandomMealTitle.setText(meal.getStrMeal());
        tv_RandomMealCategory.setText("Category : " + meal.getStrCategory());
        Picasso.get().load(meal.getStrMealThumb()).into(ivRandomMeal);
        Log.i(TAG, "setRandomMeal: "+meal.getStrMealThumb());
    }
    @Override
    public void setIngredientData(List<Ingredients> ingredients) {
        ingredientAdapter.setList(ingredients.subList(0,20));
        ingredientAdapter.notifyDataSetChanged();
    }
}