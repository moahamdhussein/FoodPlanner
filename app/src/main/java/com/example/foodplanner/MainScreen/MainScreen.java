package com.example.foodplanner.MainScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.foodplanner.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainScreen extends AppCompatActivity  {

    BottomNavigationView bottomNavigationView;
    View view;
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        navController = Navigation.findNavController(this,R.id.nav_host_main_screen_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.favouriteFragment){
                    navController.navigate(R.id.favouriteFragment);

                    return true;
                }else if (item.getItemId() == R.id.homeFragment){
                    navController.navigate(R.id.homeFragment);
                    return true;
                }else if (item.getItemId() == R.id.searchFragment){
                    navController.navigate(R.id.searchFragment);

                    return true;
                }else if (item.getItemId() ==R.id.planningFragment){
                    navController.navigate(R.id.planningFragment);

                    return true;
                }else {
                    return false;
                }
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp()||super.onSupportNavigateUp();
    }
}