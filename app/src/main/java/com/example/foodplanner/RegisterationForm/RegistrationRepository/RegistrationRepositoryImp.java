package com.example.foodplanner.RegisterationForm.RegistrationRepository;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
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
