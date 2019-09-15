package com.amitshekhar.example.ui.screen.signupscreen;

import android.app.Activity;
import android.content.Context;

import com.amitshekhar.example.R;
import com.amitshekhar.example.utils.AlertUtils;
import com.amitshekhar.example.utils.CommonUtils;

import org.json.JSONObject;

public class ModelSignupActivity {
    JSONObject jsonObjectSignupData;
    AlertUtils alertUtils;
    Activity activity;
    Context context;
    CommonUtils commonUtils;
    boolean valiateStatus = false;
    String userName = "";
    String emailId = "";
    String userId = "";
    String mobileNumber = "";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    ModelSignupActivity(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
        this.commonUtils = new CommonUtils(context);
        this.alertUtils = new AlertUtils(activity);

    }

    public void updateValidateStatus(boolean status) {
        this.valiateStatus = status;
    }

    public boolean validateSignupDatas() {
        if (userName.equalsIgnoreCase("")) {
            updateValidateStatus(false);
            alertUtils.showAlertDialog(context.getString(R.string.enter_user_name), 0);
        } else if (emailId.equalsIgnoreCase("")) {
            updateValidateStatus(false);
            alertUtils.showAlertDialog(context.getString(R.string.enter_email_id), 0);
        } else if (!emailId.matches(emailPattern)) {
            updateValidateStatus(false);
            alertUtils.showAlertDialog(context.getString(R.string.enter_valid_emailid), 0);
        } else {
            updateValidateStatus(true);
        }
        return valiateStatus;
    }


    public void parseSignupJsonData() {
        try {
            jsonObjectSignupData = new JSONObject();
            jsonObjectSignupData.put("user_id", userId);
            jsonObjectSignupData.put("user_username", userName);
            jsonObjectSignupData.put("user_emailid", emailId);
            jsonObjectSignupData.put("user_mobile_number", mobileNumber);
            jsonObjectSignupData.put("user_lat", "33.444");
            jsonObjectSignupData.put("user_lng", "56.545");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
