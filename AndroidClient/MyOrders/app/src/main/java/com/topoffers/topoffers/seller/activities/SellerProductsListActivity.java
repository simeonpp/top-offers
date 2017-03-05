package com.topoffers.topoffers.seller.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.topoffers.data.base.IData;
import com.topoffers.data.base.IImageData;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.TopOffersApplication;
import com.topoffers.topoffers.common.activities.BaseAuthenticatedActivity;
import com.topoffers.topoffers.common.fragments.DrawerFragment;
import com.topoffers.topoffers.common.fragments.LogoutFragment;
import com.topoffers.topoffers.common.fragments.ProductsListFragment;
import com.topoffers.topoffers.common.models.DrawerItemInfo;
import com.topoffers.topoffers.common.models.Product;
import com.topoffers.topoffers.profile.MyProfileActivity;
import com.topoffers.topoffers.seller.helpers.DrawerFactory;

import java.util.ArrayList;

import javax.inject.Inject;

public class SellerProductsListActivity extends BaseAuthenticatedActivity {

    @Inject
    public IData<Product> productData;

    @Inject
    public IImageData imageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_products_list);

        this.setupDrawer();
        this.initTitle();
        this.initProductsListFragment();
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

    private void initProductsListFragment() {
        ProductsListFragment productListFragment = ProductsListFragment.create(this.productData, this.imageData, this.authenticationCookie, SellerProductDetailsActivity.class);
        this.getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_seller_products_list, productListFragment)
            .commit();
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
}
