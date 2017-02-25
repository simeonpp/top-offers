package com.topoffers.topoffers.config;

import com.topoffers.data.base.IData;
import com.topoffers.data.services.HttpRestData;
import com.topoffers.topoffers.common.models.ApiUrl;
import com.topoffers.topoffers.common.models.LoginRequest;
import com.topoffers.topoffers.common.models.LoginResult;
import com.topoffers.topoffers.common.models.Order;
import com.topoffers.topoffers.common.models.Product;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    ApiUrl<LoginRequest> provideBookApiUrl(@Named("apiBaseUrl") String apiBaseUrl) {
        ApiUrl<LoginRequest> apiUrlLogin = new ApiUrl<>(apiBaseUrl, "login");
        return apiUrlLogin;
    }

    @Provides
    ApiUrl<Product> provideProductApiUrl(@Named("apiBaseUrl") String apiBaseUrl) {
        ApiUrl<Product> apiUrlProduct = new ApiUrl<>(apiBaseUrl, "products");
        return apiUrlProduct;
    }

    @Provides
    ApiUrl<Order> provideOrderApiUrl(@Named("apiBaseUrl") String apiBaseUrl) {
        ApiUrl<Order> apiUrlOrder = new ApiUrl<>(apiBaseUrl, "orders");
        return apiUrlOrder;
    }

    @Provides
    @Inject
    IData<LoginResult> provideIDataLoginResult(ApiUrl<LoginRequest> apiUrlrlLogin) {
        return  new HttpRestData<LoginResult>(apiUrlrlLogin.getUrl(), LoginResult.class, LoginResult[].class);
    }

    @Provides
    @Inject
    IData<Product> provideIDataProduct(ApiUrl<Product> apiUrlProduct) {
        return new HttpRestData<Product>(apiUrlProduct.getUrl(), Product.class, Product[].class);
    }

    @Provides
    @Inject
    IData<Order> provideOrder(ApiUrl<Order> apiUrlOrder) {
        return new HttpRestData<Order>(apiUrlOrder.getUrl(), Order.class, Order[].class);
    }
}
