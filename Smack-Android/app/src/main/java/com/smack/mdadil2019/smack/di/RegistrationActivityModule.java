package com.smack.mdadil2019.smack.di;

import com.smack.mdadil2019.smack.data.network.ApiEndPoint;
import com.smack.mdadil2019.smack.data.network.LoginService;
import com.smack.mdadil2019.smack.data.network.RegistrationService;
import com.smack.mdadil2019.smack.data.network.model.RegistrationRequest;
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
    RegistrationActivityMVP.Presenter provideRegistrationPresenter(RegistrationRequest registrationRequest, RegistrationService registrationService){
        return new RegistrationPresenter(registrationRequest,registrationService);
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
