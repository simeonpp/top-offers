package com.topoffers.topoffers.buyer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.topoffers.topoffers.R;
import com.topoffers.topoffers.common.activities.BaseAuthenticatedActivity;

public class BuyerProductsListActivity extends BaseAuthenticatedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_products_list);
    }
}
