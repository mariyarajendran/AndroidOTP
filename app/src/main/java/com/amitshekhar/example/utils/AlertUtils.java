package com.amitshekhar.example.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amitshekhar.example.R;
import com.amitshekhar.example.data.local.PreferencesHelper;
import com.amitshekhar.example.ui.screen.loginscreen.PojoLoginUserDetails;
import com.amitshekhar.example.ui.screen.signupscreen.SignupActivity;

import java.util.Timer;
import java.util.TimerTask;


public class AlertUtils {

    Activity activity;
    CommonUtils commonUtils;
    PreferencesHelper preferencesHelper;

    public interface OnPressedOTPResult {
        void onPress(boolean status);
    }

    public interface ResendOtpListener {
        void resendOtp();
    }

    public AlertUtils(Activity activity) {
        this.activity = activity;
        this.commonUtils = new CommonUtils(activity);
        this.preferencesHelper = new PreferencesHelper(activity);
    }


    public void showAlertDialog(String msg, int event) {

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.custom_alert_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        TextView txtViewMsg = (TextView) dialog.findViewById(R.id.custom_alert_txtview_title);
        txtViewMsg.setText(msg);

        Button btnOkay = (Button) dialog.findViewById(R.id.custom_alert_btn_submit);
        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

            }
        });
    }


    public String getSessionOTP() {
        PojoLoginUserDetails pojoLoginUserDetails = new PojoLoginUserDetails();
        pojoLoginUserDetails = preferencesHelper.getLoginSessionPojoDatas();
        return pojoLoginUserDetails.getUser_otp();
    }

    public void showAlertOkayCancelDialog(String msg, int event) {

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.custom_alert_dialog_okay_cancel);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        TextView txtViewMsg = (TextView) dialog.findViewById(R.id.custom_alert_okay_cancel_txtview_title);
        txtViewMsg.setText(msg);

        Button btnOkay = (Button) dialog.findViewById(R.id.custom_alert_okay_cancel_btn_ok);
        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });

        Button btnCancel = (Button) dialog.findViewById(R.id.custom_alert_okay_cancel_btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });


    }


    public void showAlertOtpDialog(final String otp, int event, final OnPressedOTPResult onPressedOTPResult, final ResendOtpListener resendOtpListener) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.custom_alert_dialog_otp);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        final TextInputLayout textInputLayoutOtp = (TextInputLayout) dialog.findViewById(R.id.custom_otp_alert_txtinput_otp);
        final EditText editTextOtp = (EditText) dialog.findViewById(R.id.textedit_login_phone_number);
        editTextOtp.setText(getSessionOTP());
        final RelativeLayout relativeLayoutResendOtp = (RelativeLayout) dialog.findViewById(R.id.custom_otp_alert_rv_resendotp);

        final TextView textViewResendOtp = (TextView) dialog.findViewById(R.id.custom_otp_alert_txtview_resendotp);


        final Button btnProceed = (Button) dialog.findViewById(R.id.custom_otp_alert_btn_proceed);
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateOtp(getSessionOTP(), editTextOtp.getText().toString()) == 1) {
                    textInputLayoutOtp.setError(activity.getString(R.string.enter_otp));
                } else if (validateOtp(getSessionOTP(), editTextOtp.getText().toString()) == 2) {
                    textInputLayoutOtp.setError(activity.getString(R.string.enter_valid_otp));
                } else if (validateOtp(getSessionOTP(), editTextOtp.getText().toString()) == 3) {
                    textInputLayoutOtp.setError(null);
                    dismissDialog(dialog);
                    //navigateRoutes(0);
                    onPressedOTPResult.onPress(true);
                }
            }
        });

        relativeLayoutResendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendOtpHintHandler(textViewResendOtp);
                resendOtpListener.resendOtp();
            }
        });


        editTextOtp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (getSessionOTP().equalsIgnoreCase(charSequence.toString())) {
                    textInputLayoutOtp.setError(null);
                } else if (charSequence.length() <= 3) {
                    btnProceed.setBackground(activity.getResources().getDrawable(R.drawable.custom_round_button_inactive));
                    btnProceed.setEnabled(false);
                    textInputLayoutOtp.setError(null);
                } else if (charSequence.length() == 4) {
                    btnProceed.setBackground(activity.getResources().getDrawable(R.drawable.custom_round_button));
                    btnProceed.setEnabled(true);
                    textInputLayoutOtp.setError(null);
                } else if (charSequence.length() == 0) {
                    textInputLayoutOtp.setError(activity.getString(R.string.enter_otp));
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void dismissDialog(Dialog dialog) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public int validateOtp(String otp, String enteredOtp) {
        int otpStatus = 0;
        if (enteredOtp.equalsIgnoreCase("")) {
            otpStatus = 1;
        } else if (!otp.equalsIgnoreCase(enteredOtp)) {
            otpStatus = 2;
        } else {
            otpStatus = 3;
        }
        return otpStatus;
    }

    public void navigateRoutes(int events) {
        if (events == 0) {
            commonUtils.navigationRoutes(activity, SignupActivity.class);
        }

    }

    public void resendOtpHintHandler(final TextView textViewResendOtp) {
        textViewResendOtp.setText(activity.getResources().getString(R.string.resend_otp_successfully));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewResendOtp.setText(activity.getResources().getString(R.string.resend_otp));
            }
        }, 2000);
    }

}
