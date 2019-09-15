package com.amitshekhar.example.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.amitshekhar.example.ui.screen.loginscreen.LoginActivity;

public class CommonUtils {

    Context context;

    public CommonUtils(Context context) {
        this.context = context;
    }


    public void showToastSmall(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void showToastLong(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }


    public void navigationRoutes(Context context, Class routingClass) {
        Intent intent = new Intent(context, routingClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
