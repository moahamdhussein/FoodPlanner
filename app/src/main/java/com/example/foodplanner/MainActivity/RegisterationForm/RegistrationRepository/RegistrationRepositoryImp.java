package com.example.foodplanner.MainActivity.RegisterationForm.RegistrationRepository;

import android.app.Activity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegistrationRepositoryImp {
    FirebaseAuth firebaseAuth ;

    private static final String TAG = "RegistrationRepositoryI";


    public FirebaseUser createNewAccount(String email, String password,Activity activity,OnCompleteListener<AuthResult> onCompleteListener) {

            firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(activity,onCompleteListener);

            return firebaseAuth.getCurrentUser();

    }
    public FirebaseUser login(String email, String password,Activity activity,OnCompleteListener<AuthResult> onCompleteListener) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(activity,onCompleteListener);
        return firebaseAuth.getCurrentUser();

    }


}
