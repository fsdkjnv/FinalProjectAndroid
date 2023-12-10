package com.example.drawer.fragment.FragmentExplore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawer.Adapter.AdapterExplore.ExploreAdapter;
import com.example.drawer.Data.DataExplore.ExploreItem;
import com.example.drawer.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {
    // Đây là một lớp Fragment dành cho màn hình "Yêu thích" trong ứng dụng Android của bạn.

    private List<ExploreItem> exploreItemList;
    private RecyclerView recyclerView;
    private ExploreAdapter exploreAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Phương thức này được gọi để tạo giao diện cho fragment.

        // LayoutInflater được sử dụng để bố trí (render) giao diện của fragment.
        // "inflater.inflate(R.layout.fragment_favorite, container, false);" bố trí (render) giao diện từ tệp layout fragment_favorite.xml.

        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        exploreItemList = generateExploreItem();
        recyclerView = view.findViewById(R.id.recyclerViewExplore);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        exploreAdapter = new ExploreAdapter(getActivity(), exploreItemList);
        recyclerView.setAdapter(exploreAdapter);

        return view;
    }


    private List<ExploreItem> generateExploreItem()
    {
        List<ExploreItem> exploreItems = new ArrayList<>();
        exploreItems.add(new ExploreItem(R.drawable.flower_1,
                "Top 5 Gardening Trends for 2022",
                "According to the 2021 National Gardening Survey, 58% of 35-44 year-olds surveyed said they gardened more because of the pandemic. With the increased time most have spent at home, getting outdoors and in touch with the natural world by practicing mindfulness through growing has brought respite from the stress of the pandemic. Here are the top 5 gardening trends we expect to see in 2022.","Miss Vi"));
        exploreItems.add(new ExploreItem(R.drawable.flower_2, "What is Certified Organic?",
                "Per the USDA, in order to be Certified Organic, products must be meet certain criteria. They must be produced using agricultural production practices that:\n" +
                        "Foster resource cycling\n" +
                        "Promote ecological balance\n" +
                        "Maintain and improve soil and water quality\n" +
                        "Minimize the use of synthetic materials\n" +
                        "Conserve biodiversity",
                "Mr Jane"));
        exploreItems.add(new ExploreItem(R.drawable.flower_3, "Summer 2021 Photo Contest",
                "We’re lucky to have so many customers willing to share their gorgeous garden creations, whether flower, veggie or herb. They email us daily to show off what’s grown from their Eden Brothers’ seeds or bulbs. Knowing how talented our customers are and how photogenic our products can be, we decided to host a photo contest this summer." +
                        "Our marketing team launched the Summer 2021 Photo Contest at the end of July thinking we’d get a hundred or so entries",
                "Mr David"));

        return exploreItems;
    }
}
