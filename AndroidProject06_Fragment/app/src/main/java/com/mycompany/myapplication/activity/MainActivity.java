package com.mycompany.myapplication.activity;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.fragment.FoodListFragment;
import com.mycompany.myapplication.fragment.MemberListFragment;
import com.mycompany.myapplication.fragment.ReviewListFragment;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayoutTop;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayoutTop = (LinearLayout) findViewById(R.id.linearLayoutTop);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

    }

    public void handleRbImg(View v){

        if(v==linearLayoutTop.findViewById(R.id.radioButton1)) linearLayoutTop.findViewById(R.id.imageView).setBackgroundResource(R.drawable.photo1);
        else if(v==linearLayoutTop.findViewById(R.id.radioButton2)) linearLayoutTop.findViewById(R.id.imageView).setBackgroundResource(R.drawable.photo2);
        else if(v==linearLayoutTop.findViewById(R.id.radioButton3)) linearLayoutTop.findViewById(R.id.imageView).setBackgroundResource(R.drawable.photo3);
    }

    public void handleBtncontent(View v){

        frameLayout.removeAllViews();

        if(v == linearLayoutTop.findViewById(R.id.button1)){

            ReviewListFragment rf = new ReviewListFragment();

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.frameLayout, rf);
            ft.commit();

        }
        else if(v == linearLayoutTop.findViewById(R.id.button2)){
            FoodListFragment ff = new FoodListFragment();

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.frameLayout, ff);
            ft.commit();

        }
        else if(v == linearLayoutTop.findViewById(R.id.button3)){
            MemberListFragment mf = new MemberListFragment();

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.frameLayout, mf);
            ft.commit();

        }

    }

}
