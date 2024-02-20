package com.example.foodplanner.MainScreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.foodplanner.InterNetConnectivity;


public class InternetConnection extends BroadcastReceiver {
    InterNetConnectivity listener;
     private static final String TAG = "InternetConnection";


    public InternetConnection(InterNetConnectivity listener) {
        this.listener = listener;
    }

    @Override
        public void onReceive(final Context context, final Intent intent) {
            String action = intent.getAction();

            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                if (isOnline(context)) {
                    listener.onNetworkConnected();
                } else {
                    listener.onNetworkDisconnected();
                }
            }
        }

        private boolean isOnline(Context context) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
            return false;
        }
    }
