package com.example.swe_2020_next;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

    }


    public void saveChanges(View view) {
        //Code für die Sicherung der neuen Einträge beim Profil bearbeiten
    }
}
