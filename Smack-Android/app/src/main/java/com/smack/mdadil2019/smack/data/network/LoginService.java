package com.smack.mdadil2019.smack.data.network;

import com.smack.mdadil2019.smack.data.network.model.LoginRequest;
import com.smack.mdadil2019.smack.data.network.model.LoginResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginService {
    @POST(ApiEndPoint.ENDPOINT_SERVER_LOGIN)
    @Headers(ApiHeaders.HEADER)
    Call<LoginResponse> loginRequest(@Body LoginRequest loginRequest);
}
