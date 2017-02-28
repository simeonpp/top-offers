package com.topoffers.topoffers.seller.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.topoffers.data.base.IData;
import com.topoffers.data.base.IImageData;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.TopOffersApplication;
import com.topoffers.topoffers.common.activities.BaseAuthenticatedActivity;
import com.topoffers.topoffers.common.fragments.ProductDetailsFragment;
import com.topoffers.topoffers.common.models.Product;
import com.topoffers.topoffers.seller.fragments.SellerProductDetailsExtraFragment;

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

        this.initProductDetailsFragment();
        this.initProductDetailsExtras();
    }

    @Override
    protected void init() {
        super.init();
        ((TopOffersApplication) getApplication()).getComponent().inject(this);
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
