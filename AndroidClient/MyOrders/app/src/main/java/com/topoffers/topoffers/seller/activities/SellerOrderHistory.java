package com.topoffers.topoffers.seller.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.topoffers.data.base.IData;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.TopOffersApplication;
import com.topoffers.topoffers.common.activities.BaseAuthenticatedActivity;
import com.topoffers.topoffers.common.fragments.OrdersListFragment;
import com.topoffers.topoffers.common.models.Order;
import com.topoffers.topoffers.common.models.Product;

import javax.inject.Inject;

public class SellerOrderHistory extends BaseAuthenticatedActivity {
    public static final String INTENT_ORDER_PRODUCT_ID = "intent_order_product_id";

    @Inject
    public IData<Order> orderData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_order_history);

        this.initTitle();
        this.initOrdersListFragment();
    }

    @Override
    protected void init() {
        super.init();
        ((TopOffersApplication) getApplication()).getComponent().inject(this);
    }

    private void initTitle() {
        TextView tvTitle = (TextView) this.findViewById(R.id.tv_seller_orders_list_title);
        String title = String.format("%s's orders history", this.loginResult.getFirstName());
        tvTitle.setText(title);
    }

    private void initOrdersListFragment() {
        OrdersListFragment ordersListFragment = OrdersListFragment.create(this.orderData, this.authenticationCookie, SellerOrderHistory.class);
        this.getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_seller_orders_list, ordersListFragment)
            .commit();

    }
}
