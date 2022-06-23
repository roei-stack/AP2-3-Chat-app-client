package com.example.ex3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex3.R;
import com.example.ex3.entities.Contact;

import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactsViewHolder> {

    class ContactsViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvNickname;
        private final TextView tvLastMessage;
        private final TextView tvLastDate;
        private final ImageView contactImg;

        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNickname = itemView.findViewById(R.id.tvNickname);
            tvLastMessage = itemView.findViewById(R.id.tvLastMessage);
            tvLastDate = itemView.findViewById(R.id.tvLastDate);
            contactImg = itemView.findViewById(R.id.contactImg);
        }
    }

    private final LayoutInflater mInflater;
    private List<Contact> contacts;

    public ContactsListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactItemView = mInflater.inflate(R.layout.contact_item, parent, false);
        return new ContactsViewHolder(contactItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        if (contacts == null) {
            return;
        }
        final Contact current = contacts.get(position);
        holder.tvNickname.setText(current.getName());
        holder.tvLastMessage.setText(current.getLast());
        holder.tvLastDate.setText(current.getLastdate());
        //holder.contactImg.setImageURI(Uri.parse(current.getImage()));
    }

    @Override
    public int getItemCount() {
        if (contacts == null) { return 0; }
        return contacts.size();
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }
}
