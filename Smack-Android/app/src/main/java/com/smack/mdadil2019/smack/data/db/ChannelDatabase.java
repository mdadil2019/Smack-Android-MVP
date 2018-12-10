package com.smack.mdadil2019.smack.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.smack.mdadil2019.smack.data.network.model.ChannelResponse;

@Database(entities = {ChannelResponse.class},version = 1)

public abstract class ChannelDatabase extends RoomDatabase {

    private static final String DB_NAME = "channelDb";
    private static volatile ChannelDatabase instance;

    public static synchronized ChannelDatabase getInstance(Context context){
        if(instance == null){
            instance = create(context);
        }
        return instance;
    }

    private static ChannelDatabase create(Context context) {
        return Room.databaseBuilder(context,ChannelDatabase.class,DB_NAME).build();
    }

    public abstract ChannelDao getChannelDao();
}
