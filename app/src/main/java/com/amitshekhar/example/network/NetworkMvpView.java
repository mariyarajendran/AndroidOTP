package com.amitshekhar.example.network;

import com.amitshekhar.example.ui.base.MvpView;

public interface NetworkMvpView extends MvpView {

    void showNetworkStatus(boolean networkStatus);
}
