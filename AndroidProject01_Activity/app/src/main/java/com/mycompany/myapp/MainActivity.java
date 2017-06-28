package com.mycompany.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(Utils.getTag(),"실행");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        Log.d(Utils.getTag(),"실행");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d(Utils.getTag(),"실행");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.d(Utils.getTag(),"실행");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(Utils.getTag(),"실행");
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(Utils.getTag(),"실행");
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(Utils.getTag(),"실행");
        if(item.getItemId()==R.id.action_full_activity){
            Intent intent = new Intent(this, FullActivity.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.action_dialog_activity){
            Intent intent = new Intent(this, DialogActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    //////////////////////////////////////////////////////////////////////
    @Override
    protected void onStop() {
        Log.d(Utils.getTag(),"실행");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(Utils.getTag(),"실행");
        super.onDestroy();
    }
}
