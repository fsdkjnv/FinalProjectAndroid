package com.example.drawer.fragment.FragmentHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawer.Data.DataHome.DataManager;
import com.example.drawer.Data.DataHome.DataDevice;
import com.example.drawer.Adapter.AdapterHome.DeviceAdapter;
import com.example.drawer.Data.MyDataSingleton;
import com.example.drawer.R;
import com.example.drawer.ShareView.Database;

import java.util.ArrayList;
import java.util.List;

public class DeviceFragment extends Fragment {
    RecyclerView recyclerView;
    List<DataDevice> dataList;
    DeviceAdapter adapter;
    CardView CardDevice;
    Database database;
    String toolbarTitle;
// In HomeFragment or the next fragment
     String userEmail = MyDataSingleton.getInstance().getUserEmail();
// Now you have the userEmail value in your fragment
   TextView txtRoom;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_detail, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            toolbarTitle = bundle.getString("Title");
        }

        dataList = loadDataFromSharedPreferences();

        // Cấu hình Toolbar
        CardDevice = rootView.findViewById(R.id.cardViewDevice);
        txtRoom = rootView.findViewById(R.id.txtRoom);
        txtRoom.setText(toolbarTitle);
        CardDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            }
        });

        recyclerView = rootView.findViewById(R.id.recyclerViewDevice);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        if (dataList == null) {
            dataList = new ArrayList<>(); // Khởi tạo danh sách mới nếu dataList là null
        }

        if (dataList.isEmpty()) {
            DataDevice androidData = new DataDevice("Basil", "Herb", "Herb", R.drawable.cay_1);
            dataList.add(androidData);

            androidData = new DataDevice("Mint", "Herb", "Herb", R.drawable.cay_1);
            dataList.add(androidData);

            androidData = new DataDevice("Lemon Balm", "Herb", "Herb", R.drawable.cay_1);
            dataList.add(androidData);
        }

        adapter = new DeviceAdapter(getActivity(), dataList, toolbarTitle, userEmail);
        recyclerView.setAdapter(adapter);

        ImageView addButton = rootView.findViewById(R.id.Adddevice);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataDevice androidData = new DataDevice("Bóng đèn", "Herb", "", R.drawable.cay_1);
                DataManager.getInstance().addDeviceToRoom(toolbarTitle, androidData);

                dataList.add(androidData); // Thêm vào danh sách hiện tại
                saveDataToSharedPreferences(dataList); // Lưu vào SharedPreferences

                adapter.notifyDataSetChanged();
            }
        });


        return rootView;
    }

    private List<DataDevice> loadDataFromSharedPreferences() {
        database = new Database(requireContext());
        return database.getRecyclerViewDataDevice(userEmail,toolbarTitle);
    }

    private void saveDataToSharedPreferences(List<DataDevice> dataList) {
        database.saveRecyclerViewDataDevice(userEmail,toolbarTitle, dataList);
    }
}
