package com.example.drawer.Adapter.AdapterHome;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawer.Data.DataHome.DataDevice;
import com.example.drawer.R;
import com.example.drawer.ShareView.FirebaseDatabaseHelper;

public class DeviceAdapter extends RecyclerView.Adapter<MyViewHolderDevice> {
    FirebaseDatabaseHelper firebaseDatabaseHelper;

    private Context context; // Ngữ cảnh của ứng dụng
    private List<DataDevice> dataList; // Danh sách dữ liệu thiết bị
    private String toolbarTitle; // Thêm biến để lưu trữ toolbarTitle
    private String userEmail;

    // Phương thức khởi tạo của DeviceAdapter, được sử dụng để khởi tạo adapter với ngữ cảnh và danh sách dữ liệu được cung cấp
   public DeviceAdapter(Context context, List<DataDevice> dataList, String toolbarTitle, String userEmail) {
        this.context = context;
        this.dataList = dataList != null ? dataList : new ArrayList<>();
        this.toolbarTitle = toolbarTitle;
        this.userEmail = userEmail;
        this.firebaseDatabaseHelper = new FirebaseDatabaseHelper(); // Add this line

    }

    // Phương thức được ghi đè để tạo một ViewHolder mới từ tệp nguồn recycler_item_device
    @NonNull
    @Override
    public MyViewHolderDevice onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_device, parent, false);

        return new MyViewHolderDevice(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderDevice holder, int position) {
        DataDevice dataclass = dataList.get(position);
        if (dataclass == null) {
            return;
        }
        // Nhận Bundle từ Fragment gửi đi
        holder.recImage.setImageResource(dataclass.getDataImage()); // Đặt hình ảnh cho ImageView
        holder.recTitle.setText(String.valueOf(dataclass.getDataTitle())); // Đặt tiêu đề với dữ liệu được trả về

        holder.recDesc.setText(String.valueOf(dataclass.getDataDesc())); // Gán mô tả với dữ liệu tiêu đề
        holder.nhietdo.setText(String.valueOf(dataclass.getNhietdo()));
        holder.doam.setText(String.valueOf(dataclass.getDoam()));
        holder.switchButton.setChecked(dataclass.isSwitchOn());
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
                            .setTitle("Confirm deletion")
                            .setMessage("Are you sure you want to delete this item?")
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                   String encodedEmail = encodeEmail(userEmail);

                                    dataList.remove(currentPosition);
                                    firebaseDatabaseHelper.saveRecyclerViewDataDevice(encodedEmail,toolbarTitle, dataList);
                                    notifyItemRemoved(currentPosition);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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
        holder.recCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {

                    new AlertDialog.Builder(context)
                            .setTitle("Confirm deletion")
                            .setMessage("Are you sure you want to delete this item?")
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                   String encodedEmail = encodeEmail(userEmail);
                                    dataList.remove(currentPosition);
                                   Log.e("Firebase", "Error deleting data: " );
                                    firebaseDatabaseHelper.saveRecyclerViewDataDevice(encodedEmail,toolbarTitle, dataList);

                                    notifyItemRemoved(currentPosition);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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
        // Regular click listener
        // Đoạn code này nên được đặt trong phương thức onBindViewHolder của Adapter
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle visibility of recCardTemp
                int targetHeight = holder.linearLayout.getHeight() == dpToPx(175) ? dpToPx(70) : dpToPx(175);
                animateViewHeight(holder.linearLayout, targetHeight);
            }
        });

                 holder.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        String encodedEmail = encodeEmail(userEmail);

                        // Assuming dataclass is the current item in the list that corresponds to the SwitchButton
                        dataclass.setSwitchOn(isChecked);

                        if (isChecked) {
                            Toast.makeText(context, "Watering is turned on", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Watering is turned off", Toast.LENGTH_SHORT).show();
                        }

                        // Save the updated data to Firebase
                        firebaseDatabaseHelper.saveRecyclerViewDataDevice(encodedEmail, toolbarTitle, dataList);
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

// Helper method to animate the height change
    private void animateViewHeight(final View view, final int targetHeight) {
        ValueAnimator anim = ValueAnimator.ofInt(view.getMeasuredHeight(), targetHeight);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        anim.setDuration(300); // Adjust the duration as needed
        anim.start();
    }
    private int dpToPx(int dp) {
    float density = context.getResources().getDisplayMetrics().density;
    return Math.round((float) dp * density);
    }
        public void setData(List<DataDevice> newData) {
        // Clear the existing data
        dataList.clear();

        // Add the new data
        dataList.addAll(newData);

        // Notify the adapter that the data set has changed
        notifyDataSetChanged();
    }

}

class MyViewHolderDevice extends RecyclerView.ViewHolder {

    public Switch switchButton;
    ImageView recImage;
    TextView recTitle, recDesc;
    LinearLayout linearLayout;
    CardView recCard, recCardTemp;
    TextView nhietdo, doam;

    public MyViewHolderDevice(@NonNull View itemView) {
        super(itemView);
        switchButton = itemView.findViewById(R.id.switchButton); // Điều khiển chuyển đổi
        recImage = itemView.findViewById(R.id.recImagedevice); // Hình ảnh
        recTitle = itemView.findViewById(R.id.recTitledevice); // Tiêu đề
        recDesc = itemView.findViewById(R.id.recDescdevice); // Mô tả
        recCard = itemView.findViewById(R.id.recCardevice); // Thẻ
        recCardTemp = itemView.findViewById(R.id.recCardtemp); // Thẻ
        linearLayout = itemView.findViewById(R.id.layoutdevice); // Thẻ
        nhietdo = itemView.findViewById(R.id.nhietdo); // Hình ảnh
        doam = itemView.findViewById(R.id.doam); // Tiêu đề

    }
}
