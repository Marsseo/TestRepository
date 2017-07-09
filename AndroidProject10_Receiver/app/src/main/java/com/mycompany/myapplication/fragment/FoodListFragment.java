package com.mycompany.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.dto.FoodItem;

import java.util.ArrayList;
import java.util.List;


public class FoodListFragment extends Fragment {

    private static final String TAG= FoodListFragment.class.getSimpleName();
    private ListView listView;
    private List<FoodItem> list = new ArrayList<>();
    private ItemAdapter itemAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        listView = (ListView) inflater.inflate(R.layout.food_list, null);

        itemAdapter = new ItemAdapter();
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(itemClickListener);
        return listView;
    }
    public AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            FoodItem review = (FoodItem) itemAdapter.getItem(position);
            Log.i(TAG, review.getFname());
            Log.i(TAG, review.getFdesc());
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
            return list.get(position).getFno();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                // Item UI 객체 생성(Inflation)
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.food_item, null);
            }

            // 데이터 세팅
            ImageView fphoto = (ImageView) convertView.findViewById(R.id.photo);
            TextView ftitle = (TextView) convertView.findViewById(R.id.title);
            ImageView fstar = (ImageView) convertView.findViewById(R.id.star);
            TextView fcontent = (TextView) convertView.findViewById(R.id.content);
            FoodItem item = list.get(position);
            fphoto.setImageResource(item.getFphoto());
            ftitle.setText(item.getFname());
            fstar.setImageResource(item.getFstar());
            fcontent.setText(item.getFdesc());
            return convertView;
        }
    }
    public void addItem(FoodItem item){
        list.add(item);
        itemAdapter.notifyDataSetChanged();
    }
    public void removeItem(FoodItem item){
        list.remove(item);
        itemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        for(int i=1;i<6;i++) {
            FoodItem item1 = new FoodItem();
            item1.setFphoto(getResources().getIdentifier("food"+i, "drawable", getContext().getPackageName()));
            item1.setFname("왜이러고 사는지..."+i);
            item1.setFstar(getResources().getIdentifier("star"+(int)(Math.random()*9+1), "drawable", getContext().getPackageName()));
            item1.setFdesc("진짜 미친듯이 졸립다... 집가서 자고 싶어"+i);
            addItem(item1);
        }
    }
}
