package com.topoffers.topoffers.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.topoffers.topoffers.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.addSubmitButtonListener();
    }

    private void addSubmitButtonListener() {
        Button submitButton = (Button) this.findViewById(R.id.btn_login_submit);
        submitButton.setOnClickListener(v -> {
            String username = ((EditText) this.findViewById(R.id.et_login_username)).getText().toString();
            String password = ((EditText) this.findViewById(R.id.et_login_password)).getText().toString();

            Toast
                .makeText(this, username + " - " + password, Toast.LENGTH_LONG)
                .show();
        });
    }
}
