

package com.amitshekhar.example.ui.screen.loginscreen;

import com.amitshekhar.example.ui.base.MvpPresenter;

import org.json.JSONObject;

/**
 * Created by amitshekhar on 13/01/17.
 */

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void postLoginRequest(JSONObject jsonObject, int event);

}
