package com.topoffers.topoffers.seller.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.topoffers.topoffers.R;

public class SellerOrderHistory extends AppCompatActivity {
    public static final String INTENT_ORDER_PRODUCT_ID = "intent_order_product_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_order_history);
    }
}
