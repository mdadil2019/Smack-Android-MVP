package com.smack.mdadil2019.smack.ui;

import com.smack.mdadil2019.smack.data.network.ApiEndPoint;
import com.smack.mdadil2019.smack.data.network.ChannelService;
import com.smack.mdadil2019.smack.data.network.MessageService;
import com.smack.mdadil2019.smack.data.network.model.ChannelResponse;
import com.smack.mdadil2019.smack.data.network.model.MessageResponse;
import com.smack.mdadil2019.smack.data.prefs.AppPreferencesHelper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.socket.client.IO;
import io.socket.client.Manager;
import io.socket.client.Socket;
import io.socket.client.Url;
import io.socket.emitter.Emitter;

public class NavDrawerPresenter implements NavDrawerMVP.Presenter {
    NavDrawerMVP.View view;
    Socket mSocket;
    Manager manager;
    ChannelResponse mChannelResponse;
    ChannelService mChannelService;
    AppPreferencesHelper sharedPrefs;
    MessageService mMessageService;



    ArrayList<ChannelResponse> channels;
    ArrayList<MessageResponse> messages;

    public NavDrawerPresenter(AppPreferencesHelper appPreferencesHelper, ChannelService channelService, ChannelResponse channelResponse,
                                MessageService messageService){
            manager = new Manager();
            mChannelResponse = channelResponse;
            mChannelService = channelService;
            sharedPrefs = appPreferencesHelper;
            mMessageService = messageService;
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
    public void loadAddedChannels() {
        mSocket.on("channelCreated", new Emitter.Listener() {
            @Override
            public void call(Object... args) {


                ChannelResponse channelResponse = new ChannelResponse();
                channelResponse.setChannelName(String.valueOf(args[0]));
                channelResponse.setChannelDesc(String.valueOf(args[1]));
                channelResponse.setChannelId(String.valueOf(args[2]));

                //creating an issue #Bug1
                channels.add(channelResponse);
                view.addChannelInList(channels);

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
    public void getAllChannels() {
       /*
        1. Create ChannelRequest model with Retrofit
        2. send api request
        3. store response in ChannelResponse Array
        4. return the response

         */
       mChannelService.getAllChannels("Bearer "+ sharedPrefs.getAuthToken(),"application/json; charset=utf-8")
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<ArrayList<ChannelResponse>>() {
                   @Override
                   public void onSubscribe(Disposable d) {
                       channels = new ArrayList<>();
                   }

                   @Override
                   public void onNext(ArrayList<ChannelResponse> channelResponse) {
                        channels = channelResponse;
                   }

                   @Override
                   public void onError(Throwable e) {
                        view.showUIMessage(e.getMessage());
                   }

                   @Override
                   public void onComplete() {
                        view.addChannelInList(channels);
                   }
               });

    }


    @Override
    public void openChatRoom(String channelName) {
        mMessageService.getAllMessages("Bearer "+ channelName,"application/json; charset=utf-8")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<MessageResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        messages = new ArrayList<>();
                    }

                    @Override
                    public void onNext(ArrayList<MessageResponse> messageResponses) {
                        messages = messageResponses;
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showUIMessage(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        //pass data to adapter to populate the messages

                    }
                });
    }


    @Override
    public void sendMessage(String channelId) {

    }
}
