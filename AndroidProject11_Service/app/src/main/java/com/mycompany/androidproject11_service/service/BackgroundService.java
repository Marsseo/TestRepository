package com.mycompany.androidproject11_service.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class BackgroundService extends Service {
    private static final String TAG = "BackgroundService";

    Thread thread;
    @Override
    public IBinder onBind(Intent intent) {
        // 백그라운드에서는 사용 X
        throw new UnsupportedOperationException("Not yet implemented");
    }
    //최초로 startService()를 호출할 때 실행
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "크리에이트 됨");
    }
    //startService() 를 호출할 때마다 실행
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "스타뚜커맨드");

        if(thread == null || thread.isAlive()){
            thread = new Thread(){
                private int count=0;
                @Override
                public void run() {
                    while(true) {
                        Log.i(TAG, "카운터 : " + count++);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            break;
                        }

                    }
                    stopSelf();
                }
            };
            thread.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }
    //작업관리자에서 앱을 종료시켰을 때 실행
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "부셔버리게따 디스트로이");
        if(thread == null || thread.isAlive()){
            thread.interrupt();
            thread = null;
        }
    }
}
