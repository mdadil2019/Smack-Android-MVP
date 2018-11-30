package com.smack.mdadil2019.smack.ui;


import com.smack.mdadil2019.smack.ui.LoginActivityMVP.View;

public class LoginPresenter implements LoginActivityMVP.Presenter {

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
    public boolean login(String userName, String password) {
        return false;
    }
}
