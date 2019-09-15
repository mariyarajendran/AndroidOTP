package com.amitshekhar.example.network;

import android.content.Context;

import com.amitshekhar.example.ui.base.BasePresenter;


import javax.inject.Inject;


public class NetworkPresenter<V extends NetworkMvpView> extends BasePresenter<V> implements NetworkMvpPresenter<V> {


    private final NewtworkManager mNewtworkManager;

    @Inject
    public NetworkPresenter(NewtworkManager mNewtworkManager) {
        this.mNewtworkManager = mNewtworkManager;
    }

    @Override
    public void getNetworkStatus(Context context) {
        mNewtworkManager.getNetworkStatus(context, new NetworkListener() {
            @Override
            public void networkStatus(boolean status) {
                getMvpView().showNetworkStatus(status);
            }
        });
    }
}
