package com.topoffers.topoffers.buyer.activities;

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
import com.topoffers.topoffers.buyer.fragments.BuyerProductDetailsExtraFragment;
import com.topoffers.topoffers.common.activities.BaseAuthenticatedActivity;
import com.topoffers.topoffers.common.fragments.DrawerFragment;
import com.topoffers.topoffers.common.fragments.ProductDetailsFragment;
import com.topoffers.topoffers.common.models.DrawerItemInfo;
import com.topoffers.topoffers.common.models.Product;
import com.topoffers.topoffers.profile.MyProfileActivity;
import com.topoffers.topoffers.seller.fragments.SellerProductDetailsExtraFragment;

import java.util.ArrayList;

import javax.inject.Inject;

public class BuyerProductDetailsActivity extends BaseAuthenticatedActivity {
    @Inject
    public IData<Product> productData;

    @Inject
    public IImageData imageData;

    private int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_product_details);

        Intent intent = this.getIntent();
        productId = intent.getIntExtra(ProductDetailsFragment.INTENT_PRODUCT_KEY, 0);

        this.initProductDetailsFragment();
        this.initProductDetailsExtras();
        this.setupDrawer();
    }

    @Override
    protected void init() {
        super.init();
        ((TopOffersApplication) getApplication()).getComponent().inject(this);
    }

    protected void setupDrawer() {
        View drawerContainer = this.findViewById(R.id.container_drawer);
        if (drawerContainer != null) {
            ArrayList<DrawerItemInfo> items = new ArrayList<>();

            items.add(new DrawerItemInfo(1, "Products"));
            items.add(new DrawerItemInfo(2, "My Profile"));

            Fragment drawerFragment =
                    DrawerFragment.createFragment(items, super.loginResult, new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            Intent intent;
                            switch ((int) drawerItem.getIdentifier()) {
                                case 1:
                                    intent = new Intent(BuyerProductDetailsActivity.this, BuyerProductsListActivity.class);
                                    startActivity(intent);
                                    break;
                                case 2:
                                    intent = new Intent(BuyerProductDetailsActivity.this, MyProfileActivity.class);
                                    startActivity(intent);
                                    break;
                            }

                            return true;
                        }
                    });

            this.getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_drawer, drawerFragment)
                    .commit();
        }
    }

    private void initProductDetailsFragment() {
        ProductDetailsFragment productDetailsFragment = ProductDetailsFragment.create(productId, this.productData, this.imageData, this.authenticationCookie);
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_buyer_product_details, productDetailsFragment)
                .commit();
    }

    private void initProductDetailsExtras() {
        BuyerProductDetailsExtraFragment fragment = new BuyerProductDetailsExtraFragment();
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_buyer_product_extras, fragment)
                .commit();
    }
}
