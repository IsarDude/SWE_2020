package com.example.swe_2020_next;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import controller.CardsController;


public class CardsFragment extends Fragment {
    private CardsController cardsController;
    private TextView otherUsernameTV;


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
        cardsController = new CardsController();
        otherUsernameTV = (TextView) view.findViewById(R.id.card_username);
        updateCard();

        Button btn1 = (Button) view.findViewById(R.id.button_like);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Code für Like
                cardsController.like();
                updateCard();
            }
        });

        Button btn2 = (Button) view.findViewById(R.id.button_dislike);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Code für Dislike
                cardsController.dislike();
                updateCard();
            }
        });
    }

    public void updateCard() {
        String otherUserNameAndAge = cardsController.getOtherUserNameAndAge();
        otherUsernameTV.setText(otherUserNameAndAge);
        //Hier könnte Code stehen um das Profilbild der neuen Karte in der View zu updaten
    }

}
