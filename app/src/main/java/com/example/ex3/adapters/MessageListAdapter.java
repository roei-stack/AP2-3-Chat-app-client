package com.example.ex3.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex3.R;
import com.example.ex3.entities.Message;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageViewHolder> {
    private final LayoutInflater mInflater;
    private List<Message> mMessageList;

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;

        MessageViewHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.content_text);
            timeText = (TextView) itemView.findViewById(R.id.time_text);
        }

        void bind(Message message) {
            messageText.setText(message.getContent());

            // Format the stored timestamp into a readable String using method.
            timeText.setText(message.getCreated().substring(0, message.getCreated().length() - 3));


            messageText.setText(message.getContent());
        }
    }

    public MessageListAdapter(Context context, List<Message> messageList) {
        mInflater = LayoutInflater.from(context);
        mMessageList = messageList;
    }

    @Override
    public int getItemCount() {
        if (mMessageList == null) {
            return 0;
        }
        return mMessageList.size();
    }

    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        Message message = mMessageList.get(position);

        if (message.isSent()) {
            return 1;
        }
        return 2;
    }

    // Inflates the appropriate layout according to the ViewType.
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 1) {
            view = mInflater.inflate(R.layout.sender_me, parent, false);
            return new MessageViewHolder(view);
        }
        if (viewType == 2) {
            view = mInflater.inflate(R.layout.sender_other, parent, false);
            return new MessageViewHolder(view);
        }
        return null;
    }

    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        Message message = mMessageList.get(position);
        holder.bind(message);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMessageList(List<Message> messageList) {
        this.mMessageList = messageList;
        notifyDataSetChanged();
    }
}
