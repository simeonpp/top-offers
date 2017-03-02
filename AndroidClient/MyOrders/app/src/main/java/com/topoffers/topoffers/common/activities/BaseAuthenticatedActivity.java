package com.topoffers.topoffers.common.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.common.fragments.DrawerFragment;
import com.topoffers.topoffers.common.helpers.AuthenticationHelpers;
import com.topoffers.topoffers.common.models.AuthenticationCookie;
import com.topoffers.topoffers.common.models.DrawerItemInfo;
import com.topoffers.topoffers.common.models.LoginResult;
import com.topoffers.topoffers.seller.activities.SellerProductsListActivity;

import java.util.ArrayList;

public abstract class BaseAuthenticatedActivity extends BaseActivity {
    protected AuthenticationCookie authenticationCookie;
    protected LoginResult loginResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.checkAuthentication();
        this.setupDrawer();
    }

    private void checkAuthentication() {
        authenticationCookie = AuthenticationHelpers.getAuthenticationCookie();
        Intent intent = AuthenticationHelpers.checkAuthentication(this, authenticationCookie);
        if (intent != null) {
            this.startActivity(intent);
        } else {
            loginResult = AuthenticationHelpers.getLoginResult();
        }
    }

    protected void setupDrawer() {
        View drawerContainer = this.findViewById(R.id.container_drawer);
        if (drawerContainer != null) {


            ArrayList<DrawerItemInfo> items = new ArrayList<>();

            items.add(new DrawerItemInfo(1, "Books"));
            items.add(new DrawerItemInfo(2, "Tabs"));
            items.add(new DrawerItemInfo(3, "Another"));
            items.add(new DrawerItemInfo(4, "Another 2"));

            Fragment drawerFragment =
                    DrawerFragment.createFragment(items, new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            switch ((int) drawerItem.getIdentifier()) {
                                case 1:
                                    Intent intent = new Intent(BaseAuthenticatedActivity.this, SellerProductsListActivity.class);
                                    BaseAuthenticatedActivity.this.startActivity(intent);
                                    break;
                            }

                            return true;
                        }
                    });

            this.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_drawer, drawerFragment)
                    .commit();
        }
    }
}
