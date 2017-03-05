package com.topoffers.topoffers.seller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.topoffers.data.base.IData;
import com.topoffers.data.base.IImageData;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.TopOffersApplication;
import com.topoffers.topoffers.common.activities.BaseAuthenticatedActivity;
import com.topoffers.topoffers.common.fragments.DrawerFragment;
import com.topoffers.topoffers.common.fragments.OrdersListFragment;
import com.topoffers.topoffers.common.models.DrawerItemInfo;
import com.topoffers.topoffers.common.models.Order;
import com.topoffers.topoffers.profile.MyProfileActivity;
import com.topoffers.topoffers.seller.helpers.DrawerFactory;

import java.util.ArrayList;

import javax.inject.Inject;

public class SellerOrderHistoryListActivity extends BaseAuthenticatedActivity {
    public static final String INTENT_ORDER_PRODUCT_ID = "intent_order_product_id";

    @Inject
    public IData<Order> orderData;

    @Inject
    public IImageData imageData;

    private int queryProductId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_order_history);

        Intent intent = this.getIntent();
        queryProductId = intent.getIntExtra(INTENT_ORDER_PRODUCT_ID, 0);

        this.setupDrawer();
        this.initTitle();
        this.initOrdersListFragment();
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

    private void initTitle() {
        TextView tvTitle = (TextView) this.findViewById(R.id.tv_seller_orders_list_title);
        String title = "";
        if (this.queryProductId > 0) {
            title = String.format("Product orders history", this.loginResult.getFirstName());
        } else {
            title = String.format("%s's orders history", this.loginResult.getFirstName());
        }
        tvTitle.setText(title);
    }

    private void initOrdersListFragment() {
        OrdersListFragment ordersListFragment = OrdersListFragment.create(this.queryProductId, this.orderData, this.imageData, this.authenticationCookie, SellerOrderHistoryDetailsActivity.class);
        this.getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_seller_orders_list, ordersListFragment)
            .commit();

    }
}
