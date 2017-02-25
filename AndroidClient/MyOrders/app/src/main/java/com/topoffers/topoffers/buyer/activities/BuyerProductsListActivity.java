package com.topoffers.topoffers.buyer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.topoffers.data.base.IData;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.TopOffersApplication;
import com.topoffers.topoffers.common.activities.BaseAuthenticatedActivity;
import com.topoffers.topoffers.common.fragments.LogoutFragment;
import com.topoffers.topoffers.common.fragments.ProductsListFragment;
import com.topoffers.topoffers.common.helpers.AuthenticationHelpers;
import com.topoffers.topoffers.common.models.AuthenticationCookie;
import com.topoffers.topoffers.common.models.Product;

import javax.inject.Inject;

public class BuyerProductsListActivity extends BaseAuthenticatedActivity {

    @Inject
    public IData<Product> productData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_products_list);

        this.initProductsListFragment();
        this.initLogoutFragment();
    }

    @Override
    protected void init() {
        super.init();
        ((TopOffersApplication) getApplication()).getComponent().inject(this);
    }

    private void initProductsListFragment() {
        ProductsListFragment productListFragment = ProductsListFragment.create(this.productData, this.authenticationCookie, BuyerProductDetailsActivity.class);
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_seller_products_list, productListFragment)
                .commit();
    }

    private void initLogoutFragment() {
        LogoutFragment logoutFragment = LogoutFragment.createFragment();
        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.logout_container_fragment, logoutFragment)
                .commit();
    }
}
