package com.smack.mdadil2019.smack.ui;


import android.content.Context;
import android.widget.Toast;

import com.smack.mdadil2019.smack.data.network.LoginService;
import com.smack.mdadil2019.smack.data.network.model.LoginRequest;
import com.smack.mdadil2019.smack.data.network.model.LoginResponse;
import com.smack.mdadil2019.smack.ui.LoginActivityMVP.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginActivityMVP.Presenter {

    LoginService loginService;
    LoginRequest loginRequest;

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
            Call<LoginResponse> loginResponse = loginService.loginRequest(loginRequest);
            loginResponse.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    view.showMessage("WELCOME " + response.body().getUser());
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    view.showMessage(t.getMessage());
                }
            });
        }else{
            view.showMessage("Please enter credentials to login");
        }
    }
}
