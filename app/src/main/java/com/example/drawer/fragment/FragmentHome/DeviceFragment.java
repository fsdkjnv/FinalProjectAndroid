package com.example.drawer.fragment.FragmentHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawer.Data.DataHome.DataManager;
import com.example.drawer.Data.DataHome.DataDevice;
import com.example.drawer.Adapter.AdapterHome.DeviceAdapter;
import com.example.drawer.R;

import java.util.List;

public class DeviceFragment extends Fragment {
    /*Các biến recyclerView, dataList, adapter, toolbar và toolbarTitle được khai báo ở đây.*/
    RecyclerView recyclerView;
    List<DataDevice> dataList;
    DeviceAdapter adapter;
    CardView CardDevice; // CardView chứa toàn bộ thông tin

    String toolbarTitle;
    private static final String PREF_NAME = "DevicePreferences";
    private static final String KEY_DEVICE_LIST = "deviceList";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {//Phương thức onCreateView được ghi đè để tạo giao diện người dùng cho Fragment.


        View rootView = inflater.inflate(R.layout.activity_detail, container, false);//Dòng này tạo ra View từ activity_detail.xml và gán cho rootView.
       // Lấy dữ liệu từ Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
           toolbarTitle = bundle.getString("Title");
        }
        dataList = DataManager.getInstance().getRoomDevices(toolbarTitle);

        // Cấu hình Toolbar
        CardDevice = rootView.findViewById(R.id.cardViewDevice);

        CardDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event (Navigate back or perform any desired action)
                if (getActivity() != null) {
                    getActivity().onBackPressed(); // This will simulate pressing the back button
                }
            }
        });
        // tìm Toolbar trong rootView và cấu hình nó.

        //Dòng này tìm RecyclerView trong rootView và cấu hình cho nó.

        recyclerView = rootView.findViewById(R.id.recyclerViewDevice);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        //Đoạn mã trên thêm dữ liệu mẫu vào danh sách dataList.
        if (dataList.isEmpty()){
        DataDevice androidData = new DataDevice("Bóng đèn", 1, "", R.drawable.cay_1);
        dataList.add(androidData);

        androidData = new DataDevice("Bóng đèn", 2, "", R.drawable.cay_1);
        dataList.add(androidData);

        androidData = new DataDevice("Bóng đèn", 3, "", R.drawable.cay_1);
        dataList.add(androidData);
        }


        //Dòng trên tạo adapter mới và thiết lập cho RecyclerView, cũng như xử lý sự kiện khi nhấn nút "Thêm"
        adapter = new DeviceAdapter(getActivity(), dataList);
        recyclerView.setAdapter(adapter);
        ImageView addButton = rootView.findViewById(R.id.Adddevice);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataDevice androidData = new DataDevice("Bóng đèn", 3, "", R.drawable.cay_1);
                DataManager.getInstance().addDeviceToRoom(toolbarTitle, androidData);

                adapter.notifyDataSetChanged(); // Cập nhật lại dữ liệu trong Adapter

            }
        });

        return rootView;
    }

}
