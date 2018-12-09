package com.smack.mdadil2019.smack.ui.signup;

public interface RegistrationActivityMVP {
    interface View{
        void showMessage(String message);

        String getEmail();

        String getPassword();

        String getUserName();


        void openNavigationDrawer();

        void showProgressbar();

        void hideProgressBar();
    }

    interface Presenter{

        void register();

        void setView(View v);
    }
}
