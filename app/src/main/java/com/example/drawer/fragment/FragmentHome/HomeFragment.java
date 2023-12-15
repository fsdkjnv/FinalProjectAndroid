package com.example.drawer.fragment.FragmentHome;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.AlertDialog;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawer.Adapter.AdapterHome.MyAdapter;
import com.example.drawer.Data.DataHome.DataClass;
import com.example.drawer.R;
import com.example.drawer.ShareView.Database;
import com.example.drawer.ShareView.SharedViewModel;

import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    MyAdapter adapter;
    private SharedViewModel sharedViewModel;
    private Database database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Đọc dữ liệu từ SharedPreferences khi Fragment được tạo
        List<DataClass> dataList = loadDataFromSharedPreferences();

        adapter = new MyAdapter(getActivity(), dataList);
        recyclerView.setAdapter(adapter);

        ImageView addButton = rootView.findViewById(R.id.imageViewAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddItemDialog();
            }
        });

        return rootView;
    }

    private void showAddItemDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_item, null);
        EditText titleEditText = dialogView.findViewById(R.id.editText);
        titleEditText.requestFocus();

        builder.setView(dialogView)
                .setTitle("Thêm mục mới")
                .setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String title = titleEditText.getText().toString();
                        DataClass androidData = new DataClass(title, R.string.rating, "", R.drawable.phongkhach);
                        sharedViewModel.getInstance().addRoom(androidData);
                        adapter.notifyDataSetChanged();
                        saveDataToSharedPreferences(sharedViewModel.getInstance().getDataList());
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void saveDataToSharedPreferences(List<DataClass> dataList) {
        database = new Database(requireContext());
        database.saveRecyclerViewData(dataList);
    }

    private List<DataClass> loadDataFromSharedPreferences() {
        // Đọc dữ liệu từ SharedPreferences
        database = new Database(requireContext());
        List<DataClass> dataList = database.getRecyclerViewData();

        // Kiểm tra xem dữ liệu có tồn tại không và sử dụng nó (ví dụ: đặt vào RecyclerView adapter)
        if (dataList != null) {
            sharedViewModel.getInstance().setDataList(dataList);
        }

        return dataList;
    }

}

