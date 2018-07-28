package com.example.android.newsappproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Item> items;

    // constructor
    public RecyclerAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapter.ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.author.setText(items.get(position).getAuthor());
        holder.publishDate.setText(items.get(position).getPublishDate());
        holder.content.setText(items.get(position).getContent());
        holder.sectionName.setText(items.get(position).getSectionName());

        String publish = items.get(position).getPublishDate();
        String publishSeparate = publish.replace("T", ", ");
        publishSeparate = publishSeparate.replace("Z", "");

        holder.publishDate.setText(publishSeparate);

        // set click listener
        holder.singleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                String webUrl = items.get(position).getWebUrl();
                openWebPage(holder.singleCard.getContext(), webUrl);
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void openWebPage(Context context, String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(context, intent, null);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // TextViews in cardview
        TextView title, author, publishDate, content, sectionName;
        CardView singleCard;


        ViewHolder(View itemView) {
            super(itemView);
            // find all views in card view
            title = itemView.findViewById(R.id.article_title);
            author = itemView.findViewById(R.id.author);
            publishDate = itemView.findViewById(R.id.publish_date);
            content = itemView.findViewById(R.id.article_content);
            sectionName = itemView.findViewById(R.id.section_name);
            singleCard = itemView.findViewById(R.id.single_card);
        }
    }


}
