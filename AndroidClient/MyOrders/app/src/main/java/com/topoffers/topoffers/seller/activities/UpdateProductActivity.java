package com.topoffers.topoffers.seller.activities;

import android.content.Intent;
import android.os.Bundle;

import com.topoffers.data.base.IData;
import com.topoffers.data.base.IImageData;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.TopOffersApplication;
import com.topoffers.topoffers.common.activities.BaseAuthenticatedActivity;
import com.topoffers.topoffers.common.models.Product;
import com.topoffers.topoffers.seller.fragments.UpdateProductFragment;

import javax.inject.Inject;

public class UpdateProductActivity extends BaseAuthenticatedActivity {
    public static final String INTENT_UPDATE_PRODUCT_ID = "intent_update_product_id";

    @Inject
    public IData<Product> productData;

    @Inject
    public IImageData imageData;

    private int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        Intent intent = this.getIntent();
        productId = intent.getIntExtra(INTENT_UPDATE_PRODUCT_ID, 0);

        this.setProductFragment();
    }

    @Override
    protected void init() {
        super.init();
        ((TopOffersApplication) getApplication()).getComponent().inject(this);
    }

    private void setProductFragment() {
        UpdateProductFragment updateProductFragment = UpdateProductFragment.create(this.productData, this.imageData, this.authenticationCookie, productId);
        this.getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_update_product, updateProductFragment)
            .commit();
    }
}
