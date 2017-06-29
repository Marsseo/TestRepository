package com.mycompany.myapplication.content;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.dto.ReviewItem;

import java.util.ArrayList;
import java.util.List;

public class ReviewList extends LinearLayout{


    private static final String TAG= ReviewList.class.getSimpleName();
    private ListView listView;
    private List<ReviewItem> list = new ArrayList<>();
    private ItemAdapter itemAdapter;

    public ReviewList(Context context) {
        super(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        listView = (ListView)inflater.inflate(R.layout.review_list, null);

        itemAdapter = new ItemAdapter();
        listView.setAdapter(itemAdapter);
        this.addView(listView);

        listView.setOnItemClickListener(itemClickListener);
    }
    public AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ReviewItem review = (ReviewItem) itemAdapter.getItem(position);
            Log.i(TAG, review.getTitle());
            Log.i(TAG, review.getContent());
        }
    };

    class ItemAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                // Item UI 객체 생성(Inflation)
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.review_item, null);
            }

            // 데이터 세팅
            ImageView photo = (ImageView) convertView.findViewById(R.id.photo);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            ImageView star = (ImageView) convertView.findViewById(R.id.star);
            TextView content = (TextView) convertView.findViewById(R.id.content);
            ReviewItem item = list.get(position);
            photo.setImageResource(item.getPhoto());
            title.setText(item.getTitle());
            star.setImageResource(item.getStar());
            content.setText(item.getContent());
            return convertView;
        }
    }
    public void addItem(ReviewItem item){
        list.add(item);
        itemAdapter.notifyDataSetChanged();
    }
    public void removeItem(ReviewItem item){
        list.remove(item);
        itemAdapter.notifyDataSetChanged();
    }

}
