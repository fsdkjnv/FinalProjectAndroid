package com.example.drawer.Adapter.AdapterHome;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlertDialog;

import com.example.drawer.Data.DataHome.DataClass;
import com.example.drawer.R;
import com.example.drawer.fragment.FragmentHome.DeviceFragment;

public class MyAdapter extends RecyclerView.Adapter<com.example.drawer.Adapter.AdapterHome.MyViewHolder> {
    private Context context;
    private List<DataClass> dataList;

    // khai báo lớp MyAdapter kế thừa từ RecyclerView.Adapter và khởi tạo các biến context và dataList.
    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    //Phương thức onCreateViewHolder tạo một MyViewHolder mới khi cần hiển thị một item danh sách.
    public com.example.drawer.Adapter.AdapterHome.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new com.example.drawer.Adapter.AdapterHome.MyViewHolder(view);
    }

    @Override
    //Phương thức onBindViewHolder gắn dữ liệu từ dataList vào MyViewHolder để hiển thị thông tin cho mỗi item danh sách.
    public void onBindViewHolder(@NonNull com.example.drawer.Adapter.AdapterHome.MyViewHolder holder, int position) {
    DataClass dataclass = dataList.get(position);
    if (dataclass == null) {
        return;
    }
    holder.recImage.setImageResource(dataclass.getDataImage());
    holder.recTitle.setText(dataclass.getDataTitle());
    holder.recLang.setText(dataclass.getDataLang());

    final int currentPosition = position; // Declare as final

        // Xử lý sự kiện khi người dùng nhấn vào item trong danh sách
    holder.recCard.setOnClickListener(new View.OnClickListener() {
        @Override

        public void onClick(View view) {

            // Tạo một phiên bản mới của DeviceFragment.
            DeviceFragment deviceFragment = new DeviceFragment();
            Bundle bundle = new Bundle();
            // Đặt vị trí/index của mục được nhấp vào bundle.
            bundle.putString("Title", dataclass.getDataTitle());

            // Đặt bundle như là đối số cho DeviceFragment.
            deviceFragment.setArguments(bundle);

            // Chuyển đến DeviceFragment.
            FragmentManager fragmentManager = ((AppCompatActivity) view.getContext()).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, deviceFragment)
                    .addToBackStack(null) // Tùy chọn: Thêm giao dịch vào ngăn xếp quay lại
                    .commit();
        }
    });

    // Xử lý sự kiện khi người dùng nhấn giữ lâu trên item trong danh sách
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



}


    @Override
    public int getItemCount() {
        return dataList.size();
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

