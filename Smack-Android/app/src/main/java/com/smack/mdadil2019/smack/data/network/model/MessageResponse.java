package com.smack.mdadil2019.smack.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageResponse {

    @Expose
    @SerializedName("messageBody")
    private String messageBody;

    @Expose
    @SerializedName("channelId")
    private String channelId;

    @Expose
    @SerializedName("userName")
    private String userName;

    @Expose
    @SerializedName("userAvatar")
    private String userAvatar;

    @Expose
    @SerializedName("userAvatarColor")
    private String avatarColor;

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("timestamp")
    private String timeStamp;

}
