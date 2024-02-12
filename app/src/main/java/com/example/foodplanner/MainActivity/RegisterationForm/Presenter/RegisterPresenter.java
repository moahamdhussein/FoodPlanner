package com.example.foodplanner.MainActivity.RegisterationForm.Presenter;

import android.app.Activity;

import androidx.annotation.NonNull;


import com.example.foodplanner.MainActivity.RegisterationForm.RegistrationRepository.RegistrationRepositoryImp;
import com.example.foodplanner.MainActivity.RegisterationForm.View.ISingUp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

public class RegisterPresenter implements OnCompleteListener, IRegisterPresenter {
    private RegistrationRepositoryImp repositoryImp;

    private static final String TAG = "RegisterPresenter";



   private Activity activity;
   private ISingUp view;

    public FirebaseUser firebaseUser;

    public RegisterPresenter(Activity activity,ISingUp view) {
        this.activity = activity;
        this.view =view;

    }

    @Override
    public void createNewAccount(String name, String password){
        repositoryImp = new RegistrationRepositoryImp();
        firebaseUser =  repositoryImp.createNewAccount(name,password, activity,this);

    }
    @Override
    public void onComplete(@NonNull Task task) {
            if (task.isSuccessful()){
                view.onSuccessComplete();

            }else {

            }
    }
}
