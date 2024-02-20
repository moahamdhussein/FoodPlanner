package com.example.foodplanner.MainActivity.LoginScreen.Presenter;

import com.example.foodplanner.model.HomeRepository;

public class LoginPresenter {
    HomeRepository repository;

    public LoginPresenter(HomeRepository repository) {
        this.repository = repository;
    }
    public void getDataFromFireBase(){
        repository.downloadBackup();
    }
}
