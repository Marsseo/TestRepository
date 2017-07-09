package com.mycompany.myapplication.fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.dto.MemberItem;

import java.util.ArrayList;
import java.util.List;


public class MemberListFragment extends Fragment {


    private ListView listView;
    private List<MemberItem> list = new ArrayList<>();
    private ItemAdapter itemAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        listView = (ListView)inflater.inflate(R.layout.fragment_member_list, null);

        itemAdapter = new ItemAdapter();
        listView.setAdapter(itemAdapter);

        return listView;
    }

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
                convertView = inflater.inflate(R.layout.member_item, null);
            }

            // 데이터 세팅
            ImageView photo = (ImageView) convertView.findViewById(R.id.photo);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            ImageView star = (ImageView) convertView.findViewById(R.id.star);
            TextView content = (TextView) convertView.findViewById(R.id.content);
            MemberItem item = list.get(position);
            photo.setImageResource(item.getMphoto());
            title.setText(item.getMname());
            star.setImageResource(item.getMstar());
            content.setText(item.getMcomment());
            return convertView;
        }
    }
    public void addItem(MemberItem item){
        list.add(item);
        itemAdapter.notifyDataSetChanged();
    }
    public void removeItem(MemberItem item){
        list.remove(item);
        itemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        for(int i=1;i<101;i++) {
            MemberItem item1 = new MemberItem();
            item1.setMphoto(getResources().getIdentifier("abc"+i, "drawable", getContext().getPackageName()));
            item1.setMname("인물"+i);
            item1.setMstar(getResources().getIdentifier("star"+(int)(Math.random()*10), "drawable", getContext().getPackageName()));
            item1.setMcomment("자 평가 들어 갑니다."+i);
            addItem(item1);
        }
    }
}
