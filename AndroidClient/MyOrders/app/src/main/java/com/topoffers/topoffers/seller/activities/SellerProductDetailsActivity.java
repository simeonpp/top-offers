package com.topoffers.topoffers.seller.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.topoffers.data.base.IData;
import com.topoffers.data.base.IImageData;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.TopOffersApplication;
import com.topoffers.topoffers.common.activities.BaseAuthenticatedActivity;
import com.topoffers.topoffers.common.fragments.DrawerFragment;
import com.topoffers.topoffers.common.fragments.ProductDetailsFragment;
import com.topoffers.topoffers.common.models.DrawerItemInfo;
import com.topoffers.topoffers.common.models.Product;
import com.topoffers.topoffers.profile.MyProfileActivity;
import com.topoffers.topoffers.seller.fragments.SellerProductDetailsExtraFragment;
import com.topoffers.topoffers.seller.helpers.DrawerFactory;

import java.util.ArrayList;

import javax.inject.Inject;

public class SellerProductDetailsActivity extends BaseAuthenticatedActivity {
    @Inject
    public IData<Product> productData;

    @Inject
    public IImageData imageData;

    private int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_product_details);

        Intent intent = this.getIntent();
        productId = intent.getIntExtra(ProductDetailsFragment.INTENT_PRODUCT_KEY, 0);

        this.setupDrawer();
        this.initProductDetailsFragment();
        this.initProductDetailsExtras();
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
        ProductDetailsFragment productDetailsFragment = ProductDetailsFragment.create(productId, this.productData, this.imageData, this.authenticationCookie);
        this.getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_seller_product_details, productDetailsFragment)
            .commit();
    }

    private void initProductDetailsExtras() {
        SellerProductDetailsExtraFragment fragment = SellerProductDetailsExtraFragment.create(productId, this.productData, this.authenticationCookie);
        this.getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_seller_product_extras, fragment)
            .commit();
    }
}
