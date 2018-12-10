package com.smack.mdadil2019.smack.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.smack.mdadil2019.smack.data.network.model.MessageResponse;

@Database(entities = {MessageResponse.class},version = 1)

public abstract class MessageDatabase extends RoomDatabase {

    private static final String DB_NAME = "messageDb";
    private static volatile MessageDatabase instance;

    public static synchronized MessageDatabase getInstance(Context context){
        if(instance == null){
            instance = create(context);
        }
        return instance;
    }

    private static MessageDatabase create(Context context) {
        return Room.databaseBuilder(context,MessageDatabase.class,DB_NAME).build();
    }

    public abstract MessageDao getMessageDao();
}
