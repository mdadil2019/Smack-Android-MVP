package com.smack.mdadil2019.smack.ui;

public interface LoginActivityMVP {
    interface View{
        String getUserName();

        String getPassword();

        void showMessage(String message);
    }

    interface Presenter{
        void setView(View view);

        boolean checkLoginStatus();

        void setLoginStatus(boolean loginStatus);

        boolean login(String userName,String password);
    }

    interface Model{

    }
}
