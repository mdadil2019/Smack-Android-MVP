package com.smack.mdadil2019.smack.ui;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.smack.mdadil2019.smack.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity  implements LoginActivityMVP.View {

    @BindView(R.id.inputTextUserName)
    TextInputEditText userNameEt;

    @BindView(R.id.passwordInputTxt)
    TextInputEditText passwordEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    public String getUserName() {
        return userNameEt.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEt.getText().toString();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
