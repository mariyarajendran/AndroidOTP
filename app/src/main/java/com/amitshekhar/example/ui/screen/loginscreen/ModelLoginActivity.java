package com.amitshekhar.example.ui.screen.loginscreen;

import android.app.Activity;
import android.content.Context;

import com.amitshekhar.example.R;
import com.amitshekhar.example.utils.AlertUtils;
import com.amitshekhar.example.utils.CommonUtils;

import org.json.JSONObject;

public class ModelLoginActivity {
    JSONObject jsonObjectLoginData;
    boolean valiateStatus = false;
    String phoneNumber = "";
    Context context;
    CommonUtils commonUtils;
    AlertUtils alertUtils;

    public ModelLoginActivity(Activity activity, Context context) {
        this.context = context;
        this.commonUtils = new CommonUtils(context);
        this.alertUtils = new AlertUtils(activity);
    }


    public void parseLoginJsonData() {
        try {
            jsonObjectLoginData = new JSONObject();
            jsonObjectLoginData.put("user_mobilenumber", phoneNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateValidateStatus(boolean status) {
        this.valiateStatus = status;
    }

    public boolean validateLoginDatas() {
        if (phoneNumber.equalsIgnoreCase("")) {
            updateValidateStatus(false);
            alertUtils.showAlertDialog(context.getString(R.string.enter_phone_number), 0);
        } else {
            updateValidateStatus(true);
        }
        return valiateStatus;
    }


}
