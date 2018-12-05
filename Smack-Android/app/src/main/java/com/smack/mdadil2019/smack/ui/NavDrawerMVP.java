package com.smack.mdadil2019.smack.ui;

import com.smack.mdadil2019.smack.data.network.model.ChannelResponse;

public interface NavDrawerMVP {

    interface View{
        void updateRecyclerView();

        String getCreateChannelName();

        String getCreateChannelDescription();

        void notifyAdapter();

        void createChannelTapped();

        void showUIMessage(String message);

        void addChannelInList(ChannelResponse channelResponse);


    }

    interface Presenter{
        void openChatRoom(String channelName);

        void createChannel();

        void sendMessage(String channelId);

        void setView(View view);

        void loadChannels();
    }
}
