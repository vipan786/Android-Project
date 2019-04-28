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

public class MarksAdapter extends RecyclerView.Adapter<MarksAdapter.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList<String> items = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();

    public void update(String name, String url) {

        items.add(name);
        urls.add(url);
        notifyDataSetChanged();
    }

    public MarksAdapter(RecyclerView recyclerView, Context context, ArrayList<String> items, ArrayList<String> urls) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.items = items;
        this.urls = urls;
    }

    @Override
    public MarksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.marksitems, parent, false);
        return new MarksAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarksAdapter.ViewHolder holder, int position) {
        holder.marksFiles.setText(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView marksFiles;

        public ViewHolder(View itemView) {
            super(itemView);
            marksFiles = itemView.findViewById(R.id.marksFiles);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = recyclerView.getChildAdapterPosition(view);
                    Intent intent = new Intent();
                    intent.setType(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urls.get(position)));
                    context.startActivity(intent);
                }
            });

        }
    }
}
