package com.amitshekhar.example.network;

import android.content.Context;

import com.amitshekhar.example.ui.base.MvpPresenter;
import com.amitshekhar.example.ui.base.MvpView;

public interface NetworkMvpPresenter<V extends NetworkMvpView & MvpView> extends MvpPresenter<V> {

    void getNetworkStatus(Context context);
}
