package com.example.foodplanner.MainScreen.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.airbnb.lottie.LottieAnimationView;
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

import java.lang.annotation.Native;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IHome{


    RecyclerView categoryRecyclerView,ingredientRecyclerView;

    CategoryAdapter categoryAdapter;

    IngredientAdapter ingredientAdapter;

    LinearLayoutManager layoutManager , linearLayoutManager;
    ImageView ivRandomMeal;
    TextView tvRandomMealTitle, tv_RandomMealCategory ,tvCategoryTitle,tvIngredientTitle,tvTrendingFoodTitle;
    View layoutRandomMeal;
    LottieAnimationView loadingBar;

    HomePresenter presenter;
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
        layoutRandomMeal = view.findViewById(R.id.cd_random_meal);
        loadingBar =view.findViewById(R.id.loading_bar);
        tvCategoryTitle = view.findViewById(R.id.tv_category_title);
        tvIngredientTitle = view.findViewById(R.id.tv_ingredients_title);
        tvTrendingFoodTitle = view.findViewById(R.id.tv_trending_food);



        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);


        categoryRecyclerView.setLayoutManager(layoutManager);
        ingredientRecyclerView.setLayoutManager(linearLayoutManager);


        categoryAdapter = new CategoryAdapter(getContext(), new ArrayList<>(),this);
        categoryRecyclerView.setAdapter(categoryAdapter);

        ingredientAdapter = new IngredientAdapter(getContext() ,new ArrayList<>(),this);
        ingredientRecyclerView.setAdapter(ingredientAdapter);



        presenter = new HomePresenter(this,
                HomeRepository.getInstance(CategoryRemoteDataSourceImpl.getInstance(),
                        RandomRemoteDataSourceImpl.getInstance(),
                IngredientsRemoteDataSourceImpl.getInstance()));
        presenter.getCategory();
        presenter.getRandomMeal();
        presenter.getAllIngredient();
        tvCategoryTitle.setVisibility(View.GONE);
        tvIngredientTitle.setVisibility(View.GONE);
        tvTrendingFoodTitle.setVisibility(View.GONE);
        layoutRandomMeal.setVisibility(View.GONE);
        categoryRecyclerView.setVisibility(View.GONE);
        ingredientRecyclerView.setVisibility(View.GONE);

        layoutRandomMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(HomeFragmentDirections
                        .actionHomeFragmentToInfoFragment(tvRandomMealTitle.getText().toString()));
                Log.i(TAG, "onClick: "+tvRandomMealTitle.getText().toString());
            }
        });
    }

    @Override
    public void showData(List<Category> categories) {
        categoryAdapter.setList(categories);
        categoryAdapter.notifyDataSetChanged();
        tvCategoryTitle.setVisibility(View.VISIBLE);
        categoryRecyclerView.setVisibility(View.VISIBLE);
        loadingBar.setVisibility(View.GONE);


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
        layoutRandomMeal.setVisibility(View.VISIBLE);
        tvTrendingFoodTitle.setVisibility(View.VISIBLE);
        loadingBar.setVisibility(View.GONE);

    }
    @Override
    public void setIngredientData(List<Ingredients> ingredients) {
        ingredientAdapter.setList(ingredients.subList(0,20));
        ingredientAdapter.notifyDataSetChanged();
        ingredientRecyclerView.setVisibility(View.VISIBLE);
        tvIngredientTitle.setVisibility(View.VISIBLE);
        loadingBar.setVisibility(View.GONE);

    }

    @Override
    public void onDailyMailClick(Meal meal) {
        Toast.makeText(getContext(), meal.getIdMeal(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void categoryClick(View view,String name) {
        Navigation.findNavController(view).navigate(HomeFragmentDirections
                .actionHomeFragmentToMealsFragment("c",name));
    }

    @Override
    public void ingredientClick(View view,String name) {
        Navigation.findNavController(view).navigate(HomeFragmentDirections
                .actionHomeFragmentToMealsFragment("i",name));
    }


}