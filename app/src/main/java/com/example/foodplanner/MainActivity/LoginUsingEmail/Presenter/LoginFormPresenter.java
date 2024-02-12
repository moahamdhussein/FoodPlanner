package com.example.foodplanner.MainActivity.LoginUsingEmail.Presenter;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodplanner.MainActivity.LoginUsingEmail.View.ILoginForm;
import com.example.foodplanner.MainActivity.RegisterationForm.RegistrationRepository.RegistrationRepositoryImp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseUser;

public class LoginFormPresenter implements OnCompleteListener, ILoginFormPresenter {
    private RegistrationRepositoryImp repositoryImp;

    private static final String TAG = "LoginFormPresenter";


    private Activity activity;
    private ILoginForm view;

    public FirebaseUser firebaseUser;

    public LoginFormPresenter(Activity activity, ILoginForm view) {
        this.activity = activity;
        this.view = view;

    }

    @Override
    public void login(String name, String password) {
        repositoryImp = new RegistrationRepositoryImp();
        firebaseUser = repositoryImp.login(name, password, activity, this);
        Log.i(TAG, "Login To an Account: test " + firebaseUser.getEmail());
    }

    @Override
    public void onComplete(@NonNull Task task) {
        if (task.isSuccessful()) {
            view.onSuccessCompleteLogin();
            Log.i(TAG, "onComplete: done");
        } else {
            Log.i(TAG, "onComplete: Error"+task.getException().toString());
        }
    }
}
