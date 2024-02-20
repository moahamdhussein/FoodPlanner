package com.example.foodplanner.MainActivity.LoginScreen.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.foodplanner.DataBase.MealLocalDataSourceImpl;
import com.example.foodplanner.MainActivity.LoginScreen.Presenter.LoginPresenter;
import com.example.foodplanner.MainScreen.MainScreen;
import com.example.foodplanner.Network.Random.RandomRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.model.HomeRepository;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;



public class LoginFragment extends Fragment {


    Button btnSignUp ,btnGoogle,btnGuest;
    TextView tv_Login;

    View view;

    GoogleSignInClient googleSignInClient;

    LoginPresenter presenter;

    private static final int RC_SIGN_IN = 123;

    private static final String TAG = "LoginFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        GoogleSignInOptions gos = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(requireContext(),gos);

        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        btnSignUp = view.findViewById(R.id.btn_signup);
        tv_Login = view.findViewById(R.id.tv_login);
        btnGoogle = view.findViewById(R.id.btn_google);
        btnGuest = view.findViewById(R.id.btn_guest);
        presenter = new LoginPresenter(HomeRepository.getInstance(RandomRemoteDataSourceImpl.getInstance(getContext()), MealLocalDataSourceImpl.getInstance(getContext())));


        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();
            }
        });
        btnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("setting", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isGuest",true);
                editor.putBoolean("loggedInUser",false);
                editor.apply();
                startActivity(new Intent(getContext(),MainScreen.class));
            }
        });

        tv_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(view).navigate(LoginFragmentDirections.actionLoginFragmentToLoginFormFragment());
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(LoginFragmentDirections.actionLoginFragmentToSingupFragment());
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult();
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (Exception e) {
                Snackbar.make(requireView(),"Google sign in failed",Snackbar.LENGTH_SHORT).show();

            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("setting",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("loggedInUser",true);
                        editor.putBoolean("isGuest",false);
                        editor.apply();
                        Intent intent = new Intent(getContext(), MainScreen.class);
                        startActivity(intent);

                    } else {
                        Snackbar.make(requireView(),"failed to login",Snackbar.LENGTH_SHORT).show();
                    }
                });
    }

    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}