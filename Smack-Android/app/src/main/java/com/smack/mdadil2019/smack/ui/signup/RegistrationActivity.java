package com.smack.mdadil2019.smack.ui.signup;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.smack.mdadil2019.smack.R;
import com.smack.mdadil2019.smack.di.root.MyApp;
import com.smack.mdadil2019.smack.ui.chat.NavDrawer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity implements RegistrationActivityMVP.View {


    @Inject
    RegistrationActivityMVP.Presenter presenter;

    @BindView(R.id.textInputUserNameReg)
    TextInputEditText userNameEt;

    @BindView(R.id.textInputEmailReg)
    TextInputEditText emailEt;

    @BindView(R.id.textInputPasswordReg)
    TextInputEditText passwordEt;

    @BindView(R.id.progressBarEmail)
    ProgressBar pgBarRegistration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        ((MyApp)getApplication()).getApplicationComponent().inject(this);
    }

    @OnClick(R.id.createAccountBtn)void createAccount(){
        presenter.register();
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
    public String getEmail() {
        return emailEt.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEt.getText().toString();
    }

    @Override
    public String getUserName() {
        return userNameEt.getText().toString();
    }

    @Override
    public void showProgressbar() {
        pgBarRegistration.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        pgBarRegistration.setVisibility(View.INVISIBLE);
    }

    @Override
    public void openNavigationDrawer() {
        startActivity(new Intent(this,NavDrawer.class));
        finish();
    }
}
