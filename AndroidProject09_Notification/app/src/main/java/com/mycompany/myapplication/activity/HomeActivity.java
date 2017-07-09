package com.mycompany.myapplication.activity;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.mycompany.myapplication.R;

import java.util.Set;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HOME ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        int permissionCall = ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        int permissionRD = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionWE = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(permissionCall != PackageManager.PERMISSION_GRANTED ||
                permissionRD != PackageManager.PERMISSION_GRANTED ||
                permissionWE != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                    new String[] {  Manifest.permission.CALL_PHONE,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }else{

        }
    }

    public void handleBtnUIActivity(View v){
        Intent intent = new Intent(this, UIActivity.class);
        startActivity(intent);
    }

    public void handleBtnWebBrower(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
        startActivity(intent);
    }



    public void handleFileSelect(View v){

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent.createChooser(intent, "이미지 선택"), 2);

    }


    public void handleBtnMapActivity(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:10.515889,120.07275?z=16"));
        startActivity(intent);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                int result = data.getIntExtra("result", 0);
                Log.i(TAG, "전달 : "+String.valueOf(result));
            }else{
                Log.i(TAG, "전달 실패");
            }

        }else if(requestCode==2){
            if(resultCode==RESULT_OK){
                Uri uri = data.getData();
                Log.i(TAG, String.valueOf(uri));
                String path = getRealPath(uri);
                Log.i(TAG, path);
            }

        }

    }

    public String getRealPath(Uri uri) {
        String realPath = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
        if(cursor.moveToFirst()){;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            realPath = cursor.getString(column_index);
        }
        cursor.close();
        return realPath;
    }

    public void handleBtnNotification(View v){

        Intent intent = new Intent(this, UIActivity.class);
        //Back 버튼 입력시 마지막 화면이 나옴
        //PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //Back 버튼 입력시 UIActivity 화면의 이전화면 (즉 HomeActivity 부모)이 나오도록 함
        PendingIntent pendingIntent = TaskStackBuilder.create(this)
                .addParentStack(UIActivity.class) // Manifest 파일에서 이전 화면에 정보를 얻어 스택에 넣음
                .addNextIntent(intent)
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("MQTT 알림")
                .setContentText("온도가 비정상적으로 높습니다.")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 300, 1000, 300}) // 진동이 2번 1초간 0.5초이면
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .build();

        NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1, notification);
        nm.notify(1, notification);
    }
}
