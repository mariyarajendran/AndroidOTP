package com.amitshekhar.example.ui.screen.loginscreen;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.amitshekhar.example.R;
import com.amitshekhar.example.data.local.PreferencesHelper;
import com.amitshekhar.example.network.NetworkMvpView;
import com.amitshekhar.example.network.NetworkPresenter;
import com.amitshekhar.example.ui.base.BaseActivity;
import com.amitshekhar.example.ui.screen.dashboardscreen.Dashboard;
import com.amitshekhar.example.ui.screen.signupscreen.SignupActivity;
import com.amitshekhar.example.utils.CommonUtils;
import com.amitshekhar.example.utils.AlertUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginMvpView, NetworkMvpView {

    @Inject
    LoginPresenter<LoginMvpView> mMainPresenter;
    @Inject
    NetworkPresenter<NetworkMvpView> mNetworkPresenter;
    @Inject
    PreferencesHelper preferencesHelper;

    @BindView(R.id.login_btnLoadData)
    Button btnLoadData;

    @BindView(R.id.textedit_login_phone_number)
    EditText edtTxtPhoneNumber;

    CommonUtils commonUtils;
    ModelLoginActivity modelMainActivity;
    AlertUtils alertUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMain();
    }


    void initMain() {
        activityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainPresenter.attachView(getApplicationContext(), this);
        mNetworkPresenter.attachView(getApplicationContext(), this);
        commonUtils = new CommonUtils(getApplicationContext());
        alertUtils = new AlertUtils(LoginActivity.this);
        preferencesHelper = new PreferencesHelper(getApplicationContext());
        modelMainActivity = new ModelLoginActivity(LoginActivity.this, getApplicationContext());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
        mNetworkPresenter.detachView();
    }

    @Override
    public void onSuccessResponseLogin(String msg, final PojoLoginUserDetails pojoLoginUserDetails, int event) {
        if (event == 0) {
            alertUtils.showAlertOtpDialog(pojoLoginUserDetails.user_otp, 0, new AlertUtils.OnPressedOTPResult() {
                @Override
                public void onPress(boolean status) {
                    validateRegisterStatus(pojoLoginUserDetails.user_register_status);
                }
            }, new AlertUtils.ResendOtpListener() {
                @Override
                public void resendOtp() {
                    apiCallBack(1);
                }
            });
        } else if (event == 1) {

        }

    }

    @Override
    public void onFailureResponseLogin(String error, int event) {
        alertUtils.showAlertDialog(error, 0);
    }

    @OnClick(R.id.login_btnLoadData)
    void btnLoadData() {
        validateLoginData();
    }


    public void checkNetworkAndInitApiCallLogin() {
        mNetworkPresenter.getNetworkStatus(getApplicationContext());

    }


    @Override
    public void showNetworkStatus(boolean networkStatus) {
        handleNetworkStatus(networkStatus);
    }


    public void handleNetworkStatus(boolean status) {
        if (status) {
            apiCallBack(0);
        } else {
            commonUtils.showToastLong(getString(R.string.no_network));
        }
    }


    public void navigateRoutes(int events) {
        if (events == 0) {
            commonUtils.navigationRoutes(getApplicationContext(), Dashboard.class);
        }

    }


    public void apiCallBack(int events) {
        if (events == 0) {
            modelMainActivity.parseLoginJsonData();
            mMainPresenter.postLoginRequest(modelMainActivity.jsonObjectLoginData, 0);
        } else if (events == 1) {
            modelMainActivity.parseLoginJsonData();
            mMainPresenter.postLoginRequest(modelMainActivity.jsonObjectLoginData, 1);
        }
    }


    public void validateLoginData() {
        modelMainActivity.phoneNumber = edtTxtPhoneNumber.getText().toString();
        if (modelMainActivity.validateLoginDatas()) {
            checkNetworkAndInitApiCallLogin();
        }
    }

    public void validateRegisterStatus(String registerStatus) {
        if (registerStatus.equalsIgnoreCase("0")) {
            navigateRoutes(0);
        } else if (registerStatus.equalsIgnoreCase("1")) {
            alertUtils.showAlertDialog("success home page", 0);
        }

    }


}
