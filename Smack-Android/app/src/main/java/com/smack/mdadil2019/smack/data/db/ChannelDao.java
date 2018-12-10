package com.smack.mdadil2019.smack.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.smack.mdadil2019.smack.data.network.model.ChannelResponse;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

@Dao
public interface ChannelDao {
    @Query("SELECT * FROM channelresponse")
    Flowable<List<ChannelResponse>> getChannels();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertChannel(ChannelResponse channel);



}
