package com.example.swe_2020_next;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import datingDatabase.ConnectMySql;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ConnectMySql connection = new ConnectMySql();
        connection.execute("");
    }
}

