package com.example.foodplanner.MainScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
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
        navController = Navigation.findNavController(this,R.id.nav_host_main_screen_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.favouriteFragment){
                    navigateToFragment(R.id.favouriteFragment);
                }else if (item.getItemId() == R.id.homeFragment){
                    navigateToFragment(R.id.homeFragment);
                }else if (item.getItemId() == R.id.searchFragment){
                    navigateToFragment(R.id.searchFragment);
                }else if (item.getItemId() ==R.id.planningFragment){
                    navigateToFragment(R.id.planningFragment);

                }else if (item.getItemId() ==R.id.settingFragment){
                    navigateToFragment(R.id.settingFragment);
                }
                return true;
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp()||super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (!navController.navigateUp()){
            super.onBackPressed();
        }
    }

    private void navigateToFragment(int fragmentId) {
        if (navController.getCurrentDestination().getId() != fragmentId) {
            navController.popBackStack(R.id.homeFragment, false); // Consider the necessity based on your app's flow
            navController.navigate(fragmentId);
        }
    }
}