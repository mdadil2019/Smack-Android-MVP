package com.smack.mdadil2019.smack.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smack.mdadil2019.smack.R;
import com.smack.mdadil2019.smack.data.network.model.MessageResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyChatViewHolder> {

    ArrayList<MessageResponse> messages;
    Context mContext;
    public ChatAdapter(ArrayList<MessageResponse> data, Context context){
        messages = data;
        mContext = context;

    }
    @NonNull
    @Override
    public MyChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.message_layout,viewGroup,false);
        return new MyChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyChatViewHolder holder, int i) {
        holder.userNameTv.setText(messages.get(i).getUserName());
        holder.timeTv.setText(messages.get(i).getTimeStamp());
        holder.messageTv.setText(messages.get(i).getMessageBody());

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MyChatViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewUserName)
        TextView userNameTv;

        @BindView(R.id.textViewTime)
        TextView timeTv;

        @BindView(R.id.textViewMessage)
        TextView messageTv;

        @BindView(R.id.imageViewMessage)
        ImageView avatarIv;
        public MyChatViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
