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
import com.example.drawer.ShareView.SharedViewModel;


public class HomeFragment extends Fragment {

    //Dòng trên khai báo lớp HomeFragment kế thừa từ lớp Fragment. Đồng thời khai báo biến recyclerView, dataList và adapter
    RecyclerView recyclerView;
    MyAdapter adapter;
    private SharedViewModel sharedViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new MyAdapter(getActivity(), sharedViewModel.getInstance().getDataList());
        recyclerView.setAdapter(adapter);

        ImageView addButton = rootView.findViewById(R.id.imageViewAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị hộp thoại nhập dữ liệu ở đây
                showAddItemDialog();// Phương thức này hiển thị hộp thoại để thêm mục mới
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


        // Sử dụng Handler để tập trung vào EditText sau khi hộp thoại được hiển thị
        builder.setView(dialogView)
                .setTitle("Thêm mục mới")
                .setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String title = titleEditText.getText().toString();
                        // Làm gì đó với dữ liệu nhập vào ở đây
                        DataClass androidData = new DataClass(title, R.string.rating, "", R.drawable.phongkhach);
                        sharedViewModel.getInstance().addRoom(androidData);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();

                    }

                }
                )
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Đóng hộp thoại nếu người dùng chọn Hủy
                        dialog.dismiss();
                    }
                });
        // Thiết lập cho phép hiển thị bàn phím ứng dụng khi hộp thoại mở ra
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
