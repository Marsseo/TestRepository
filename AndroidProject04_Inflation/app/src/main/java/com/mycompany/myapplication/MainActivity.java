package com.mycompany.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

        //방법1
//        LayoutInflater inflater = getLayoutInflater();
        //방법2
//        LayoutInflater inflater = LayoutInflater.from(this);

        //방법3
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //뷰 객체를 framelayout에 넣음 방법1
//        View view = inflater.inflate(R.layout.content1, null);
        //방법2
//        View view = inflater.inflate(R.layout.content1, frameLayout, false);
//        frameLayout.addView(view);
        //방법3 (true 가 디폴트이다)
        int layout;

        if(v == linearLayoutTop.findViewById(R.id.button1)){
            Content1 content1 = new Content1(this);
            frameLayout.addView(content1);

            for(int i=1;i<10;i++){
                Item item = new Item();
                item.setPhoto(getResources().getIdentifier("member"+i, "drawable", getPackageName()));
                item.setTitle("집가고 싶다... 정말"+i);
                item.setContent("진짜 좀 프로젝트할 시간 좀 주지... 너무하네");
                item.setStar(getResources().getIdentifier("star"+i, "drawable", getPackageName()));
                content1.addItem(item);
            }
        }
        else if(v == linearLayoutTop.findViewById(R.id.button2)){
            layout = R.layout.content2;
            inflater.inflate(layout, frameLayout);
        }
        else if(v == linearLayoutTop.findViewById(R.id.button3)){
            layout = R.layout.content3;
            inflater.inflate(layout, frameLayout);
        }

    }

}
