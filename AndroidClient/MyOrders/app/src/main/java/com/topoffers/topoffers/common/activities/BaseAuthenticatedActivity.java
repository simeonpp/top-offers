package com.topoffers.topoffers.common.activities;

import android.content.Intent;
import android.os.Bundle;

import com.topoffers.topoffers.common.helpers.AuthenticationHelpers;
import com.topoffers.topoffers.common.models.AuthenticationCookie;
import com.topoffers.topoffers.common.models.LoginResult;

public abstract class BaseAuthenticatedActivity extends BaseActivity {
    protected AuthenticationCookie authenticationCookie;
    protected LoginResult loginResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.checkAuthentication();
    }

    private void checkAuthentication() {
        authenticationCookie = AuthenticationHelpers.getAuthenticationCookie();
        Intent intent = AuthenticationHelpers.checkAuthentication(this, authenticationCookie);
        if (intent != null) {
            this.startActivity(intent);
        } else {
            loginResult = AuthenticationHelpers.getLoginResult();
        }
    }
}
