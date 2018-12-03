package com.smack.mdadil2019.smack.data.network;

import com.smack.mdadil2019.smack.data.network.model.RegistrationRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RegistrationService {
    @POST(ApiEndPoint.ENDPOINT_SERVER_REGISTER)
    @Headers(ApiHeaders.HEADER)
    Call<ResponseBody> registerRequest(@Body RegistrationRequest registrationRequest);
}
