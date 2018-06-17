package com.hm_master.masterapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.security.KeyStore;
import java.util.List;

public class CustomListAdapter extends ArrayAdapter<Room> {

    static class ViewHolder{
        TextView textView_freeQ;
        TextView textView_RoomName;
        TextView textView_Duration;
        TextView textView_Time;
        int position;
    }

    List<Room> myEntries;
    public CustomListAdapter(@NonNull Context context, @NonNull List<Room> objects) {
        super(context, R.layout.layout_lstentry, objects);
        myEntries = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public Room getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_lstentry,parent, false);

            holder = new ViewHolder();
            holder.position = position;
            holder.textView_Duration = (TextView) convertView.findViewById(R.id.entry_Duration);
            holder.textView_RoomName = (TextView) convertView.findViewById(R.id.entry_Room);
            holder.textView_Time = (TextView) convertView.findViewById(R.id.entry_Time);
            holder.textView_freeQ = (TextView) convertView.findViewById(R.id.entry_When);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Room entry = getItem(position);

        holder.textView_freeQ.setText(entry.getStatus());
        holder.textView_RoomName.setText(entry.getName());
        holder.textView_Duration.setText(entry.getDuration());
        holder.textView_Time.setText(entry.getTime());

        return convertView;
    }
}

