package com.example.android.newsappproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder    > {
    private ArrayList<Item> items;

    // TODO constructor
    // temporarily it will be ArrayList<Item>; in next step it will be List<Item>


    public RecyclerAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // TextViews in cardview
        TextView title, author, publishDate, content, sectionName;


        public ViewHolder(View itemView) {
            super(itemView);
            // find all views in card view
            title = itemView.findViewById(R.id.article_title);
            author = itemView.findViewById(R.id.author);
            publishDate = itemView.findViewById(R.id.publish_date);
            content = itemView.findViewById(R.id.article_content);
            sectionName = itemView.findViewById(R.id.section_name);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.author.setText(items.get(position).getAuthor());
        holder.publishDate.setText(items.get(position).getPublishDate());
        holder.content.setText(items.get(position).getContent());
        holder.sectionName.setText(items.get(position).getSectionName());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
