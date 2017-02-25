package com.topoffers.topoffers;

import android.app.Application;

import com.topoffers.topoffers.config.ConfigModule;
import com.topoffers.topoffers.config.DataModule;
import com.topoffers.topoffers.login.LoginActivity;
import com.topoffers.topoffers.seller.activities.SellerProductsListActivity;

import dagger.Component;

public class TopOffersApplication extends Application {
    private ApplicationComponent component;

    public void onCreate() {
        super.onCreate();

        this.component = DaggerTopOffersApplication_ApplicationComponent.builder()
            .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    @Component(modules = {ConfigModule.class, DataModule.class})
    public interface ApplicationComponent {
        void inject(LoginActivity loginActivity);
        void inject(SellerProductsListActivity sellerProductsListActivity);
    }
}
