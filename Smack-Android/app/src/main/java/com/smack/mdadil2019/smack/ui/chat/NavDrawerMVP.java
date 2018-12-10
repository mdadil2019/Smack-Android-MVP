package com.smack.mdadil2019.smack.ui.chat;

import com.smack.mdadil2019.smack.data.network.model.ChannelResponse;
import com.smack.mdadil2019.smack.data.network.model.MessageResponse;

import java.util.ArrayList;

public interface NavDrawerMVP {

    interface View{
        void updateRecyclerView(ArrayList<MessageResponse> messageResponses);

        String getCreateChannelName();

        String getCreateChannelDescription();

        void notifyAdapter();

        void createChannelTapped();

        void showUIMessage(String message);

        void addChannelInList(ArrayList<ChannelResponse> channelResponses);

        void clearMessageText();

        void showProgressbar();

        void hideProgressBar();

        //after logout
        void showLoginScreen();


    }

    interface Presenter{
        void openChatRoom(String channelName);



        void createChannel();

        void sendMessage(String channelId,String message);

        void setView(View view);

        void loadAddedChannels();

        void getAllChannels();

        void getAllChannelsOffline();

        void getAllMessagesOffline(String channelName);

        void getMessage();

        void logOut();
    }
}
