package com.example.foodplanner.MainScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;


import com.example.foodplanner.DataBase.MealLocalDataSourceImpl;
import com.example.foodplanner.InterNetConnectivity;
import com.example.foodplanner.Network.Random.RandomRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainScreen extends AppCompatActivity implements InterNetConnectivity {

    private BottomNavigationView bottomNavigationView;
    private View view;
    private NavController navController;

    SharedPreferences sharedPreferences;
    private boolean isConnected ;
    private static final String TAG = "MainScreen";
    private InternetConnection internetConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        isConnected=true;
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        navController = Navigation.findNavController(this, R.id.nav_host_main_screen_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        FloatingActionButton fabExit = findViewById(R.id.fab_exit);
        internetConnection = new InternetConnection(this);
        registerReceiver(internetConnection, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        sharedPreferences = getSharedPreferences("setting", MODE_PRIVATE);
        Boolean isGuest = sharedPreferences.getBoolean("isGuest",false);
        Boolean isLoggedIn= sharedPreferences.getBoolean("loggedInUser",false);
        Log.i(TAG, "onCreate: "+isConnected + "\t"+isLoggedIn);
        if (isConnected &&isLoggedIn){
           RandomRemoteDataSourceImpl.getInstance(this).getDataFromFireBase();
        }
        fabExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected&&!isGuest) {
                    MealLocalDataSourceImpl localDataSource =  MealLocalDataSourceImpl.getInstance(MainScreen.this);
                    localDataSource.deleteAllData();
                    FirebaseAuth.getInstance().signOut();
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isGuest",false);
                editor.putBoolean("loggedInUser",false);
                editor.apply();
                finish();
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.i(TAG, "onNavigationItemSelected: "+isConnected);
                if (item.getItemId() == R.id.favouriteFragment) {
                    if (!isGuest){
                        navigateToFragment(R.id.favouriteFragment);

                    }else {
                        showGuestDialog();
                        return false;
                    }

                } else if (item.getItemId() == R.id.homeFragment) {
                    if (isConnected) {
                        navigateToFragment(R.id.homeFragment);
                    } else {
                        showOfflineDialog();
                        return false;
                    }
                } else if (item.getItemId() == R.id.searchFragment) {
                    if (isConnected) {
                        navigateToFragment(R.id.searchFragment);
                    } else {
                        showOfflineDialog();
                        return false;
                    }

                } else if (item.getItemId() == R.id.planningFragment) {
                    if (!isGuest){
                        navigateToFragment(R.id.planningFragment);
                    }else {
                        showGuestDialog();
                        return false;
                    }

                } else if (item.getItemId() == R.id.settingFragment) {
                    if (isConnected &&!isGuest) {
                        navigateToFragment(R.id.settingFragment);
                    } else if (isGuest){
                        showGuestDialog();
                        return false;
                    }else {
                        showOfflineDialog();
                        return false;
                    }

                }
                return true;

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (!navController.navigateUp()) {
            super.onBackPressed();
        }
    }

    private void navigateToFragment(int fragmentId) {
        if (navController.getCurrentDestination().getId() != fragmentId) {
            navController.popBackStack(R.id.homeFragment, false); // Consider the necessity based on your app's flow
            navController.navigate(fragmentId);
        }
    }

    void showOfflineDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainScreen.this);
        builder.setTitle(getString(R.string.internet_error)).setMessage(R.string.offline_mode_content).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
    void showGuestDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainScreen.this);
        builder.setTitle(getString(R.string.guest_dialog_title)).setMessage(getString(R.string.guest_dialog_content)).setPositiveButton(getString(R.string.login), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isGuest",false);
                editor.putBoolean("loggedInUser",false);
                editor.apply();
                finish();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }



    @Override
    public void onNetworkConnected() {
        isConnected = true;
    }

    @Override
    public void onNetworkDisconnected() {
        Log.i(TAG, "onNetworkDisconnected: ");
        isConnected = false;
//        setOfflineMode();
    }
}