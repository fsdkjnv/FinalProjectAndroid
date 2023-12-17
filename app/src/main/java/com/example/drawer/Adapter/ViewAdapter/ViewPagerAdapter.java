package com.example.drawer.Adapter.ViewAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.drawer.fragment.FragmentLogin_Logout.ui.FragmentLogin;
import com.example.drawer.fragment.FragmentLogin_Logout.ui.Signup_Fragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1){
            return new Signup_Fragment();
        }
        return new FragmentLogin();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}