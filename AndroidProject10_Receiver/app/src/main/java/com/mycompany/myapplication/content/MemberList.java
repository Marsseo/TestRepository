package com.mycompany.myapplication.content;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.dto.MemberItem;

import java.util.ArrayList;
import java.util.List;

public class MemberList extends LinearLayout{


    private ListView listView;
    private List<MemberItem> list = new ArrayList<>();
    private ItemAdapter itemAdapter;

    public MemberList(Context context) {
        super(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        listView = (ListView)inflater.inflate(R.layout.member_list, null);

        itemAdapter = new ItemAdapter();
        listView.setAdapter(itemAdapter);
        this.addView(listView);
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

}
