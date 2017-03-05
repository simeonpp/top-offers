package com.topoffers.topoffers.seller.helpers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.common.fragments.DrawerFragment;
import com.topoffers.topoffers.common.helpers.AuthenticationHelpers;
import com.topoffers.topoffers.common.helpers.DrawerCommonFactory;
import com.topoffers.topoffers.common.models.DrawerFactoryModel;
import com.topoffers.topoffers.common.models.DrawerFactorySingleItemModel;
import com.topoffers.topoffers.common.models.DrawerItemInfo;
import com.topoffers.topoffers.common.models.LoginResult;
import com.topoffers.topoffers.profile.MyProfileActivity;
import com.topoffers.topoffers.seller.activities.SellerOrderHistoryListActivity;
import com.topoffers.topoffers.seller.activities.SellerProductsListActivity;
import com.topoffers.topoffers.seller.activities.UpdateProductActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Simeon on 5.3.2017 г..
 */

public class DrawerFactory {
    private Context context;
    private View drawerContainer;
    private LoginResult loginResult;

    public DrawerFactory(Context context, View drawerContainer, LoginResult loginResult) {
        this.context = context;
        this.drawerContainer = drawerContainer;
        this.loginResult = loginResult;
    }

    public Fragment getFragment() {
        DrawerFactorySingleItemModel itemMyProducts = new DrawerFactorySingleItemModel("My Products", SellerProductsListActivity.class);
        DrawerFactorySingleItemModel itemAddProduct = new DrawerFactorySingleItemModel("Add Product", UpdateProductActivity.class);
        DrawerFactorySingleItemModel itemMyOrders = new DrawerFactorySingleItemModel("My Orders", SellerOrderHistoryListActivity.class);
        DrawerFactorySingleItemModel itemMyProfile = new DrawerFactorySingleItemModel("My Profile", MyProfileActivity.class);

        DrawerFactorySingleItemModel[] itemArray = {itemMyProducts, itemAddProduct, itemMyOrders, itemMyProfile};
        DrawerFactoryModel drawerFactoryModel = new DrawerFactoryModel(new ArrayList<DrawerFactorySingleItemModel>(Arrays.asList(itemArray)));

        DrawerCommonFactory drawerCommonFactory = new DrawerCommonFactory(this.context, this.drawerContainer, this.loginResult, drawerFactoryModel);
        return drawerCommonFactory.getFragment();
    }
}
