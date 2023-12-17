package com.example.drawer.fragment.FragmentMe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.drawer.LoginandSignup;
import com.example.drawer.R;

public class SlideshowFragment extends Fragment {
    Button btn_logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate (nạp) một fragment layout trong Android. Hàm onCreateView trong một Fragment được sử dụng để tạo giao diện người dùng của Fragment đó.
        // Cụ thể, mã này sẽ inflate layout được đặt tên là "fragment_favorite" và hiển thị nó trong container (không gian hiển thị) của Fragment.
        View view = inflater.inflate(R.layout.fragment_sildeshow, container, false);

        btn_logout = view.findViewById(R.id.logout_button);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getActivity(), LoginandSignup.class));
               getActivity().finish();
            }
        });

        return view;
    }
}
