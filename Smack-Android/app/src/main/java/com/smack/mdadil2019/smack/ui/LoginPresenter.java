package com.smack.mdadil2019.smack.ui;


import android.content.Context;
import android.widget.Toast;

import com.smack.mdadil2019.smack.data.network.LoginService;
import com.smack.mdadil2019.smack.data.network.model.LoginRequest;
import com.smack.mdadil2019.smack.data.network.model.LoginResponse;
import com.smack.mdadil2019.smack.ui.LoginActivityMVP.View;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginActivityMVP.Presenter {

    LoginService loginService;
    LoginRequest loginRequest;
    Disposable disposable;

    public LoginPresenter(LoginService loginApiService, LoginRequest loginReq){
        loginService = loginApiService;
        loginRequest = loginReq;
    }

    View view;

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public boolean checkLoginStatus() {
        return false;
    }

    @Override
    public void setLoginStatus(boolean loginStatus) {

    }

    @Override
    public void login(String userName, String password) {
        if(userName!=null && password!=null){
            loginRequest.setEmail(userName);
            loginRequest.setPassword(password);
            Observable<LoginResponse> loginResponse = loginService.loginRequest(loginRequest);
            loginResponse.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<LoginResponse>() {
                @Override
                public void onSubscribe(Disposable d) {
                    disposable = d;
                }

                @Override
                public void onNext(LoginResponse loginResponse) {
                    view.showMessage("Welcome " + loginResponse.getUser());
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {
                    view.showMessage("Login successful");
                    disposable.dispose();
                    view.openNavigationDrawer();
                }
            });
        }else{
            view.showMessage("Please enter credentials to login");
        }
    }

    @Override
    public void openSignUpActivity() {
        view.signUpActivity();
    }
}
