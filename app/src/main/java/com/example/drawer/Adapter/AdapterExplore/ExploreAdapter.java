package com.example.drawer.Adapter.AdapterExplore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawer.Data.DataExplore.ExploreItem;
import com.example.drawer.R;
import com.example.drawer.fragment.FragmentExplore.DetailFragment;

import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder> {

    public ExploreAdapter(Context context, List<ExploreItem> exploreItemList) {
        this.exploreItemList = exploreItemList;
        this.context = context;
    }

    private Context context;

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

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DetailFragment.class);
                intent.putExtra("Image", exploreItemList.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("Title", exploreItemList.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("Content", exploreItemList.get(holder.getAdapterPosition()).getContent());
                intent.putExtra("Author", exploreItemList.get(holder.getAdapterPosition()).getAuthor());

                context.startActivity(intent);
            }
        });
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
        CardView cardView;

        public ExploreViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recImagePlant);
            textViewTitle = itemView.findViewById(R.id.recTitleExplore);
            textViewContent = itemView.findViewById(R.id.recContent);
            textViewAuthor = itemView.findViewById(R.id.recAuthor);

            cardView = itemView.findViewById(R.id.recCardExplore);
        }
    }

}
