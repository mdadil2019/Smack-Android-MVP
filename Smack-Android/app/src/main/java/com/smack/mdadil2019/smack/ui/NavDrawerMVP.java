package com.smack.mdadil2019.smack.ui;

import com.smack.mdadil2019.smack.data.network.model.ChannelResponse;

import java.util.ArrayList;

public interface NavDrawerMVP {

    interface View{
        void updateRecyclerView();

        String getCreateChannelName();

        String getCreateChannelDescription();

        void notifyAdapter();

        void createChannelTapped();

        void showUIMessage(String message);

        void addChannelInList(ArrayList<ChannelResponse> channelResponses);


    }

    interface Presenter{
        void openChatRoom(String channelName);

        void createChannel();

        void sendMessage(String channelId);

        void setView(View view);

        void loadAddedChannels();

        void getAllChannels();
    }
}
