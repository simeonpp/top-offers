package com.topoffers.topoffers.seller.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.topoffers.data.base.IData;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.TopOffersApplication;
import com.topoffers.topoffers.common.activities.BaseAuthenticatedActivity;
import com.topoffers.topoffers.common.fragments.LogoutFragment;
import com.topoffers.topoffers.common.fragments.ProductsListFragment;
import com.topoffers.topoffers.common.models.Product;

import javax.inject.Inject;

public class SellerProductsListActivity extends BaseAuthenticatedActivity {

    @Inject
    public IData<Product> productData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_products_list);

        this.initTitle();
        this.initProductsList();
        this.initLogoutFragment();
    }

    @Override
    protected void init() {
        super.init();
        ((TopOffersApplication) getApplication()).getComponent().inject(this);
    }

    private void initTitle() {
        TextView tvTitle = (TextView) this.findViewById(R.id.tv_seller_products_list_title);
        String title = String.format("%s's products for sale", this.loginResult.getFirstName());
        tvTitle.setText(title);
    }

    private void initProductsList() {
        ProductsListFragment productListFragment = ProductsListFragment.create(this.productData, this.authenticationCookie);
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
