package com.example.swe_2020_next;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import controller.EditProfileController;


public class EditProfileActivity extends AppCompatActivity {
    EditProfileController editProfileController;

    private EditText firstNameET;
    private EditText lastNameET;
    private EditText ageET;
    private EditText genderET;
    private EditText languageET;
    private EditText infoTextET;
    private EditText hobbyET;
    private EditText emailET;
    private boolean userVisibility;
    private RadioButton onRB;
    private RadioButton offRB;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        editProfileController = new EditProfileController();

        firstNameET = (EditText) findViewById(R.id.edit_firstname);
        lastNameET = (EditText) findViewById(R.id.edit_lastname);
        ageET = (EditText) findViewById(R.id.edit_age);
        genderET = (EditText) findViewById(R.id.edit_gender);
        languageET = (EditText) findViewById(R.id.edit_language);
        infoTextET = (EditText) findViewById(R.id.edit_infoText);
        hobbyET = (EditText) findViewById(R.id.edit_hobby);
        emailET = (EditText) findViewById(R.id.edit_email);
        onRB = (RadioButton) findViewById(R.id.edit_visibilityOn);
        offRB = (RadioButton) findViewById(R.id.edit_visibilityOff);

        updateViewData();
    }

    public void saveChanges(View view) {
        String newFirstName = firstNameET.getText().toString();
        String newLastName = lastNameET.getText().toString();
        String newAge = ageET.getText().toString();
        String newGender = genderET.getText().toString();
        String newLanguage = languageET.getText().toString();
        String newInfoText = infoTextET.getText().toString();
        String newHobby = hobbyET.getText().toString();
        String newEmail = emailET.getText().toString();

        editProfileController.saveProfileChanges(newFirstName, newLastName, newAge, newGender, newLanguage, newInfoText, newHobby, newEmail, userVisibility);
    }

    public void getVisibilitySelection(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.edit_visibilityOn:
                if (checked)
                    userVisibility = true;
                    onRB.setChecked(true);
                    offRB.setChecked(false);
                    break;
            case R.id.edit_visibilityOff:
                if (checked)
                    userVisibility = false;
                    offRB.setChecked(true);
                    onRB.setChecked(false);
                    break;
        }
    }

    //Aktualisiert die Angezeigten Profilinformationen
    public void updateViewData() {
        firstNameET.setText(editProfileController.getFirstName());
        lastNameET.setText(editProfileController.getLastName());
        ageET.setText(editProfileController.getAge());
        genderET.setText(editProfileController.getGender());
        languageET.setText(editProfileController.getLanguage());
        infoTextET.setText(editProfileController.getInfoText());
        hobbyET.setText(editProfileController.getHobby());
        emailET.setText(editProfileController.getEmail());

        if(editProfileController.getVisibility()) {
            onRB.setChecked(true);
            offRB.setChecked(false);
        } else {
            offRB.setChecked(true);
            onRB.setChecked(false);
        }
    }
}
