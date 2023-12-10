package com.example.drawer.Adapter.AdapterExplore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

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
            holder.imageView.setImageResource(exploreItem.getImage()); // Đặt hình ảnh cho ImageView
            holder.textViewTitle.setText(String.valueOf(exploreItem.getTitle())); // Đặt tiêu đề với dữ liệu được trả về

            holder.textViewContent.setText(""); // Gán mô tả với dữ liệu tiêu đề
                // Tạo một phiên bản mới của DeviceFragment.
            DetailFragment detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();

            // Đặt vị trí/index của mục được nhấp vào bundle.
            bundle.putString("Title", exploreItem.getTitle());

            // Đặt bundle như là đối số cho DeviceFragment.
            detailFragment.setArguments(bundle);

            // Chuyển đến DeviceFragment.
            FragmentManager fragmentManager = ((AppCompatActivity) view.getContext()).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, detailFragment)
                    .addToBackStack(null) // Tùy chọn: Thêm giao dịch vào ngăn xếp quay lại
                    .commit();
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
