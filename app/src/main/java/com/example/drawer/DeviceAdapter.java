package com.example.drawer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.SeekBar;

import java.util.List;
import android.app.AlertDialog;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawer.DataDevice;
import com.example.drawer.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
public class DeviceAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context; // Ngữ cảnh của ứng dụng
    private List<DataDevice> dataList; // Danh sách dữ liệu thiết bị

    public static String convertDeviceListToJson(List<DataDevice> dataList) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<DataDevice>>() {}.getType();
        return gson.toJson(dataList, type);
    }
    public static List<DataDevice> convertJsonToDeviceList(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<DataDevice>>() {}.getType();
        return gson.fromJson(json, type);
    }
    // Phương thức này được sử dụng để đặt danh sách tìm kiếm mới và thông báo cho adapter biết rằng dữ liệu đã thay đổi
    public void setSearchList(List<DataDevice> dataSearchList){
        this.dataList = dataSearchList;
        notifyDataSetChanged(); // Thông báo cho adapter biết rằng dữ liệu đã thay đổi
    }

    // Phương thức khởi tạo của DeviceAdapter, được sử dụng để khởi tạo adapter với ngữ cảnh và danh sách dữ liệu được cung cấp
    public DeviceAdapter(Context context, List<DataDevice> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    // Phương thức được ghi đè để tạo một ViewHolder mới từ tệp nguồn recycler_item_device
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_device, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataDevice dataclass = dataList.get(position);
        if (dataclass == null) {
            return;
        }
        // Nhận Bundle từ Fragment gửi đi
        holder.recImage.setImageResource(dataclass.getDataImage()); // Đặt hình ảnh cho ImageView
        holder.recTitle.setText(String.valueOf(dataclass.getDataTitle())); // Đặt tiêu đề với dữ liệu được trả về

        holder.recDesc.setText(""); // Gán mô tả với dữ liệu tiêu đề

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Thêm mã xử lý sự kiện ở đây nếu cần thiết
            }
        });

        holder.recCard.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION) {
                new AlertDialog.Builder(context)
                        .setTitle("Xác nhận xóa")
                        .setMessage("Bạn có chắc chắn muốn xóa mục này không?")
                        .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Xóa mục tại vị trí "currentPosition" trong dataList
                                dataList.remove(currentPosition);
                                // Notify adapter about data changes and reflect the changes in the UI
                                notifyItemRemoved(currentPosition);
                            }
                        })
                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
            return true;
        }
    });
    holder.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if (isChecked) {
                holder.recImage.setImageResource(R.drawable.bongdensang); // Đặt hình ảnh khi nút được bật
            } else {
                holder.recImage.setImageResource(R.drawable.bongdenoff); // Đặt hình ảnh khi nút được tắt
            }
        }
    });


 }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    public Switch switchButton;
    ImageView recImage;
    TextView recTitle, recDesc;

    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        switchButton = itemView.findViewById(R.id.switchButton); // Điều khiển chuyển đổi
        recImage = itemView.findViewById(R.id.recImagedevice); // Hình ảnh
        recTitle = itemView.findViewById(R.id.recTitledevice); // Tiêu đề
        recDesc = itemView.findViewById(R.id.recDescdevice); // Mô tả
        recCard = itemView.findViewById(R.id.recCardevice); // Thẻ

    }
}
