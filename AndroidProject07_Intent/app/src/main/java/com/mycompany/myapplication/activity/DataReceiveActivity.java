package com.mycompany.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.dto.Review;

import java.util.Set;

public class DataReceiveActivity extends AppCompatActivity {

    private static final String TAG = "DATA RECEIVE ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_receive);

        Intent intent = getIntent();

        int key1 = intent.getIntExtra("key1",0);
        String key2 = intent.getStringExtra("key2");
        Review key3 = (Review)intent.getSerializableExtra("key3");
        Set<String> a = intent.getCategories();
        String ab = a.toString();

        Log.i(TAG, ""+key1);
        Log.i(TAG, key2);
        Log.i(TAG, key3.getTitle());

    }
}
