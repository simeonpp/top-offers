package com.topoffers.topoffers.profile;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.topoffers.data.base.IData;
import com.topoffers.data.models.Headers;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.TopOffersApplication;
import com.topoffers.topoffers.buyer.activities.BuyerOrderHistoryListActivity;
import com.topoffers.topoffers.buyer.activities.BuyerProductsCart;
import com.topoffers.topoffers.buyer.activities.BuyerProductsListActivity;
import com.topoffers.topoffers.common.activities.BaseAuthenticatedActivity;
import com.topoffers.topoffers.common.fragments.DrawerFragment;
import com.topoffers.topoffers.common.fragments.LoadingFragment;
import com.topoffers.topoffers.common.helpers.AuthenticationHelpers;
import com.topoffers.topoffers.common.helpers.DrawerCommonFactory;
import com.topoffers.topoffers.common.helpers.Utils;
import com.topoffers.topoffers.common.models.DrawerFactoryModel;
import com.topoffers.topoffers.common.models.DrawerFactorySingleItemModel;
import com.topoffers.topoffers.common.models.DrawerItemInfo;
import com.topoffers.topoffers.common.models.Profile;
import com.topoffers.topoffers.seller.activities.SellerOrderHistoryDetailsActivity;
import com.topoffers.topoffers.seller.activities.SellerOrderHistoryListActivity;
import com.topoffers.topoffers.seller.activities.SellerProductsListActivity;
import com.topoffers.topoffers.seller.activities.UpdateProductActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public class MyProfileActivity extends BaseProfileActivity {

    @Inject
    public IData<Profile> profileData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        this.setupDrawer();
        this.initEditProfileButton();
    }

    @Override
    protected void onProfileReady(Profile profile) {
        TextView tvMyProfileUsername = (TextView) findViewById(R.id.tv_my_profile_username);
        tvMyProfileUsername.setText(Utils.buildDetailsString("Username", profile.getUsername()));

        TextView tvMyProfileFirstName = (TextView) findViewById(R.id.tv_my_profile_firstName);
        tvMyProfileFirstName.setText(Utils.buildDetailsString("First name", profile.getFirstName()));

        TextView tvMyProfileLastName = (TextView) findViewById(R.id.tv_my_profile_lasstName);
        tvMyProfileLastName.setText(Utils.buildDetailsString("Last name", profile.getLastName()));

        TextView tvMyProfileAddress = (TextView) findViewById(R.id.tv_my_profile_address);
        tvMyProfileAddress.setText(Utils.buildDetailsString("Address", profile.getAddress()));

        TextView tvMyProfilePhone = (TextView) findViewById(R.id.tv_my_profile_phone);
        tvMyProfilePhone.setText(Utils.buildDetailsString("Phone", profile.getPhone()));

        TextView tvMyProfileRole = (TextView) findViewById(R.id.tv_my_profile_role);
        tvMyProfileRole.setText(Utils.buildDetailsString("Role", profile.getRole()));
    }

    protected void setupDrawer() {
        View drawerContainer = this.findViewById(R.id.container_drawer);

        DrawerCommonFactory drawerCommonFactory;
        String role = super.authenticationCookie.getRole();

        DrawerFactoryModel drawerFactoryModel = super.getDrawerFactoryModel();
        drawerCommonFactory = new DrawerCommonFactory(this, drawerContainer, super.loginResult, drawerFactoryModel);

        Fragment drawerFragment = drawerCommonFactory.getFragment();
        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_drawer, drawerFragment)
                .commit();
    }

    private void initEditProfileButton() {
        final Context context = this;
        Button btnEditProfile = (Button) findViewById(R.id.btn_edit_profile);
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditMyProfileActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
