package com.example.guest.lkce;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {
    ImageView img;
    RecyclerView recyclerView;
    Context context;
    public long count;
    ArrayList<String> items = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();


    public void update(String name, String url) {

        items.add(name);
        urls.add(url);
        notifyDataSetChanged();
    }

    public BlogAdapter(RecyclerView recyclerView, Context context, ArrayList<String> items, ArrayList<String> urls) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.items = items;
        this.urls = urls;
    }

    @Override
    public BlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.blogitems, parent, false);
        return new BlogAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogAdapter.ViewHolder holder, int position) {
        holder.messageFiles.setText(items.get(position));

        Glide.with(context).load(urls.get(position)).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView messageFiles;

        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            messageFiles = itemView.findViewById(R.id.blogFiles);
            img = itemView.findViewById(R.id.image);

        }
    }
}

