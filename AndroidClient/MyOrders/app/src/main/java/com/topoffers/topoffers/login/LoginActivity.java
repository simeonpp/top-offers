package com.topoffers.topoffers.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orm.SugarContext;
import com.orm.SugarRecord;
import com.topoffers.data.base.IData;
import com.topoffers.data.base.RequestWithBodyType;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.TopOffersApplication;
import com.topoffers.topoffers.buyer.activities.BuyerProductsListActivity;
import com.topoffers.topoffers.common.activities.BaseActivity;
import com.topoffers.topoffers.common.helpers.RedirectHelpers;
import com.topoffers.topoffers.common.models.AuthenticationCookie;
import com.topoffers.topoffers.common.models.LoginRequest;
import com.topoffers.topoffers.common.models.LoginResult;
import com.topoffers.topoffers.seller.activities.SellerProductsListActivity;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public class LoginActivity extends BaseActivity {

    @Inject
    public IData<LoginResult> loginData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.addSubmitButtonListener();
    }

    @Override
    protected void init() {
        super.init();
        ((TopOffersApplication) getApplication()).getComponent().inject(this);
    }

    private void addSubmitButtonListener() {
        Button submitButton = (Button) this.findViewById(R.id.btn_login_submit);
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

                                // Save cookie to device SQLite DB
                                AuthenticationCookie authenticationCookie = loginResult.getCookie();
                                SugarRecord.deleteAll(AuthenticationCookie.class); // delete any current records
                                SugarRecord.save(authenticationCookie);

                                // Save login response to device SQLLite DB
                                SugarRecord.deleteAll(LoginResult.class); // delete any current records
                                SugarRecord.save(loginResult);

                                // Redirect to corresponding page
                                Intent intent = RedirectHelpers.baseRedirect(context, authenticationCookie);
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
