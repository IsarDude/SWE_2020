package com.example.swe_2020_next;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.swe_2020_next.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import controller.CardsController;


public class CardsFragment extends Fragment {
    private CardsController cardsController;
    private TextView otherUsernameTV;
    private ImageView otherUserPictureIV;
    private int[] otherUserPictures = new int[10]; //For demo purposes only
    private int otherUserPicturesIndex = 0; //For demo purposes only


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
        otherUserPictureIV = (ImageView) view.findViewById(R.id.user_profile_picture);
        fillOtherUserPictures(); //For demo purposes only
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
        otherUserPictureIV.setImageResource(otherUserPictures[otherUserPicturesIndex]); //For demo purposes only
        manageResourceIndex(); //For demo purposes only
    }

    //For demo purposes only
    private void fillOtherUserPictures() {
        int imageResource0 = getResources().getIdentifier("@drawable/other_user_pic_0", "drawable", getActivity().getPackageName());
        int imageResource1 = getResources().getIdentifier("@drawable/other_user_pic_1", "drawable", getActivity().getPackageName());
        int imageResource2 = getResources().getIdentifier("@drawable/other_user_pic_2", "drawable", getActivity().getPackageName());
        int imageResource3 = getResources().getIdentifier("@drawable/other_user_pic_3", "drawable", getActivity().getPackageName());
        int imageResource4 = getResources().getIdentifier("@drawable/other_user_pic_4", "drawable", getActivity().getPackageName());
        otherUserPictures[0] = imageResource0;
        otherUserPictures[1] = imageResource1;
        otherUserPictures[2] = imageResource2;
        otherUserPictures[3] = imageResource3;
        otherUserPictures[4] = imageResource4;
    }

    //For demo purposes only
    private void manageResourceIndex() {
        if (otherUserPicturesIndex == 4) {
            otherUserPicturesIndex = 0;
        } else {
            otherUserPicturesIndex++;
        }
    }

}
