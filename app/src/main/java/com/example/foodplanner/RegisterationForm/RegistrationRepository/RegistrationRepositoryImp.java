package com.example.foodplanner.RegisterationForm.RegistrationRepository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodplanner.LoginScreen.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

class RegistrationRepositoryImp {
    FirebaseAuth firebaseAuth = null;

    private static final String TAG = "RegistrationRepositoryI";


    public FirebaseAuth getInstance() {
        if (firebaseAuth == null) {
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;
    }

    public void createNewAccount(String email, String password, Context context) {

    }

}
