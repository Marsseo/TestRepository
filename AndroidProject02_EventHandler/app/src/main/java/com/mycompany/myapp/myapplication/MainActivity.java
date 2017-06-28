package com.mycompany.myapp.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private ImageView imgTitle;
    private Button btnImg1, btnImg2;
    private RadioButton rbImg1, rbImg2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgTitle = (ImageView)findViewById(R.id.imgTitle);
        btnImg1 = (Button) findViewById(R.id.btnImg1);
        btnImg2 = (Button) findViewById(R.id.btnImg2);
        rbImg1 = (RadioButton) findViewById(R.id.rBtnImg1);
        rbImg2 = (RadioButton) findViewById(R.id.rBtnImg2);


        btnImg1.setOnClickListener(handleBtnImg);
        btnImg2.setOnClickListener(handleBtnImg);
        rbImg1.setOnClickListener(handleBtnImg);
        rbImg2.setOnClickListener(handleBtnImg);
    }

    private View.OnClickListener handleBtnImg = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v == btnImg1 || v == rbImg1) imgTitle.setImageResource(R.drawable.photo1);
            else if(v == btnImg2 || v == rbImg2) imgTitle.setImageResource(R.drawable.photo2);

        }
    };

      // 각각 핸들러를 만든 경우
//    private View.OnClickListener handleBtnImg2 = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            imgTitle.setImageResource(R.drawable.photo2);
//        }
//    };

    public void handleBtnImg3(View v){
        imgTitle.setImageResource(R.drawable.photo3);
    }

    public void handleRbImg3(View v){
        imgTitle.setImageResource(R.drawable.photo3);
    }
}
