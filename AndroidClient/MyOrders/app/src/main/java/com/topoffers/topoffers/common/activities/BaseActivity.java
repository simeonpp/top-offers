package com.topoffers.topoffers.common.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.orm.SugarContext;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.init();
    }

    protected void init() {
        SugarContext.init(this); // required by the library
    }
}
