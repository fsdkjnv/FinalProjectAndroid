package com.example.drawer.ShareView;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.drawer.Data.DataHome.DataClass;
import com.example.drawer.Data.DataHome.DataDevice;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private static final String NODE_USERS = "users"; // Tên của node chứa thông tin người dùng

    private DatabaseReference databaseReference;

    public FirebaseDatabaseHelper() {
        // Khởi tạo DatabaseReference từ Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    // Ghi dữ liệu cho DataClass
    public void saveRecyclerViewData(String userId, List<DataClass> dataList) {
        DatabaseReference userRef = databaseReference.child(NODE_USERS).child(userId);
        userRef.child("recyclerViewData").setValue(dataList);
    }

    // Đọc dữ liệu cho DataClass

    // Đọc dữ liệu cho DataClass
public void getRecyclerViewData(String userId, OnDataLoadedListener onDataLoadedListener) {
    final List<DataClass> dataList = new ArrayList<>();
    DatabaseReference userRef = databaseReference.child(NODE_USERS).child(userId);

    userRef.child("recyclerViewData").addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                GenericTypeIndicator<List<DataClass>> type = new GenericTypeIndicator<List<DataClass>>() {};
                List<DataClass> newDataList = dataSnapshot.getValue(type);
                if (newDataList != null) {
                    dataList.addAll(newDataList);
                }
                // Gọi callback khi dữ liệu đã được tải
                onDataLoadedListener.onDataLoaded(dataList);
            } else {
                // Gọi callback với danh sách trống khi không có dữ liệu
                onDataLoadedListener.onDataLoaded(new ArrayList<DataClass>());
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            // Xử lý khi có lỗi đọc dữ liệu
            onDataLoadedListener.onCancelled(databaseError.getMessage());
        }
    });
}

// Interface để định nghĩa các phương thức callback
public interface OnDataLoadedListener {
    void onDataLoaded(List<DataClass> dataList);

    void onCancelled(String errorMessage);
}


    // Ghi dữ liệu cho DataDevice
    public void saveRecyclerViewDataDevice(String userId, String key, List<DataDevice> dataList) {
        DatabaseReference userRef = databaseReference.child(NODE_USERS).child(userId);
        userRef.child("recyclerViewDataDevice").child(key).setValue(dataList);
    }
   public static String encodeEmail(String email) {
        return email.replace(".", ",");
    }
    // Đọc dữ liệu cho DataDevice
   public void getRecyclerViewDataDevice(String userId, String key, final OnDataLoadedListenerDevice onDataLoadedListener) {
    DatabaseReference userRef = databaseReference.child(NODE_USERS).child(userId);

    userRef.child("recyclerViewDataDevice").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            List<DataDevice> dataList = new ArrayList<>();
            if (dataSnapshot.exists()) {
                GenericTypeIndicator<List<DataDevice>> type = new GenericTypeIndicator<List<DataDevice>>() {};
                List<DataDevice> newDataList = dataSnapshot.getValue(type);
                if (newDataList != null) {
                    dataList.addAll(newDataList);

                    // Notify the listener with the loaded data
                    onDataLoadedListener.onDataLoaded(dataList);
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            // Notify the listener about the cancellation
            onDataLoadedListener.onCancelled(databaseError.getMessage());
        }
    });
}

public interface OnDataLoadedListenerDevice<T> {
    void onDataLoaded(List<T> dataList);

    void onCancelled(String errorMessage);
}


    // Xóa dữ liệu cho DataDevice
    public void deleteRecyclerViewDataDevice(String userId, String key) {
        DatabaseReference userRef = databaseReference.child(NODE_USERS).child(userId);
        userRef.child("recyclerViewDataDevice").child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("Firebase", "Delete operation successful");
                } else {
                    Log.e("Firebase", "Error deleting data: " + task.getException());
                }
            }
        });
    }
}

