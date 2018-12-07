package com.smack.mdadil2019.smack.ui.signup;

public interface RegistrationActivityMVP {
    interface View{
        void showMessage(String message);

        String getEmail();

        String getPassword();

        String getUserName();

        void openAvatarPicker();

        void changeColor();

        void openNavigationDrawer();
    }

    interface Presenter{

        void register();

        void pickAvatar();

        void changeAvatarColor();

        void setView(View v);
    }
}
