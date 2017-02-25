package com.topoffers.topoffers.seller.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.orm.SugarContext;
import com.orm.SugarRecord;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.TopOffersApplication;
import com.topoffers.topoffers.common.activities.BaseActivity;
import com.topoffers.topoffers.common.activities.BaseAuthenticatedActivity;
import com.topoffers.topoffers.common.helpers.AuthenticationHelpers;
import com.topoffers.topoffers.common.models.AuthenticationCookie;

public class SellerProductsListActivity extends BaseAuthenticatedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_products_list);

        AuthenticationCookie cookie = AuthenticationHelpers.getAuthenticationCookie();
        Toast
                .makeText(this, cookie.getUsername() + " - " + cookie.getId() + " - " + cookie.getRole(), Toast.LENGTH_LONG)
                .show();
    }
}
