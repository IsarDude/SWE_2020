package com.example.swe_2020_next;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MatchesFragment extends Fragment {

    ListView listView;

    String[] ListviewTitle = new String[]{
            "Kathrin","Friederike","Elaine","Denise","Hanna"
    };
    String[] ListviewDescription = new String[]{
            "25","19","22","20",
            "27"
    };
    // images
    int[] ListviewImages = new int[]{
            R.drawable.ic_profile,R.drawable.ic_profile,R.drawable.ic_profile,R.drawable.ic_profile,R.drawable.ic_profile
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_matches, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        listView = (ListView) view.findViewById(R.id.matches_ListView);

        List<HashMap<String,String>> aList = new ArrayList<HashMap<String, String>>();
        for (int x = 0; x < ListviewTitle.length; x++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("ListTitle",ListviewTitle[x]);
            hm.put("ListDescription",ListviewDescription[x]);
            hm.put("ListImages",Integer.toString(ListviewImages[x]));
            aList.add(hm);
        }

        String[] from = {
                "ListImages","ListTitle","ListDescription"
        };
        int[] to = {
                R.id.listview_images,R.id.listview_name,R.id.listview_age
        };

        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),aList, R.layout.listview_items,from,to);
        ListView simpleListview = (ListView) Objects.requireNonNull(getView()).findViewById(R.id.matches_ListView);
        simpleListview.setAdapter(simpleAdapter);
    }
}
