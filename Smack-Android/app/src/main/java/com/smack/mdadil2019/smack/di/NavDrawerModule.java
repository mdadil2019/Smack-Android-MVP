package com.smack.mdadil2019.smack.di;

import com.smack.mdadil2019.smack.data.network.ChannelService;
import com.smack.mdadil2019.smack.data.network.MessageService;
import com.smack.mdadil2019.smack.data.network.model.ChannelResponse;
import com.smack.mdadil2019.smack.data.prefs.AppPreferencesHelper;
import com.smack.mdadil2019.smack.ui.chat.NavDrawerMVP;
import com.smack.mdadil2019.smack.ui.chat.NavDrawerPresenter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class NavDrawerModule {

    @Provides
    NavDrawerMVP.Presenter provideNavDrawerPresenter(AppPreferencesHelper appPreferencesHelper, ChannelService channelService,
                                                     ChannelResponse channelResponse, MessageService messageService){
        return new NavDrawerPresenter(appPreferencesHelper, channelService,channelResponse,messageService);
    }

    @Provides
    ChannelService provideChannelService(Retrofit retrofit){
        return retrofit.create(ChannelService.class);
    }

    @Provides
    MessageService provideMessageService(Retrofit retrofit){
        return retrofit.create(MessageService.class);
    }

    @Provides
    ChannelResponse provideChannelResponse(){
        return new ChannelResponse();
    }
}
