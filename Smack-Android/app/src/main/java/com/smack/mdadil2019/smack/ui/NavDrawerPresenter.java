package com.smack.mdadil2019.smack.ui;

import com.smack.mdadil2019.smack.data.network.ApiEndPoint;
import com.smack.mdadil2019.smack.data.network.model.ChannelResponse;

import java.net.URI;
import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Manager;
import io.socket.client.Socket;
import io.socket.client.Url;
import io.socket.emitter.Emitter;

public class NavDrawerPresenter implements NavDrawerMVP.Presenter {
    NavDrawerMVP.View view;
    Socket mSocket;
    Manager manager;
    public NavDrawerPresenter(){
            manager = new Manager();
        try {
            mSocket = IO.socket("https://adilchat.herokuapp.com");
        } catch (URISyntaxException e) {
            view.showUIMessage(e.getMessage());
        }
    }
    @Override
    public void setView(NavDrawerMVP.View view) {
        this.view = view;
        mSocket.connect();
    }

    @Override
    public void openChatRoom(String channelName) {

    }

    @Override
    public void loadChannels() {
        mSocket.on("channelCreated", new Emitter.Listener() {
            @Override
            public void call(Object... args) {


                ChannelResponse channelResponse = new ChannelResponse();
                channelResponse.setChannelName(String.valueOf(args[0]));
                channelResponse.setChannelDesc(String.valueOf(args[1]));
                channelResponse.setChannelId(String.valueOf(args[2]));
                view.addChannelInList(channelResponse);

            }

        });
    }

    @Override
    public void createChannel() {
        final String channelName = view.getCreateChannelName();
        final String channelDesc = view.getCreateChannelDescription();
        mSocket.emit("newChannel",channelName,channelDesc);
        mSocket.on(Socket.EVENT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                view.showUIMessage(String.valueOf(args[0]) );
            }
        });
    }

    @Override
    public void sendMessage(String channelId) {

    }
}
