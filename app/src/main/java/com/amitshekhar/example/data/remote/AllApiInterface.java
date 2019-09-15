package com.amitshekhar.example.data.remote;

import android.content.Context;

import com.amitshekhar.example.data.listeners.DataListener;

import org.json.JSONObject;

public interface AllApiInterface {

    public void postLoginApiRequest(Context context, JSONObject jsonObject, int event, DataListener dataListener);

    public void postSignupApiRequest(Context context, JSONObject jsonObject, int event, DataListener dataListener);

}
