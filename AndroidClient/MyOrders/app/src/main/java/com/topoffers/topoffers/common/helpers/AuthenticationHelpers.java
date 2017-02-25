package com.topoffers.topoffers.common.helpers;

import android.content.Context;
import android.content.Intent;

import com.orm.SugarRecord;
import com.topoffers.topoffers.buyer.activities.BuyerProductsListActivity;
import com.topoffers.topoffers.common.models.AuthenticationCookie;
import com.topoffers.topoffers.login.LoginActivity;
import com.topoffers.topoffers.seller.activities.SellerProductsListActivity;

import java.util.List;

public class AuthenticationHelpers {
    private void AuthenticationHelpers() {
    }

    public static Intent checkAuthentication(Context context) {
        AuthenticationCookie cookie = getAuthenticationCookie();
        return checkAuthentication(context, cookie);
    }

    public static Intent checkAuthentication(Context context, AuthenticationCookie cookie) {
        Intent intent;
        if (cookie == null) {
            intent = new Intent(context, LoginActivity.class);
        } else {
            intent = null;
        }

        return intent;
    }

    public static AuthenticationCookie getAuthenticationCookie() {
        List<AuthenticationCookie> cookies  = SugarRecord.listAll(AuthenticationCookie.class);
        if (cookies.size() == 0) {
            return null;
        } else {
            return cookies.get(0);
        }
    }

    public static void logout(Context context) {
        // Remove authentication cookies
        SugarRecord.deleteAll(AuthenticationCookie.class);

        // Redirect go login screen
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
