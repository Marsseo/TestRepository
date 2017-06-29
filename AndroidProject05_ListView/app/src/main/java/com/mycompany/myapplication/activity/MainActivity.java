package com.mycompany.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.content.FoodList;
import com.mycompany.myapplication.content.MemberList;
import com.mycompany.myapplication.content.ReviewList;
import com.mycompany.myapplication.dto.FoodItem;
import com.mycompany.myapplication.dto.MemberItem;
import com.mycompany.myapplication.dto.ReviewItem;

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
            ReviewList reviewList = new ReviewList(this);
            frameLayout.addView(reviewList);
            for(int i=1;i<10;i++) {
                ReviewItem item = new ReviewItem();
                item.setPhoto(R.drawable.member1);
                item.setTitle("왜이러고 사는지...");
                item.setStar(R.drawable.star8);
                item.setContent("진짜 미친듯이 졸립다... 집가서 자고 싶어");
                reviewList.addItem(item);
            }
        }
        else if(v == linearLayoutTop.findViewById(R.id.button2)){
            FoodList reviewList = new FoodList(this);
            frameLayout.addView(reviewList);
            for(int i=1;i<6;i++) {
                FoodItem item1 = new FoodItem();
                item1.setFphoto(getResources().getIdentifier("food"+i, "drawable", getPackageName()));
                item1.setFname("왜이러고 사는지..."+i);
                item1.setFstar(getResources().getIdentifier("star"+i, "drawable", getPackageName()));
                item1.setFdesc("진짜 미친듯이 졸립다... 집가서 자고 싶어"+i);
                reviewList.addItem(item1);
            }
        }
        else if(v == linearLayoutTop.findViewById(R.id.button3)){
            MemberList memberList = new MemberList(this);
            frameLayout.addView(memberList);
            for(int i=1;i<101;i++) {
                MemberItem item1 = new MemberItem();
                item1.setMphoto(getResources().getIdentifier("abc"+i, "drawable", getPackageName()));
                item1.setMname("인물"+i);
                item1.setMstar(getResources().getIdentifier("star"+(int)(Math.random()*10), "drawable", getPackageName()));
                item1.setMcomment("자 평가 들어 갑니다."+i);
                memberList.addItem(item1);
            }
        }

    }

}
