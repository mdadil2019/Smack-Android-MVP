package com.smack.mdadil2019.smack.ui;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.smack.mdadil2019.smack.R;
import com.smack.mdadil2019.smack.di.root.MyApp;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity  implements LoginActivityMVP.View {

    @BindView(R.id.inputTextUserName)
    TextInputEditText userNameEt;

    @BindView(R.id.passwordInputTxt)
    TextInputEditText passwordEt;

    @Inject
    LoginActivityMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        ((MyApp)getApplication()).getApplicationComponent().inject(this);
    }

    @OnClick(R.id.loginBtn)void loginClicked(){
        String userName = userNameEt.getText().toString();
        String password = passwordEt.getText().toString();
        presenter.login(userName,password);
    }

    @OnClick(R.id.textViewSignup)void signUpTouched(){
        presenter.openSignUpActivity();
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void signUpActivity() {
        startActivity(new Intent(this,RegistrationActivity.class));
    }

    @Override
    public void openNavigationDrawer() {
        startActivity(new Intent(this,NavDrawer.class));
        finish();
    }

}
