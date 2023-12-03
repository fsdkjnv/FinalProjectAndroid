package com.example.drawer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.drawer.R;

public class FavoriteFragment extends Fragment {
    // Đây là một lớp Fragment dành cho màn hình "Yêu thích" trong ứng dụng Android của bạn.

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Phương thức này được gọi để tạo giao diện cho fragment.

        // LayoutInflater được sử dụng để bố trí (render) giao diện của fragment.
        // "inflater.inflate(R.layout.fragment_favorite, container, false);" bố trí (render) giao diện từ tệp layout fragment_favorite.xml.

        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }
}
