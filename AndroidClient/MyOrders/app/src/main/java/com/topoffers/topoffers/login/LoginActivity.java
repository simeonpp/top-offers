package com.topoffers.topoffers.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.topoffers.data.base.IData;
import com.topoffers.data.base.RequestWithBodyType;
import com.topoffers.data.services.HttpRestData;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.buyer.activities.BuyerProductsListActivity;
import com.topoffers.topoffers.common.models.LoginRequest;
import com.topoffers.topoffers.common.models.LoginResult;
import com.topoffers.topoffers.seller.activities.SellerProductsListActivity;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private HttpRestData<LoginResult> loginData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginData = new HttpRestData<LoginResult>("http://192.168.0.103:8000/api/login", LoginResult.class, LoginResult[].class);

        this.addSubmitButtonListener();
    }

    private void addSubmitButtonListener() {
        Button submitButton = (Button) this.findViewById(R.id.btn_login_submit);
        submitButton.setOnClickListener(v -> {
            String username = ((EditText) this.findViewById(R.id.et_login_username)).getText().toString();
            String password = ((EditText) this.findViewById(R.id.et_login_password)).getText().toString();

            LoginRequest loginRequest = new LoginRequest(username, password);

            loginData.custom(RequestWithBodyType.POST, loginRequest)
                .subscribe(loginResult -> {
                    if (loginResult.getError() == null) {
                        String role = loginResult.getCookie().getRole();
                        Intent intent;
                        if (Objects.equals(role, "seller")) {
                            intent = new Intent(this, SellerProductsListActivity.class);
                        } else {
                            intent = new Intent(this, BuyerProductsListActivity.class);
                        }
                        startActivity(intent);
                    } else {
                        Toast
                            .makeText(this, loginResult.getError().getMessage(), Toast.LENGTH_LONG)
                            .show();
                    }
                });
        });
    }
}
