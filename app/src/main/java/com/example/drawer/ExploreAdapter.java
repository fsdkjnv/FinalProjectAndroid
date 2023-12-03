package com.example.drawer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder> {

    public ExploreAdapter(List<ExploreItem> exploreItemList) {
        this.exploreItemList = exploreItemList;
    }

    private List<ExploreItem> exploreItemList;

    @NonNull
    @Override
    public ExploreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_explore, parent, false);
        return new ExploreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreViewHolder holder, int position) {
        ExploreItem exploreItem = exploreItemList.get(position);
        holder.imageView.setImageResource(exploreItem.getImage());
        holder.textViewTitle.setText(exploreItem.getTitle());
        holder.textViewContent.setText(exploreItem.getContent());
        holder.textViewAuthor.setText(exploreItem.getAuthor());
    }

    @Override
    public int getItemCount() {
        return exploreItemList.size();
    }

    public class ExploreViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewContent;
        TextView textViewAuthor;

        public ExploreViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recImagePlant);
            textViewTitle = itemView.findViewById(R.id.recTitle);
            textViewContent = itemView.findViewById(R.id.recContent);
            textViewAuthor = itemView.findViewById(R.id.recAuthor);
        }
    }

}
