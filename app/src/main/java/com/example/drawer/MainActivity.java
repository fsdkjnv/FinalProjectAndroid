package com.example.drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.drawer.fragment.SlideshowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.os.Handler;

import com.example.drawer.fragment.FavoriteFragment;
import com.example.drawer.fragment.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    // toi la ai
    // Khai báo và ánh xạ đối tượng BottomNavigationView và các hằng số Fragment
    private BottomNavigationView bottomNavigationView;
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_FAVORITE = 1;
    private static final int FRAGMENT_SLIDESHOW = 2;
    private int mCurrentFragment = FRAGMENT_HOME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.nav_home:
                                // Xử lý khi người dùng nhấn vào mục Home
                                replaceFragment(new HomeFragment());
                                mCurrentFragment = FRAGMENT_HOME;
                                return true;
                            case R.id.nav_favorite:
                                // Xử lý khi người dùng nhấn vào mục Favorite
                                replaceFragment(new FavoriteFragment());
                                mCurrentFragment = FRAGMENT_FAVORITE;
                                return true;
                            case R.id.nav_slideshow:
                                // Xử lý khi người dùng nhấn vào mục Slideshow
                                replaceFragment(new SlideshowFragment());
                                mCurrentFragment = FRAGMENT_SLIDESHOW;
                                return true;
                        }
                        return false;
                    }
                });

        // Thiết lập fragment mặc định
        replaceFragment(new HomeFragment());
    }

    @Override
    public void onBackPressed() {
        if (mCurrentFragment != FRAGMENT_HOME) {
            // Nếu không ở mục Home, quay về mục Home
            replaceFragment(new HomeFragment());
            bottomNavigationView.setSelectedItemId(R.id.nav_home);
            mCurrentFragment = FRAGMENT_HOME;
        } else {
            super.onBackPressed();
        }
    }

        // Hàm thay thế Fragment hiện tại bằng một Fragment mới
    private void replaceFragment(Fragment fragment) {
        // Bắt đầu một giao dịch Fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment); // Thay thế Fragment hiện tại bằng Fragment mới
        transaction.commit(); // Hoàn thành giao dịch và áp dụng thay đổi
    }

}

