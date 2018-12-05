package com.smack.mdadil2019.smack.di.root;

import android.app.Application;

import com.smack.mdadil2019.smack.di.LoginActivityModule;
import com.smack.mdadil2019.smack.di.NavDrawerModule;
import com.smack.mdadil2019.smack.di.RegistrationActivityModule;

public class MyApp extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .loginActivityModule(new LoginActivityModule())
                .registrationActivityModule(new RegistrationActivityModule())
                .navDrawerModule(new NavDrawerModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }
}
