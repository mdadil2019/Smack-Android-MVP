package com.smack.mdadil2019.smack.ui;

import com.smack.mdadil2019.smack.data.network.RegistrationService;
import com.smack.mdadil2019.smack.data.network.model.RegistrationRequest;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationPresenter implements RegistrationActivityMVP.Presenter {

    RegistrationActivityMVP.View view;
    RegistrationService registrationService;
    RegistrationRequest registrationRequest;

    String avatarName;
    String avatarColor;

    public RegistrationPresenter(RegistrationRequest regRequest, RegistrationService regService){
        registrationRequest = regRequest;
        registrationService = regService;
    }
    @Override
    public void register() {
        String userName = view.getUserName();
        String password = view.getPassword();
        String email = view.getEmail();
        if(!userName.equals("") && !password.equals("") && !email.equals("")){
            registrationRequest.setEmail(email);
            registrationRequest.setPassword(password);
            Call<ResponseBody> responseInstance = registrationService.registerRequest(registrationRequest);
            responseInstance.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        view.showMessage(response.body().string());
                        if(response.body().string().contains("success")){
                            //send create user request with avatarColor and name
                            if(avatarName!=null && !avatarName.equals("")){

                            }else{
                                view.showMessage("Please select any avatar");
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        view.showMessage(e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    view.showMessage(t.getMessage());
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
