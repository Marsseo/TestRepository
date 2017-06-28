package com.mycompany.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout rootlinearLayout, linearLayoutTop, content1, content2, content3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayoutTop = (LinearLayout) findViewById(R.id.linearLayoutTop);
        rootlinearLayout = (LinearLayout) findViewById(R.id.rootLinearLayout);
        content1 = (LinearLayout) findViewById(R.id.linear1);
        content2 = (LinearLayout) findViewById(R.id.linear2);
        content3 = (LinearLayout) findViewById(R.id.linear3);
    }

    public void handleRbImg(View v){

        if(v==linearLayoutTop.findViewById(R.id.radioButton1)) linearLayoutTop.setBackgroundResource(R.drawable.photo1);
        else if(v==linearLayoutTop.findViewById(R.id.radioButton2)) linearLayoutTop.setBackgroundResource(R.drawable.photo2);
        else if(v==linearLayoutTop.findViewById(R.id.radioButton3)) linearLayoutTop.setBackgroundResource(R.drawable.photo3);
    }

    public void handleBtncontent(View v){
        if(v==rootlinearLayout.findViewById(R.id.button1)){
            content1.setVisibility(View.VISIBLE);
            content2.setVisibility(View.INVISIBLE);
            content3.setVisibility(View.INVISIBLE);
        }else if(v==rootlinearLayout.findViewById(R.id.button2)){
            content1.setVisibility(View.INVISIBLE);
            content2.setVisibility(View.VISIBLE);
            content3.setVisibility(View.INVISIBLE);
        }else if(v==rootlinearLayout.findViewById(R.id.button3)){
            content1.setVisibility(View.INVISIBLE);
            content2.setVisibility(View.INVISIBLE);
            content3.setVisibility(View.VISIBLE);
        }
    }

}
