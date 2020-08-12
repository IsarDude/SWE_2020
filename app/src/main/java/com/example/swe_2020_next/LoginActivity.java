package com.example.swe_2020_next;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import controller.LoginController;

public class LoginActivity extends Activity {
    EditText editUsername;
    EditText editPassword;
    LoginController loginController = new LoginController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUsername = (EditText) findViewById(R.id.login_usernameInput);
        editPassword = (EditText) findViewById(R.id.login_passwordInput);
    }

    public void loginUser(View view) {
        if(loginController.loginControl(editUsername.getText().toString(),editPassword.getText().toString())){
            Toast.makeText(this, "Login erfolgreich!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }else{
            Toast.makeText(this, "Login fehlgeschlagen!", Toast.LENGTH_LONG).show();
        }
    }

    public void createNewUser(View view) {
        startActivity(new Intent(LoginActivity.this, NewProfilActivity.class));
    }

    public void resetPassword(View view) {
        Toast.makeText(this, "Email zum Zurücksetzen des Passworts wurde Versand!", Toast.LENGTH_LONG).show();
    }
}
