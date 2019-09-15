package com.amitshekhar.example.ui.screen.signupscreen;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.amitshekhar.example.R;
import com.amitshekhar.example.data.local.PreferencesHelper;
import com.amitshekhar.example.network.NetworkMvpView;
import com.amitshekhar.example.network.NetworkPresenter;
import com.amitshekhar.example.ui.base.BaseActivity;
import com.amitshekhar.example.ui.screen.locationscreen.LocationScreen;
import com.amitshekhar.example.ui.screen.loginscreen.PojoLoginUserDetails;
import com.amitshekhar.example.utils.AlertUtils;
import com.amitshekhar.example.utils.CommonUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends BaseActivity implements SignupMvpView, NetworkMvpView {

    @Inject
    SignupPresenter<SignupMvpView> signupPresenter;
    @Inject
    NetworkPresenter<NetworkMvpView> mNetworkPresenter;
    @Inject
    PreferencesHelper preferencesHelper;


    CommonUtils commonUtils;
    ModelSignupActivity modelSignupActivity;
    AlertUtils alertUtils;

    @BindView(R.id.common_header_txtview_title)
    TextView txtviewCommonHeaderTitle;

    @BindView(R.id.textedit_signup_username)
    EditText editTextUserName;

    @BindView(R.id.textedit_signup_emailid)
    EditText editTextEmailID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMain();

    }

    void initMain() {
        activityComponent().inject(this);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        signupPresenter.attachView(getApplicationContext(), this);
        mNetworkPresenter.attachView(getApplicationContext(), this);
        commonUtils = new CommonUtils(getApplicationContext());
        preferencesHelper = new PreferencesHelper(getApplicationContext());
        modelSignupActivity = new ModelSignupActivity(SignupActivity.this, getApplicationContext());
        alertUtils = new AlertUtils(SignupActivity.this);

        //headerTitle
        txtviewCommonHeaderTitle.setText(getResources().getString(R.string.update_details));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        signupPresenter.detachView();
        mNetworkPresenter.detachView();
    }

    @OnClick(R.id.signup_btn_signup)
    void btnSignUpData() {
        validateSignUpData();

    }


    @Override
    public void showNetworkStatus(boolean networkStatus) {
        handleNetworkStatus(networkStatus);
    }

    @Override
    public void showSignupResponseData(String msg, PojoSignupUserDetails pojoSignupUserDetails, int event) {
        commonUtils.showToastLong(msg);
        navigateRoutes(0);
    }

    @Override
    public void showResponseError(String error, int event) {
        alertUtils.showAlertDialog(error, 0);
    }

    public void handleNetworkStatus(boolean status) {
        if (status) {
            modelSignupActivity.parseSignupJsonData();
            signupPresenter.postSignupRequest(modelSignupActivity.jsonObjectSignupData, 0);
        } else {
            commonUtils.showToastLong(getString(R.string.no_network));
        }
    }


    public void validateSignUpData() {
        modelSignupActivity.userName = editTextUserName.getText().toString();
        modelSignupActivity.emailId = editTextEmailID.getText().toString();
        if (modelSignupActivity.validateSignupDatas()) {
            getSessionLoginDetails();
            checkNetworkAndInitApiCallSignup();
        }
    }


    public void navigateRoutes(int events) {
        if (events == 0) {
            commonUtils.navigationRoutes(getApplicationContext(), LocationScreen.class);
        }

    }

    public void getSessionLoginDetails() {
        PojoLoginUserDetails pojoLoginUserDetails = new PojoLoginUserDetails();
        pojoLoginUserDetails = preferencesHelper.getLoginSessionPojoDatas();
        modelSignupActivity.userId = pojoLoginUserDetails.getUser_id();
        modelSignupActivity.mobileNumber = pojoLoginUserDetails.getUser_mobile_number();
    }

    public void checkNetworkAndInitApiCallSignup() {
        mNetworkPresenter.getNetworkStatus(getApplicationContext());

    }


}
