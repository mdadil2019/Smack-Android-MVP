package com.smack.mdadil2019.smack.di.root;

import com.smack.mdadil2019.smack.di.LoginActivityModule;
import com.smack.mdadil2019.smack.ui.LoginActivity;

import dagger.Component;

@Component(modules = {ApplicationModule.class, LoginActivityModule.class})
public interface ApplicationComponent {
    void inject(LoginActivity loginActivity);
}
