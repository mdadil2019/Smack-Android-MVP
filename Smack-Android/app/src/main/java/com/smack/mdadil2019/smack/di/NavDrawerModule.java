package com.smack.mdadil2019.smack.di;

import com.smack.mdadil2019.smack.ui.NavDrawerMVP;
import com.smack.mdadil2019.smack.ui.NavDrawerPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class NavDrawerModule {

    @Provides
    NavDrawerMVP.Presenter provideNavDrawerPresenter(){
        return new NavDrawerPresenter();
    }
}
