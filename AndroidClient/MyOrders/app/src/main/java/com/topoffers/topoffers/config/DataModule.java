package com.topoffers.topoffers.config;

import com.topoffers.data.base.IData;
import com.topoffers.data.services.HttpRestData;
import com.topoffers.topoffers.common.models.ApiUrl;
import com.topoffers.topoffers.common.models.LoginRequest;
import com.topoffers.topoffers.common.models.LoginResult;

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
    @Inject
    IData<LoginResult> provideIDataLoginResult(ApiUrl<LoginRequest> apiurlLogin) {
        return  new HttpRestData<LoginResult>(apiurlLogin.getUrl(), LoginResult.class, LoginResult[].class);
    }
}
