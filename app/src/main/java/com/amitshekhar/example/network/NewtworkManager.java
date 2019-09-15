package com.amitshekhar.example.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.amitshekhar.example.ui.base.BaseActivity;

import javax.inject.Inject;

public class NewtworkManager {

    @Inject
    public NewtworkManager() {

    }

    public void getNetworkStatus(Context context, final NetworkListener networkListener) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        networkListener.networkStatus(activeNetwork != null && activeNetwork.isConnectedOrConnecting());

    }

}
