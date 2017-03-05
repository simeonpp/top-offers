package com.topoffers.topoffers.profile;

import android.os.Bundle;
import android.util.Log;

import com.topoffers.data.base.IData;
import com.topoffers.data.models.Headers;
import com.topoffers.topoffers.TopOffersApplication;
import com.topoffers.topoffers.buyer.activities.BuyerOrderHistoryListActivity;
import com.topoffers.topoffers.buyer.activities.BuyerProductsCart;
import com.topoffers.topoffers.buyer.activities.BuyerProductsListActivity;
import com.topoffers.topoffers.common.activities.BaseAuthenticatedActivity;
import com.topoffers.topoffers.common.fragments.LoadingFragment;
import com.topoffers.topoffers.common.helpers.AuthenticationHelpers;
import com.topoffers.topoffers.common.helpers.DrawerCommonFactory;
import com.topoffers.topoffers.common.models.DrawerFactoryModel;
import com.topoffers.topoffers.common.models.DrawerFactorySingleItemModel;
import com.topoffers.topoffers.common.models.Profile;
import com.topoffers.topoffers.seller.activities.SellerOrderHistoryListActivity;
import com.topoffers.topoffers.seller.activities.SellerProductsListActivity;
import com.topoffers.topoffers.seller.activities.UpdateProductActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import javax.inject.Inject;

public abstract class BaseProfileActivity extends BaseAuthenticatedActivity {
    @Inject
    public IData<Profile> profileData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.initProfile();
    }

    @Override
    protected void init() {
        super.init();
        ((TopOffersApplication) getApplication()).getComponent().inject(this);
    }

    private void initProfile() {
        Headers headers = AuthenticationHelpers.getAuthenticationHeaders(this.authenticationCookie);

        final LoadingFragment loadingFragment = LoadingFragment.create(this, "Preparing profile...");
        loadingFragment.show();

        profileData.getSingle(headers)
            .subscribe(new io.reactivex.functions.Consumer<Profile>() {
                @Override
                public void accept(Profile profile) throws Exception {
                    loadingFragment.hide();
                    profile = profile;
                    onProfileReady(profile);
                }
            });
    }

    protected DrawerFactoryModel getDrawerFactoryModel() {
        String role = super.authenticationCookie.getRole();
        DrawerFactoryModel drawerFactoryModel;

        if (Objects.equals(role, "seller")) {
            DrawerFactorySingleItemModel itemMyProducts = new DrawerFactorySingleItemModel("My Products", SellerProductsListActivity.class);
            DrawerFactorySingleItemModel itemAddProduct = new DrawerFactorySingleItemModel("Add Product", UpdateProductActivity.class);
            DrawerFactorySingleItemModel itemMyOrders = new DrawerFactorySingleItemModel("My Orders", SellerOrderHistoryListActivity.class);
            DrawerFactorySingleItemModel itemMyProfile = new DrawerFactorySingleItemModel("My Profile", MyProfileActivity.class);

            DrawerFactorySingleItemModel[] itemArray = {itemMyProducts, itemAddProduct, itemMyOrders, itemMyProfile};
            drawerFactoryModel = new DrawerFactoryModel(new ArrayList<DrawerFactorySingleItemModel>(Arrays.asList(itemArray)));
        } else {
            DrawerFactorySingleItemModel itemMyProducts = new DrawerFactorySingleItemModel("Products", BuyerProductsListActivity.class);
            DrawerFactorySingleItemModel itemMyProfile = new DrawerFactorySingleItemModel("My Profile", MyProfileActivity.class);
            DrawerFactorySingleItemModel itemAddProduct = new DrawerFactorySingleItemModel("Cart", BuyerProductsCart.class);
            DrawerFactorySingleItemModel itemMyOrders = new DrawerFactorySingleItemModel("My Orders", BuyerOrderHistoryListActivity.class);


            DrawerFactorySingleItemModel[] itemArray = {itemMyProducts, itemAddProduct, itemMyOrders, itemMyProfile};
            drawerFactoryModel = new DrawerFactoryModel(new ArrayList<DrawerFactorySingleItemModel>(Arrays.asList(itemArray)));
        }

        return drawerFactoryModel;
    }

    protected abstract void onProfileReady(Profile profile);

}
