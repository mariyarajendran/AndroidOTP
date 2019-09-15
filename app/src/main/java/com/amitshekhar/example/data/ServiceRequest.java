package com.amitshekhar.example.data;

import android.content.Context;
import android.util.Log;

import com.amitshekhar.example.data.listeners.DataListener;
import com.amitshekhar.example.data.remote.AllApiInterface;
import com.amitshekhar.example.data.remote.MySingleton;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class ServiceRequest implements AllApiInterface {

    @Inject
    ServiceRequest() {
    }

    public static String BASE_URL = "http://eecshopping.com/";


    public static String LOGIN_API_CALL_URL = BASE_URL + "tomato/api/json/login";

    @Override
    public void postLoginApiRequest(Context context, JSONObject jsonObject, int event, final DataListener dataListener) {
        Log.i("Url", ": " + LOGIN_API_CALL_URL);
        Log.i("jsonObject: ", "" + jsonObject.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, LOGIN_API_CALL_URL, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.i("responseData: ", "" + response);
                            dataListener.onResponse(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("errorResponse: ", "" + error);
                        dataListener.onError(error.toString());
                    }
                });
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public static String REGISTER_API_CALL_URL = BASE_URL + "tomato/api/json/signup";

    @Override
    public void postSignupApiRequest(Context context, JSONObject jsonObject, int event, final DataListener dataListener) {
        Log.i("Url", ": " + REGISTER_API_CALL_URL);
        Log.i("jsonObject: ", "" + jsonObject.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, REGISTER_API_CALL_URL, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.i("responseData: ", "" + response);
                            dataListener.onResponse(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("errorResponse: ", "" + error);
                        dataListener.onError(error.toString());
                    }
                });
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
