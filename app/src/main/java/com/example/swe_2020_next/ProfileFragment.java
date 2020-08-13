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

import controller.ProfileController;


public class ProfileFragment extends Fragment{
    private Activity activity;
    private ProfileController profileController;
    private TextView firstNameTV;
    private TextView ageTV;
    private TextView genderTV;
    private TextView infoTextTV;
    private TextView hobbyTV;
    private TextView emailTV;
    private TextView visibilityTV;

    public ProfileFragment(Activity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        profileController = new ProfileController();
        Button btn1 = (Button) view.findViewById(R.id.editProfile);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, EditProfileActivity.class);
                activity.startActivity(intent);
                activity.finish();

            }
        });

        firstNameTV = (TextView) view.findViewById(R.id.user_firstname);
        ageTV = (TextView) view.findViewById(R.id.user_age);
        genderTV = (TextView) view.findViewById(R.id.user_gender);
        infoTextTV = (TextView) view.findViewById(R.id.user_infoText);
        hobbyTV = (TextView) view.findViewById(R.id.user_hobby);
        emailTV = (TextView) view.findViewById(R.id.user_email);
        visibilityTV = (TextView) view.findViewById(R.id.user_visible);

        updateViewData();
    }

    //Aktualisiert die Angezeigten Profilinformationen
    public void updateViewData() {
        firstNameTV.setText(profileController.getFirstName());
        ageTV.setText(profileController.getAge());
        genderTV.setText(profileController.getGender());
        infoTextTV.setText(profileController.getInfoText());
        hobbyTV.setText(profileController.getHobby());
        emailTV.setText(profileController.getEmail());

        if(profileController.getVisibility()) {
            visibilityTV.setText("Profil ist sichtbar");
        } else {
            visibilityTV.setText("Profil ist unsichtbar");
        }
    }
}
