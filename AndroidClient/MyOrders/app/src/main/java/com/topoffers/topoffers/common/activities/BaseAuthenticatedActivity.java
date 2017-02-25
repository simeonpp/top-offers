package com.topoffers.topoffers.common.activities;

import android.content.Intent;
import android.os.Bundle;

import com.orm.SugarRecord;
import com.topoffers.topoffers.common.helpers.AuthenticationHelpers;
import com.topoffers.topoffers.common.models.AuthenticationCookie;

public class BaseAuthenticatedActivity extends BaseActivity {
    protected AuthenticationCookie authenticationCookie;

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
        }
    }
}
