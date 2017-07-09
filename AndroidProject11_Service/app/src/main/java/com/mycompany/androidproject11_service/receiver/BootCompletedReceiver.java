package com.mycompany.androidproject11_service.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mycompany.androidproject11_service.service.BackgroundService;

public class BootCompletedReceiver extends BroadcastReceiver {

    private static final String TAG = "BootCompletedReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(TAG, "실행 확인");
        Intent i = new Intent(context, BackgroundService.class);
        context.startService(intent);
    }
}
