package com.topoffers.topoffers.seller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.topoffers.data.base.IData;
import com.topoffers.data.base.IImageData;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.TopOffersApplication;
import com.topoffers.topoffers.common.activities.BaseAuthenticatedActivity;
import com.topoffers.topoffers.common.fragments.DrawerFragment;
import com.topoffers.topoffers.common.fragments.OrderDetailsFragment;
import com.topoffers.topoffers.common.models.DrawerItemInfo;
import com.topoffers.topoffers.common.models.Order;
import com.topoffers.topoffers.profile.MyProfileActivity;
import com.topoffers.topoffers.seller.helpers.DrawerFactory;

import java.util.ArrayList;

import javax.inject.Inject;

public class SellerOrderHistoryDetailsActivity extends BaseAuthenticatedActivity {
    @Inject
    public IData<Order> orderData;

    @Inject
    public IImageData imageData;

    private int orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_order_history_details);

        Intent intent = this.getIntent();
        orderId = intent.getIntExtra(OrderDetailsFragment.INTENT_ORDER_KEY, 0);

        this.setupDrawer();
        this.initProductDetailsFragment();
    }

    @Override
    protected void init() {
        super.init();
        ((TopOffersApplication) getApplication()).getComponent().inject(this);
    }
    protected void setupDrawer() {
        View drawerContainer = this.findViewById(R.id.container_drawer);

        DrawerFactory drawerFactory = new DrawerFactory(this, drawerContainer, super.loginResult);
        Fragment drawerFragment = drawerFactory.getFragment();

        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_drawer, drawerFragment)
                .commit();
    }

    private void initProductDetailsFragment() {
        OrderDetailsFragment orderDetailsFragment = OrderDetailsFragment.create(orderId, this.orderData, this.imageData, this.authenticationCookie);
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_seller_order_details, orderDetailsFragment)
                .commit();
    }
}
