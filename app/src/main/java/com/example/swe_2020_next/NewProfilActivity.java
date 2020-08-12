package com.example.swe_2020_next;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import controller.NewProfileController;
import model.Dater;

public class NewProfilActivity extends AppCompatActivity {
    EditText newEmail;
    EditText newPassword;
    EditText newFirstName;
    EditText newLastName;
    EditText newAge;
    EditText newGender;
    EditText newLanguage;
    EditText newInfoText;
    EditText newHobby;
    NewProfileController newProfileController = new NewProfileController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profil);

        newEmail = (EditText) findViewById(R.id.edit_new_email);
        newPassword = (EditText) findViewById(R.id.edit_new_passwort);
        newFirstName = (EditText) findViewById(R.id.edit_new_firstname);
        newLastName = (EditText) findViewById(R.id.edit_new_lastname);
        newAge = (EditText) findViewById(R.id.edit_new_age);
        newGender = (EditText) findViewById(R.id.edit_new_gender);
        newLanguage = (EditText) findViewById(R.id.edit_new_language);
        newInfoText = (EditText) findViewById(R.id.edit_new_infoText);
        newHobby = (EditText) findViewById(R.id.edit_new_hobby);

    }

    public void newProfile(View view) {
        if( newAge.getText().toString().length() == 0 )
            newAge.setError( "First name is required!" );

        newProfileController.newUserControl(
                newEmail.getText().toString(),
                newPassword.getText().toString(),
                newFirstName.getText().toString(),
                newLastName.getText().toString(),
                Integer.parseInt(newAge.getText().toString()),
                newGender.getText().toString(),
                newLanguage.getText().toString(),
                newInfoText.getText().toString(),
                newHobby.getText().toString()
        );
        Toast.makeText(this, "Neuer User wurde erstellt!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(NewProfilActivity.this, LoginActivity.class));
    }
}