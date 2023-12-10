package com.example.drawer.fragment.FragmentExplore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drawer.Data.DataHome.DataDevice;
import com.example.drawer.R;


public class DetailFragment extends Fragment {

    ImageView imageView;
    TextView textViewTitle;
    TextView textViewContent;
    TextView textViewAuthor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);//Dòng này tạo ra View từ activity_detail.xml và gán cho rootView.

        imageView = rootView.findViewById(R.id.recImagePlant);
        textViewTitle = rootView.findViewById(R.id.recTitleExplore);
        textViewContent = rootView.findViewById(R.id.recContent);
        textViewAuthor = rootView.findViewById(R.id.recAuthor);
        Bundle bundle = getArguments();
        if (bundle != null) {
            imageView.setImageResource(bundle.getInt("Image"));
            textViewTitle.setText(bundle.getString("Title"));
            textViewContent.setText(bundle.getString("Content"));
            textViewAuthor.setText(bundle.getString("Author"));
        }
//        DataDevice dataDevice
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_detail, container, false);
        return rootView;

    }
}