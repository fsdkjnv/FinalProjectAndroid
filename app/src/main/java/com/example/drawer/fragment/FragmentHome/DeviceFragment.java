package com.example.drawer.fragment.FragmentHome;

import static com.example.drawer.fragment.FragmentHome.HomeFragment.encodeEmail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawer.Adapter.AdapterHome.MyAdapter;
import com.example.drawer.Data.DataHome.DataDevice;
import com.example.drawer.Adapter.AdapterHome.DeviceAdapter;
import com.example.drawer.Data.DataHome.DataManager;
import com.example.drawer.Data.MyDataSingleton;
import com.example.drawer.R;
import com.example.drawer.ShareView.FirebaseDatabaseHelper;
import com.example.drawer.ShareView.SharedViewModelDevice;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.AlertDialog;
import android.widget.EditText;

import java.util.List;




public class DeviceFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<DataDevice> dataList;
    private DeviceAdapter adapter;
    private CardView cardDevice;
    private FirebaseDatabaseHelper firebaseDatabaseHelper;
    private String encodedEmail;
    private String toolbarTitle;
    private String userEmail;
    private TextView txtRoom;
    private SharedViewModelDevice sharedViewModel;
    @Nullable
    @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_detail, container, false);
            sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModelDevice.class);
            Bundle bundle = getArguments();
            if (bundle != null) {
                toolbarTitle = bundle.getString("Title");

            }
            userEmail = MyDataSingleton.getInstance().getUserEmail();
            encodedEmail = encodeEmail(userEmail);

            firebaseDatabaseHelper = new FirebaseDatabaseHelper();
            dataList = sharedViewModel.getDeviceList(); // Retrieve dataList from ViewModel
            // Cấu hình Toolbar
            cardDevice = rootView.findViewById(R.id.cardViewDevice);
            txtRoom = rootView.findViewById(R.id.txtRoom);
            txtRoom.setText(toolbarTitle);
            cardDevice.setOnClickListener(new View.OnClickListener() {
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

            ImageView addButton = rootView.findViewById(R.id.Adddevice);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAddItemDialog();
                }
            });

           loadDataFromFirebaseData() ;
            return rootView;
        }
        public static String encodeEmail(String email) {
        return email.replace(".", ",");
       }
        private void showAddItemDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = requireActivity().getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.add_devic, null);
            EditText titleEditText = dialogView.findViewById(R.id.editText);
            EditText titleEditText_2 = dialogView.findViewById(R.id.editText_2);

            titleEditText.requestFocus();

            builder.setView(dialogView)
                    .setTitle("Create Plant")
                    .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            String title = titleEditText.getText().toString();
                            String title_2 = titleEditText_2.getText().toString();

                            boolean isSwitchOn = false;
                            DataDevice androidData = new DataDevice(title, title_2, "", R.drawable.cay_1, 20, 35, isSwitchOn);
                            sharedViewModel.getInstance().addDevice(androidData);
                         // Notify the adapter that the data set has changed
  // Initialize the adapter if it is null
                            if (adapter == null) {
                                // Clear the existing list
                                sharedViewModel.getInstance().getDeviceList().clear();

                                // Add the new data
                                sharedViewModel.getInstance().getDeviceList().addAll(dataList);

                                // Add the new device
                                sharedViewModel.getInstance().addDevice(androidData);

                                // Initialize the adapter
                                adapter = new DeviceAdapter(getActivity(), sharedViewModel.getDeviceList(), toolbarTitle, userEmail);

                                // Set the adapter to the RecyclerView
                                recyclerView.setAdapter(adapter);
                                loadDataFromFirebaseData();
                                // Notify the adapter that the data set has changed
                                //adapter.notifyDataSetChanged();
                            }

                           adapter.notifyDataSetChanged();


                            // Save the updated data to Firebase
                           firebaseDatabaseHelper.saveRecyclerViewDataDevice(encodedEmail, toolbarTitle, sharedViewModel.getInstance().getDeviceList());

                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.rounded_corner);

            alertDialog.show();
        }

        public void loadDataFromFirebaseData() {
            firebaseDatabaseHelper.getRecyclerViewDataDevice(encodedEmail,toolbarTitle, new FirebaseDatabaseHelper.OnDataLoadedListenerDevice<DataDevice>() {
                @Override
                public void onDataLoaded(List<DataDevice> dataList) {
                    // Update the UI here, e.g., update the adapter
                    if (dataList != null) {
                        // Initialize the adapter if it is null
                        // Update the existing adapter with the new data
                            sharedViewModel.getInstance().setDeviceList(dataList);
                            adapter = new DeviceAdapter(getActivity() ,dataList, toolbarTitle, userEmail);

                            recyclerView.setAdapter(adapter);

                            // Notify the adapter that the data set has changed
                            adapter.notifyDataSetChanged();

                    }
                }

                @Override
                public void onCancelled(String errorMessage) {
                    // Handle errors
                    Log.e("FirebaseDatabaseHelper", "Data loading cancelled: " + errorMessage);
                }
            });
        }
    }