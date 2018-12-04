package com.smack.mdadil2019.smack.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smack.mdadil2019.smack.data.network.ApiEndPoint;
import com.smack.mdadil2019.smack.data.network.LoginService;
import com.smack.mdadil2019.smack.data.network.model.LoginRequest;
import com.smack.mdadil2019.smack.data.network.model.LoginResponse;
import com.smack.mdadil2019.smack.ui.LoginActivityMVP;
import com.smack.mdadil2019.smack.ui.LoginPresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class LoginActivityModule {

    @Provides
    public LoginActivityMVP.Presenter provideLoginActivityPresenter(LoginService loginService, LoginRequest loginRequest){
        return new LoginPresenter(loginService,loginRequest);
    }

    @Provides
    public LoginRequest provideLoginRequest(){
       return new LoginRequest();
    }

    @Provides
    public LoginService provideLoginService(){
        return provideRetrofit(provideOkHttpClient(),provideGson()).create(LoginService.class);
    }


    @Provides
    public Retrofit provideRetrofit( OkHttpClient okHttpClient, Gson gson){
        return new Retrofit.Builder()
                .baseUrl(ApiEndPoint.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    public Gson provideGson(){
        return new GsonBuilder().setLenient().create();
    }

    @Provides
    public OkHttpClient provideOkHttpClient(){
        return new OkHttpClient.Builder().
                readTimeout(20,TimeUnit.SECONDS)
                .connectTimeout(20,TimeUnit.SECONDS)
                .build();
    }

}
