package com.mycompany.androidproject11_service.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mycompany.androidproject11_service.R;
import com.mycompany.androidproject11_service.service.BackgroundService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleBtnStartService(View v){
        Intent intent = new Intent(this, BackgroundService.class);
        startService(intent);
    }

    public void handleBtnStopService(View v){
        Intent intent = new Intent(this, BackgroundService.class);
        stopService(intent);
    }
}
