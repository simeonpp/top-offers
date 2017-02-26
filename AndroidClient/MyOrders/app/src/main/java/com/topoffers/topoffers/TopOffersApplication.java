package com.topoffers.topoffers;

import android.app.Application;

import com.topoffers.topoffers.buyer.activities.BuyerProductDetailsActivity;
import com.topoffers.topoffers.buyer.activities.BuyerProductsListActivity;
import com.topoffers.topoffers.config.ConfigModule;
import com.topoffers.topoffers.config.DataModule;
import com.topoffers.topoffers.login.LoginActivity;
import com.topoffers.topoffers.seller.activities.SellerOrderHistoryListActivity;
import com.topoffers.topoffers.seller.activities.SellerOrderHistoryDetailsActivity;
import com.topoffers.topoffers.seller.activities.UpdateProductActivity;
import com.topoffers.topoffers.seller.activities.SellerProductDetailsActivity;
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
        void inject(SellerProductDetailsActivity sellerProductDetailsActivity);
        void inject(UpdateProductActivity updateProductActivity);
        void inject(SellerOrderHistoryListActivity sellerOrderHistoryListActivity);
        void inject(SellerOrderHistoryDetailsActivity sellerOrderHistoryDetailsActivity);

        void inject(BuyerProductsListActivity buyerProductsListActivity);
        void inject(BuyerProductDetailsActivity buyerProductDetailsActivity);
    }
}
