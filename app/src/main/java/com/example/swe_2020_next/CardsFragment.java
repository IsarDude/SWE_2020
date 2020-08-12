package com.example.swe_2020_next;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CardsFragment extends Fragment {

    /* Code falls Activity benötigt wird (Bei szenenwechsel nötig)
    private Activity activity;

    public CardsFragment(Activity activity) {
        this.activity = activity;
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_cards, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button btn1 = (Button) view.findViewById(R.id.button_like);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Code für Like

            }
        });

        Button btn2 = (Button) view.findViewById(R.id.button_dislike);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Code für Dislike

            }
        });
    }
}
