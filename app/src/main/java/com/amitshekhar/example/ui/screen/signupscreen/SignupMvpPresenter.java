

package com.amitshekhar.example.ui.screen.signupscreen;

import com.amitshekhar.example.ui.base.MvpPresenter;

import org.json.JSONObject;

/**
 * Created by amitshekhar on 13/01/17.
 */

public interface SignupMvpPresenter<V extends SignupMvpView> extends MvpPresenter<V> {

    void postSignupRequest(JSONObject jsonObject, int event);

}
