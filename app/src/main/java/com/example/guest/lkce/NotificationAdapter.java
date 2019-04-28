package com.example.guest.lkce;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> items = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();

    public void update(String name, String url) {

        items.add(name);
        urls.add(url);
        notifyDataSetChanged();
    }

    public NotificationAdapter(RecyclerView recyclerView, Context context, ArrayList<String> items, ArrayList<String> urls) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.items = items;
        this.urls = urls;
    }

    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.notificationitems, parent, false);
        return new NotificationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {
        holder.messageFiles.setText(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView messageFiles;

        public ViewHolder(View itemView) {
            super(itemView);
            messageFiles = itemView.findViewById(R.id.notificationFiles);

        }
    }
}

