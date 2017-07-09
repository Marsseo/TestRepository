package com.mycompany.myapplication.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.dto.Review;

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

    public void handleBtnDialActivity(View v){
        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-4567-1234"));
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if(permission == PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-4567-1234"));
            startActivity(intent);
        }

    }

    public void handleFileSelect(View v){

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent.createChooser(intent, "이미지 선택"), 2);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(grantResults.length >= 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED){

                Toast.makeText(this,"권한얻음", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"권한을 얻지 못함", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void handleBtnMapActivity(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:10.515889,120.07275?z=16"));
        startActivity(intent);
    }

    public void handleBtnDataSend(View v){

        Intent intent = new Intent(this, DataReceiveActivity.class);
        intent.putExtra("key1",10);
        intent.putExtra("key2","안드로이드");
        Review review = new Review();
        review.setTitle("아이고");
        intent.putExtra("key3", review);
        startActivity(intent);
    }

    public void handleBtnReturnValue(View v){
        Intent intent = new Intent(this, ReturnValueActivity.class);
        intent.putExtra("x", 30);
        intent.putExtra("y", 40);
        startActivityForResult(intent, 1);
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
}
