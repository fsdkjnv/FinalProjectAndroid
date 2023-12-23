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
    private static final String NODE_USERS = "users";
    private DatabaseReference databaseReference;

    public FirebaseDatabaseHelper() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void saveRecyclerViewData(String userId, List<DataClass> dataList) {
        DatabaseReference userRef = databaseReference.child(NODE_USERS).child(userId);
        userRef.child("recyclerViewData").setValue(dataList);
    }

    public void getRecyclerViewData(String userId, OnDataLoadedListener onDataLoadedListener) {
        final List<DataClass> dataList = new ArrayList<>();
        DatabaseReference userRef = databaseReference.child(NODE_USERS).child(userId);

        userRef.child("recyclerViewData").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DataClass data = snapshot.getValue(DataClass.class);
                    if (data != null) {
                        dataList.add(data);
                    }
                }
                onDataLoadedListener.onDataLoaded(dataList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                onDataLoadedListener.onCancelled(databaseError.getMessage());
            }
        });
    }

    public interface OnDataLoadedListener {
        void onDataLoaded(List<DataClass> dataList);

        void onCancelled(String errorMessage);
    }

    public void saveRecyclerViewDataDevice(String userId, String key, List<DataDevice> dataList) {
        DatabaseReference userRef = databaseReference.child(NODE_USERS).child(userId);
        userRef.child("recyclerViewDataDevice").child(key).setValue(dataList);
    }

    public void getRecyclerViewDataDevice(String userId, String key, final OnDataLoadedListenerDevice onDataLoadedListener) {
        final List<DataDevice> dataList = new ArrayList<>();
        DatabaseReference userRef = databaseReference.child(NODE_USERS).child(userId);

        userRef.child("recyclerViewDataDevice").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DataDevice data = snapshot.getValue(DataDevice.class);
                    if (data != null) {
                        dataList.add(data);
                    }
                }
                onDataLoadedListener.onDataLoaded(dataList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                onDataLoadedListener.onCancelled(databaseError.getMessage());
            }
        });
    }

    public interface OnDataLoadedListenerDevice<T> {
        void onDataLoaded(List<T> dataList);

        void onCancelled(String errorMessage);
    }

    public void deleteRecyclerViewDataDevice(String userId, String key) {
        DatabaseReference userRef = databaseReference.child(NODE_USERS).child(userId);
        userRef.child("recyclerViewDataDevice").child(key).removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
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


