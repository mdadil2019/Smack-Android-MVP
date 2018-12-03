package com.smack.mdadil2019.smack.ui;

public interface LoginActivityMVP {
    interface View{

        void showMessage(String message);

        void signUpActivity();
    }

    interface Presenter{
        void setView(View view);

        boolean checkLoginStatus();

        void setLoginStatus(boolean loginStatus);

        void login(String userName,String password);

        void openSignUpActivity();
    }

    interface Model{

    }
}
