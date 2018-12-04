package com.smack.mdadil2019.smack.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.smack.mdadil2019.smack.data.network.ApiEndPoint;
import com.smack.mdadil2019.smack.data.network.CreateUserService;
import com.smack.mdadil2019.smack.data.network.LoginService;
import com.smack.mdadil2019.smack.data.network.RegistrationService;
import com.smack.mdadil2019.smack.data.network.model.CreateUserRequest;
import com.smack.mdadil2019.smack.data.network.model.LoginRequest;
import com.smack.mdadil2019.smack.data.network.model.RegistrationRequest;
import com.smack.mdadil2019.smack.data.prefs.AppPreferencesHelper;
import com.smack.mdadil2019.smack.ui.RegistrationActivityMVP;
import com.smack.mdadil2019.smack.ui.RegistrationPresenter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.http.POST;

@Module(includes = {LoginActivityModule.class})
public class RegistrationActivityModule {

    @Provides
    RegistrationActivityMVP.Presenter provideRegistrationPresenter(RegistrationRequest registrationRequest,
                                                                   RegistrationService registrationService, LoginService loginService,
                                                                   LoginRequest loginRequest,CreateUserService createUserService,
                                                                   CreateUserRequest createUserRequest, AppPreferencesHelper prefs){
        return new RegistrationPresenter(registrationRequest,registrationService, loginService, loginRequest,createUserRequest,
                createUserService,prefs);
    }

    @Provides
    AppPreferencesHelper provideSharedPrefs(Context context){
        return new AppPreferencesHelper(context);//let's see how it gets the context of registration view
    }

    @Provides
    CreateUserService provideCreateUserService(Retrofit retrofit){
        return retrofit.create(CreateUserService.class);
    }

    @Provides
    CreateUserRequest provideCreateUserRequest(){
        return new CreateUserRequest();
    }

    @Provides
    RegistrationRequest provideRegistrationRequest(){
        return new RegistrationRequest();
    }

    @Provides
    RegistrationService provideRegistrationService(Retrofit retrofit){
        return retrofit.create(RegistrationService.class);
    }
}
