package com.smack.mdadil2019.smack.ui;

import android.content.SharedPreferences;

import com.smack.mdadil2019.smack.data.network.CreateUserService;
import com.smack.mdadil2019.smack.data.network.LoginService;
import com.smack.mdadil2019.smack.data.network.RegistrationService;
import com.smack.mdadil2019.smack.data.network.model.CreateUserRequest;
import com.smack.mdadil2019.smack.data.network.model.CreateUserResponse;
import com.smack.mdadil2019.smack.data.network.model.LoginRequest;
import com.smack.mdadil2019.smack.data.network.model.LoginResponse;
import com.smack.mdadil2019.smack.data.network.model.RegistrationRequest;
import com.smack.mdadil2019.smack.data.prefs.AppPreferencesHelper;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationPresenter implements RegistrationActivityMVP.Presenter {

    RegistrationActivityMVP.View view;
    RegistrationService registrationService;
    RegistrationRequest registrationRequest;
    LoginService loginService;
    LoginRequest loginRequest;
    CreateUserRequest createUserRequest;
    CreateUserService createUserService;
    AppPreferencesHelper sharedPreferences;

    String avatarName;
    String avatarColor;

    public RegistrationPresenter(RegistrationRequest regRequest, RegistrationService regService, LoginService loginSer,
                                 LoginRequest loginReq, CreateUserRequest createUserReq, CreateUserService createUserSer,
                                 AppPreferencesHelper prefs){
        registrationRequest = regRequest;
        registrationService = regService;
        loginService = loginSer;
        loginRequest = loginReq;
        createUserRequest = createUserReq;
        createUserService = createUserSer;
        sharedPreferences = prefs;
    }
    @Override
    public void register() {
        final String userName = view.getUserName();
        final String password = view.getPassword();
        final String email = view.getEmail();
        if(!userName.equals("") && !password.equals("") && !email.equals("")){
            registrationRequest.setEmail(email);
            registrationRequest.setPassword(password);
            Observable<CreateUserResponse> response = registrationService.registerRequest(registrationRequest)
                    .concatMap(new Function<ResponseBody, ObservableSource<? extends LoginResponse>>() {
                        @Override
                        public ObservableSource<? extends LoginResponse> apply(ResponseBody responseBody) throws Exception {
                            if(responseBody.string().contains("Successfully")){
                                loginRequest.setPassword(password);
                                loginRequest.setEmail(email);
                                return loginService.loginRequest(loginRequest);
                            }
                            return null;
                        }
                    }).concatMap(new Function<LoginResponse, ObservableSource<? extends CreateUserResponse>>() {
                @Override
                public ObservableSource<? extends CreateUserResponse> apply(LoginResponse loginResponse) throws Exception {
                    String token = loginResponse.getToken(); //save the token in shared preferences
                    sharedPreferences.setAuthToken(token);
                    createUserRequest.setEmail(email);
                    createUserRequest.setName(userName);
                    createUserRequest.setAvatarColor(avatarColor);
                    createUserRequest.setAvatarName(avatarName);
                    return createUserService.createUser(createUserRequest,"Bearer " + token,"application/json; charset=utf-8");
                }
            });
            response.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CreateUserResponse>() {
                @Override
                public void onSubscribe(Disposable d) {
                    view.showMessage("Subscribed");
                }

                @Override
                public void onNext(CreateUserResponse createUserResponse) {
                    view.showMessage(createUserResponse.getEmail());
                }

                @Override
                public void onError(Throwable e) {
                    view.showMessage(e.getMessage());
                }

                @Override
                public void onComplete() {
                    view.showMessage("Completed: observing");
                    view.openNavigationDrawer();
                }
            });
        }else{
            view.showMessage("Please enter the registration details");
        }
    }

    @Override
    public void pickAvatar() {
        view.openAvatarPicker();
    }

    @Override
    public void changeAvatarColor() {
        view.changeColor();
    }

    @Override
    public void setView(RegistrationActivityMVP.View v) {
        view = v;
    }
}
