package com.example.drawer.fragment.FragmentMe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.drawer.Data.MyDataSingleton;
import com.example.drawer.LoginandSignup;
import com.example.drawer.R;

public class SlideshowFragment extends Fragment {
    private Button btn_logout;
    private SharedPreferences sharedPreferences;
    private TextView email; // Declare TextView
    String userEmail = MyDataSingleton.getInstance().getUserEmail();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sildeshow, container, false);
        email = view.findViewById(R.id.email_textview); // Initialize TextView

        btn_logout = view.findViewById(R.id.logout_button);
        sharedPreferences = getActivity().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        email.setText(userEmail);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xóa thông tin đăng nhập từ SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("loggedIn");
                editor.apply();

                // Mở lại trang đăng nhập
                startActivity(new Intent(getActivity(), LoginandSignup.class));
                getActivity().finish();
            }
        });

        return view;
    }
}
