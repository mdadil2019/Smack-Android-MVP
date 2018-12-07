package com.smack.mdadil2019.smack.data.network;

import com.smack.mdadil2019.smack.data.network.model.MessageResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface MessageService {

    @GET
    Observable<ArrayList<MessageResponse>> getAllMessages(@Header("Authorization") String token,
                                                         @Header("Content-Type") String type);
}
