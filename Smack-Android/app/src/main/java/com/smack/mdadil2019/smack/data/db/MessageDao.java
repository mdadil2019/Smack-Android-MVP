package com.smack.mdadil2019.smack.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.smack.mdadil2019.smack.data.network.model.MessageResponse;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM messageresponse WHERE channelId LIKE :channelId")
    Flowable<List<MessageResponse>> getAllMessages(String channelId);

    @Insert
    void insertMessage(MessageResponse messageResponse);
}
