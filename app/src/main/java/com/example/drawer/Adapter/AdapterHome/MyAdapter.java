package com.example.drawer.Adapter.AdapterHome;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlertDialog;

import com.example.drawer.Data.DataHome.DataClass;
import com.example.drawer.R;
import com.example.drawer.ShareView.FirebaseDatabaseHelper;
import com.example.drawer.fragment.FragmentHome.DeviceFragment;
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<DataClass> dataList;
// In HomeFragment or the next fragment
// Now you have the userEmail value in your fragment
    private String userEmail;
    private String nameHome;

    FirebaseDatabaseHelper firebaseDatabaseHelper;
    String encodedEmail;

    public MyAdapter(Context context, List<DataClass> dataList, String userEmail) {
        this.context = context;
        this.dataList = dataList != null ? dataList : new ArrayList<>();
        this.userEmail = userEmail;
        this.firebaseDatabaseHelper = new FirebaseDatabaseHelper(); // Add this line

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataClass dataclass = dataList.get(position);
        if (dataclass == null) {
            return;
        }
        holder.recImage.setImageResource(dataclass.getDataImage());
        holder.recTitle.setText(dataclass.getDataTitle());
        holder.recLang.setText(dataclass.getDataLang());
        holder.recDesc.setText(String.valueOf(dataclass.getDataDesc()));


        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    DataClass dataclass = dataList.get(adapterPosition);

                    DeviceFragment deviceFragment = new DeviceFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("Position", adapterPosition);
                    bundle.putString("Title", dataclass.getDataTitle());
                    deviceFragment.setArguments(bundle);

                    FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame, deviceFragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });


        holder.recCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                int adapterPosition = holder.getAdapterPosition();

                if (currentPosition != RecyclerView.NO_POSITION) {
                    DataClass dataclass = dataList.get(adapterPosition);
                    String title = dataclass.getDataTitle();

                    AlertDialog dialog = new AlertDialog.Builder(context)
                            .setTitle("Confirm deletion")
                            .setMessage("Are you sure you want to delete this item?")
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String encodedEmail = encodeEmail(userEmail);
                                    dataList.remove(currentPosition);
                                    // Lưu dữ liệu vào SharedPreferences sau khi xóa
                                    firebaseDatabaseHelper.saveRecyclerViewData(encodedEmail, dataList);
                                    firebaseDatabaseHelper.deleteRecyclerViewDataDevice(encodedEmail, title);
                                    notifyItemRemoved(currentPosition);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();

                    // Set background drawable for the dialog window
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.rounded_corner);

                    dialog.show();
                }
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

     public static String encodeEmail(String email) {
        return email.replace(".", ",");
    }

}

class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView recImage; // Ảnh của mục
    TextView recTitle, recDesc, recLang; // Tiêu đề, Mô tả và Ngôn ngữ của mục
    CardView recCard; // CardView chứa toàn bộ thông tin

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        recImage = itemView.findViewById(R.id.recImage); // Tìm và gán ImageView từ itemView
        recTitle = itemView.findViewById(R.id.recTitle); // Tìm và gán TextView cho tiêu đề từ itemView
        recDesc = itemView.findViewById(R.id.recDesc); // Tìm và gán TextView cho mô tả từ itemView
        recLang = itemView.findViewById(R.id.recLang); // Tìm và gán TextView cho ngôn ngữ từ itemView
        recCard = itemView.findViewById(R.id.recCard); // Tìm và gán CardView từ itemView
    }
}

