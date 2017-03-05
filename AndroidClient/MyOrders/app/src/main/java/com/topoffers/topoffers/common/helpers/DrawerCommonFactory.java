package com.topoffers.topoffers.common.helpers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.topoffers.topoffers.common.fragments.DrawerFragment;
import com.topoffers.topoffers.common.models.DrawerFactoryModel;
import com.topoffers.topoffers.common.models.DrawerFactorySingleItemModel;
import com.topoffers.topoffers.common.models.DrawerItemInfo;
import com.topoffers.topoffers.common.models.LoginResult;
import com.topoffers.topoffers.profile.MyProfileActivity;
import com.topoffers.topoffers.seller.activities.SellerOrderHistoryListActivity;
import com.topoffers.topoffers.seller.activities.SellerProductsListActivity;
import com.topoffers.topoffers.seller.activities.UpdateProductActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Simeon on 5.3.2017 г..
 */

public class DrawerCommonFactory {
    private Context context;
    private View drawerContainer;
    private LoginResult loginResult;
    private DrawerFactoryModel drawerItems;

    public DrawerCommonFactory(Context context, View drawerContainer, LoginResult loginResult, DrawerFactoryModel drawerItems) {
        this.context = context;
        this.drawerContainer = drawerContainer;
        this.loginResult = loginResult;
        this.drawerItems = drawerItems;
    }

    public Fragment getFragment() {
        if (this.drawerContainer != null) {
            ArrayList<DrawerItemInfo> items = new ArrayList<>();

            final HashMap<Integer, Class> mapper = new HashMap<>();

            int count = 1;
            for (DrawerFactorySingleItemModel item : drawerItems.getItems()) {
                items.add(new DrawerItemInfo(count, item.getTitle()));
                mapper.put(count, item.getClassName());
                count++;
            }

            items.add(new DrawerItemInfo(++count, "Logout"));

            final int finalCount = count;
            Fragment drawerFragment =
                    DrawerFragment.createFragment(items, loginResult, new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            Intent intent;
                            int currentId = (int) drawerItem.getIdentifier();

                            if (currentId == finalCount) { // Logout case
                                AuthenticationHelpers.logout(context);
                            } else {
                                intent = new Intent(context, mapper.get(currentId));
                                context.startActivity(intent);
                            }

                            return true;
                        }
                    });

            return drawerFragment;
        }

        return null;
    }
}
