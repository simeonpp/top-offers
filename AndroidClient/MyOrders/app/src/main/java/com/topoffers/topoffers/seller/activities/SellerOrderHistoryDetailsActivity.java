package com.topoffers.topoffers.seller.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.topoffers.data.base.IData;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.TopOffersApplication;
import com.topoffers.topoffers.common.activities.BaseAuthenticatedActivity;
import com.topoffers.topoffers.common.fragments.OrderDetailsFragment;
import com.topoffers.topoffers.common.models.Order;

import javax.inject.Inject;

public class SellerOrderHistoryDetailsActivity extends BaseAuthenticatedActivity {
    @Inject
    public IData<Order> orderData;
    private int orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_order_history_details);

        Intent intent = this.getIntent();
        orderId = intent.getIntExtra(OrderDetailsFragment.INTENT_ORDER_KEY, 0);

        this.initProductDetailsFragment();
    }

    @Override
    protected void init() {
        super.init();
        ((TopOffersApplication) getApplication()).getComponent().inject(this);
    }

    private void initProductDetailsFragment() {
        OrderDetailsFragment orderDetailsFragment = OrderDetailsFragment.create(orderId, this.orderData, this.authenticationCookie);
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_seller_order_details, orderDetailsFragment)
                .commit();
    }
}
