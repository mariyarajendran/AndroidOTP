/*
 *    Copyright (C) 2018 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.amitshekhar.example.ui.screen.signupscreen;

import com.amitshekhar.example.data.ServiceRequest;
import com.amitshekhar.example.data.listeners.DataListener;
import com.amitshekhar.example.data.local.PreferencesHelper;
import com.amitshekhar.example.ui.base.BasePresenter;

import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by amitshekhar on 13/01/17.
 */

public class SignupPresenter<V extends SignupMvpView> extends BasePresenter<V> implements SignupMvpPresenter<V> {
    ServiceRequest serviceRequest;
    PreferencesHelper preferencesHelper;

    @Inject
    public SignupPresenter(ServiceRequest serviceRequest, PreferencesHelper preferencesHelper) {
        this.serviceRequest = serviceRequest;
        this.preferencesHelper = preferencesHelper;
    }


    @Override
    public void postSignupRequest(final JSONObject jsonObject, int event) {
        getMvpView().showLoading();
        serviceRequest.postSignupApiRequest(getContext(), jsonObject, event, new DataListener() {
            @Override
            public void onResponse(JSONObject data) {
                try {
                    getMvpView().hideLoading();
                    validateResponseData(data.getBoolean("status"), data.getString("message"), data);
                    //getMvpView().showData(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                getMvpView().hideLoading();
                getMvpView().showResponseError(error, 0);
            }
        });

    }

    public void validateResponseData(boolean status, String msg, JSONObject data) {
        if (status) {
            paresJsonData(data, msg);
        } else {
            getMvpView().showResponseError(msg, 0);
        }
    }

    public void paresJsonData(JSONObject data, String msg) {
        try {
            PojoSignupUserDetails pojoSignupUserDetails = new PojoSignupUserDetails();
            String user_details = data.getString("user_details");
            JSONObject jsonObjectUserDetails = new JSONObject(user_details);
            pojoSignupUserDetails.setUser_id(jsonObjectUserDetails.getString("user_id"));
            pojoSignupUserDetails.setUser_name(jsonObjectUserDetails.getString("user_name"));
            pojoSignupUserDetails.setUser_mailid(jsonObjectUserDetails.getString("user_mailid"));
            pojoSignupUserDetails.setUser_mobile_number(jsonObjectUserDetails.getString("user_mobile_number"));
            pojoSignupUserDetails.setUser_current_lat(jsonObjectUserDetails.getString("user_current_lat"));
            pojoSignupUserDetails.setUser_current_lng(jsonObjectUserDetails.getString("user_current_lng"));
            setSignupResponseSession(pojoSignupUserDetails);
            getMvpView().showSignupResponseData(msg, pojoSignupUserDetails, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setSignupResponseSession(PojoSignupUserDetails pojoSignupUserDetails) {
        preferencesHelper.putSignupSessionPojoDatas(pojoSignupUserDetails);
    }


}
