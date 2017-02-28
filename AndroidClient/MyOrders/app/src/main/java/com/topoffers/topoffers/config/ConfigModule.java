package com.topoffers.topoffers.config;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ConfigModule {

    @Provides
    @Named("apiBaseUrl")
    String provideBaseUrl() {
        return "http://192.168.0.103:8000/api/";
    }

    @Provides
    @Named("apiBaseImageUrl")
    String provideBaseImageUrl() {
        return "http://192.168.0.103:8000/api/images/";
    }
}
