package com.example.cm2019pf.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cm2019pf.R;
import com.example.cm2019pf.model.hospitalTimes;
import com.example.cm2019pf.model.statusHospital;

import java.util.ArrayList;
import java.util.List;

public class hospitalgetTimeandTypeAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<statusHospital>elementosStatus;


    public hospitalgetTimeandTypeAdapter(Context context, ArrayList<statusHospital> elementosStatus) {
        this.context = context;
        this.elementosStatus = elementosStatus;
    }

    @Override
    public int getCount() {
        return elementosStatus.size();
    }

    @Override
    public Object getItem(int id) {
        return elementosStatus.get(id);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        @SuppressLint("ViewHolder")
        View rowview = inflater.inflate(R.layout.layout_status,parent,false);

        TextView tempo = (TextView)rowview.findViewById(R.id.tempoespera);
        TextView scaletype = (TextView)rowview.findViewById(R.id.scaletype);


        tempo.setText(elementosStatus.get(position).getLastUpdate());
        scaletype.setText(elementosStatus.get(position).getScaleType().toString());


        return rowview;
    }
}
