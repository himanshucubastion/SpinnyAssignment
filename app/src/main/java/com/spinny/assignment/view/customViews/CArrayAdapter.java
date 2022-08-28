package com.spinny.assignment.view.customViews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.spinny.assignment.R;

import java.util.List;

public class CArrayAdapter<T> extends ArrayAdapter<T> {

    private final int groupId;
    private final List<T> list;
    private final LayoutInflater inflater;
    private boolean enableHeader = false;

    public CArrayAdapter(Context context, int groupId, int id, List<T> list,boolean enableHeader) {
        super(context,id,list);
        this.list=list;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupId=groupId;
        this.enableHeader=enableHeader;
    }

    public CArrayAdapter(Context context, int groupId, int id, List<T> list) {
        super(context,id,list);
        this.list=list;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupId=groupId;
    }

    @Override
    public boolean isEnabled(int position){
        if (position!=0) return true;
        else return enableHeader;
    }

    @SuppressLint("ViewHolder")
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView=inflater.inflate(groupId,parent,false);
        TextView textView= itemView.findViewById(R.id.customTextView);
        textView.setText(list.get(position).toString());
        return itemView;
    }

    public View getDropDownView(int position, View convertView,@NonNull ViewGroup parent){
        View view = getView(position, convertView, parent);
        TextView tv = view.findViewById(R.id.customTextView);
        if(position == 0 && !enableHeader){
            tv.setTextColor(Color.GRAY);
        }
        else {
            tv.setTextColor(Color.BLACK);
        }
        return view;
    }
}
