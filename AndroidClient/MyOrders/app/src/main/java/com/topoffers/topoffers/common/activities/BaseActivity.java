package com.topoffers.topoffers.common.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.orm.SugarContext;
import com.orm.SugarRecord;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.TopOffersApplication;
import com.topoffers.topoffers.buyer.activities.BuyerProductsListActivity;
import com.topoffers.topoffers.common.helpers.AuthenticationHelpers;
import com.topoffers.topoffers.common.models.AuthenticationCookie;
import com.topoffers.topoffers.login.LoginActivity;
import com.topoffers.topoffers.seller.activities.SellerProductsListActivity;

import java.util.List;
import java.util.Objects;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.init();
    }

    protected void init() {
        SugarContext.init(this); // required by the library
    }
}
