package com.smack.mdadil2019.smack.ui.login;

import android.Manifest;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.smack.mdadil2019.smack.R;
import com.smack.mdadil2019.smack.di.root.MyApp;
import com.smack.mdadil2019.smack.ui.chat.NavDrawer;
import com.smack.mdadil2019.smack.ui.signup.RegistrationActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity  implements LoginActivityMVP.View {

    @BindView(R.id.inputTextUserName)
    TextInputEditText userNameEt;

    @BindView(R.id.passwordInputTxt)
    TextInputEditText passwordEt;


    @BindView(R.id.pgBarLogin)
    ProgressBar loginPgBar;

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
    public void showProgressbar() {
        loginPgBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        loginPgBar.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.INTERNET,Manifest.permission.ACCESS_WIFI_STATE,Manifest.permission.ACCESS_NETWORK_STATE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if(!report.areAllPermissionsGranted()){
                            Toast.makeText(LoginActivity.this, "You can't access the features of the app without permissions", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }).check();
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
