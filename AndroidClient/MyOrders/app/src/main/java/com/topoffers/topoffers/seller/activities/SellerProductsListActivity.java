package com.topoffers.topoffers.seller.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.topoffers.data.base.IData;
import com.topoffers.data.base.IImageData;
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

    @Inject
    public IImageData imageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_products_list);

        this.initTitle();
        this.initProductsListFragment();
        this.initLogoutFragment();
        this.addTempButtonListener();
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

    private void initLogoutFragment() {
        LogoutFragment logoutFragment = LogoutFragment.createFragment();
        this.getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.logout_container_fragment, logoutFragment)
            .commit();
    }

    private void addTempButtonListener() {
        final Context context = this;
        Button btnCreateNewProduct = (Button) this.findViewById(R.id.btn_temp_add_new_product);

        btnCreateNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateProductActivity.class);
                context.startActivity(intent);
            }
        });

        // Orders list navigation
        Button btnOrdersList = (Button) this.findViewById(R.id.temp_btn_orders);
        btnOrdersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SellerOrderHistoryListActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
