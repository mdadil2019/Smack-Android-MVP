package com.smack.mdadil2019.smack.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChannelResponse {
    @Expose
    @SerializedName("name")
    private String channelName;

    @Expose
    @SerializedName("description")
    private String channelDesc;

    @Expose
    @SerializedName("_id")
    private String channelId;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelDesc() {
        return channelDesc;
    }

    public void setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
