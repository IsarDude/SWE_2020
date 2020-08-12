package com.example.swe_2020_next;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginUser(View view) {
        // code für den Login
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    public void createNewUser(View view) {
        //Code für neuen Nutzer
    }

    public void resetPassword(View view) {
        //Code für Passwort vergessen
    }
}
