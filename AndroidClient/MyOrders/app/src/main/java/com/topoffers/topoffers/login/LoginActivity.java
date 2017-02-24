package com.topoffers.topoffers.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.topoffers.data.base.IData;
import com.topoffers.data.base.RequestWithBodyType;
import com.topoffers.data.services.HttpRestData;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.TopOffersApplication;
import com.topoffers.topoffers.buyer.activities.BuyerProductsListActivity;
import com.topoffers.topoffers.common.models.LoginRequest;
import com.topoffers.topoffers.common.models.LoginResult;
import com.topoffers.topoffers.seller.activities.SellerProductsListActivity;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public class LoginActivity extends AppCompatActivity {

    @Inject
    public IData<LoginResult> loginData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((TopOffersApplication) getApplication()).getComponent().inject(this);

        this.addSubmitButtonListener();
    }

    private void addSubmitButtonListener() {
        Button submitButton = (Button) this.findViewById(R.id.btn_login_submit);
        // Have some problem with jack options and dagger, will sort it out later

//        submitButton.setOnClickListener(v -> {
//            String username = ((EditText) this.findViewById(R.id.et_login_username)).getText().toString();
//            String password = ((EditText) this.findViewById(R.id.et_login_password)).getText().toString();
//
//            LoginRequest loginRequest = new LoginRequest(username, password);
//
//            loginData.custom(RequestWithBodyType.POST, loginRequest)
//                .subscribe(loginResult -> {
//                    if (loginResult.getError() == null) {
//                        String role = loginResult.getCookie().getRole();
//                        Intent intent;
//                        if (Objects.equals(role, "seller")) {
//                            intent = new Intent(this, SellerProductsListActivity.class);
//                        } else {
//                            intent = new Intent(this, BuyerProductsListActivity.class);
//                        }
//                        startActivity(intent);
//                    } else {
//                        Toast
//                            .makeText(this, loginResult.getError().getMessage(), Toast.LENGTH_LONG)
//                            .show();
//                    }
//                });
//        });

        final Context context = this;
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText) findViewById(R.id.et_login_username)).getText().toString();
                String password = ((EditText) findViewById(R.id.et_login_password)).getText().toString();
                LoginRequest loginRequest = new LoginRequest(username, password);

                loginData.custom(RequestWithBodyType.POST, loginRequest)
                    .subscribe(new Consumer<LoginResult>() {
                        @Override
                        public void accept(LoginResult loginResult) throws Exception {
                            if (loginResult.getError() == null) {
                                String welcomeString = String.format("Welcome %s", loginResult.getFirstName());
                                Toast
                                    .makeText(context, welcomeString, Toast.LENGTH_LONG)
                                    .show();

                                // Redirect to corresponding page
                                String role = loginResult.getCookie().getRole();
                                Intent intent;
                                if (Objects.equals(role, "seller")) {
                                    intent = new Intent(context, SellerProductsListActivity.class);
                                } else {
                                    intent = new Intent(context, BuyerProductsListActivity.class);
                                }
                                startActivity(intent);
                            } else {
                                // Toast will be exported to Wrap component
                                Toast
                                    .makeText(context, loginResult.getError().getMessage(), Toast.LENGTH_LONG)
                                    .show();
                            }
                        }
                    });

            }
        });
    }
}
