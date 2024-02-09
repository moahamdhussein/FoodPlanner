package com.example.foodplanner.MainScreen.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
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

public class HomeActivity extends AppCompatActivity implements IHome {
    RecyclerView recyclerView;

    HomeAdapter adapter;
    LinearLayoutManager layoutManager;
    ImageView ivRandomMeal;
    TextView tvRandomMealTitle, tv_RandomMealCategory;
    private static final String TAG = "HomeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.my_recycler_view);
        ivRandomMeal = findViewById(R.id.iv_random_meal);
        tvRandomMealTitle =findViewById(R.id.tv_random_meal_title);
        tv_RandomMealCategory =findViewById(R.id.tv_random_meal_category);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomeAdapter(this, new ArrayList<>());
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