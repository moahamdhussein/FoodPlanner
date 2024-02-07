package com.example.foodplanner.MainScreen.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodplanner.MainScreen.model.Category;
import com.example.foodplanner.MainScreen.model.CategoryRepository;
import com.example.foodplanner.MainScreen.presenter.CategoryPresenter;
import com.example.foodplanner.Network.CategoryRemoteDataSource;
import com.example.foodplanner.Network.CategoryRemoteDataSourceImpl;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements ICategory {
    RecyclerView recyclerView ;

    CategoryRepository repository;

    HomeAdapter adapter;
    LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomeAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(adapter);
        CategoryRemoteDataSource remoteDataSource = CategoryRemoteDataSourceImpl.getInstance();
        repository = CategoryRepository.getInstance(remoteDataSource);
        CategoryPresenter presenter = new CategoryPresenter(this,repository);
        presenter.getCategory();

    }

    @Override
    public void showData(List<Category> categories) {
        adapter.setList(categories);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showErrorMessage(String error) {

    }
}