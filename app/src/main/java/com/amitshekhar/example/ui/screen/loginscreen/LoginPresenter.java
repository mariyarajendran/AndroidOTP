

package com.amitshekhar.example.ui.screen.loginscreen;

import com.amitshekhar.example.data.listeners.DataListener;
import com.amitshekhar.example.data.ServiceRequest;
import com.amitshekhar.example.data.local.PreferencesHelper;
import com.amitshekhar.example.ui.base.BasePresenter;

import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by amitshekhar on 13/01/17.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {
    ServiceRequest serviceRequest;
    PreferencesHelper preferencesHelper;

    @Inject
    public LoginPresenter(ServiceRequest serviceRequest, PreferencesHelper preferencesHelper) {
        this.serviceRequest = serviceRequest;
        this.preferencesHelper = preferencesHelper;
    }


    @Override
    public void postLoginRequest(final JSONObject jsonObject, final int event) {
        handleLoaderShow(event);
        serviceRequest.postLoginApiRequest(getContext(), jsonObject, event, new DataListener() {
            @Override
            public void onResponse(JSONObject data) {
                try {
                    handleLoaderHide(event);
                    validateResponseData(data.getBoolean("status"), data.getString("message"), data, event);
                } catch (Exception e) {
                    handleLoaderHide(event);
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                handleLoaderHide(event);
                getMvpView().onFailureResponseLogin(error, 0);
            }
        });

    }

    public void validateResponseData(boolean status, String msg, JSONObject data, int event) {
        if (status) {
            paresJsonData(data, msg, event);
        } else {
            getMvpView().onFailureResponseLogin(msg, 0);
        }
    }

    void handleLoaderShow(int event) {
        if (event == 0) {
            showLoader();
        }
    }

    void handleLoaderHide(int event) {
        if (event == 0) {
            hideLoader();
        }
    }



    public void paresJsonData(JSONObject data, String msg, int event) {
        try {
            PojoLoginUserDetails pojoLoginUserDetails = new PojoLoginUserDetails();
            String user_details = data.getString("user_details");
            JSONObject jsonObjectUserDetails = new JSONObject(user_details);
            pojoLoginUserDetails.setUser_id(jsonObjectUserDetails.getString("user_id"));
            pojoLoginUserDetails.setUser_register_status(jsonObjectUserDetails.getString("user_register_status"));
            pojoLoginUserDetails.setUser_mobile_number(jsonObjectUserDetails.getString("user_mobile_number"));
            pojoLoginUserDetails.setUser_otp(jsonObjectUserDetails.getString("user_otp"));
            setLoginResponseSession(pojoLoginUserDetails);
            getMvpView().onSuccessResponseLogin(msg, pojoLoginUserDetails, event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setLoginResponseSession(PojoLoginUserDetails pojoLoginUserDetails) {
        preferencesHelper.putLoginSessionPojoDatas(pojoLoginUserDetails);
    }


    public void showLoader() {
        getMvpView().showLoading();
    }


    public void hideLoader() {
        getMvpView().hideLoading();
    }
}
