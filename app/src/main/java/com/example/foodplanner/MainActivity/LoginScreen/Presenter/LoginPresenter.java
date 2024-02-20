package com.example.foodplanner.MainActivity.LoginScreen.Presenter;


import com.example.foodplanner.model.IHomeRepository;

public class LoginPresenter {
    IHomeRepository repository;

    public LoginPresenter(IHomeRepository repository) {
        this.repository = repository;
    }
    public void getDataFromFireBase(){
        repository.downloadBackup();
    }
}
