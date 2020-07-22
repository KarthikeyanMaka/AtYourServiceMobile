package com.example.atyourservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class HospitalGridAdapter extends BaseAdapter{
    Context c;
    ArrayList<CityCovidHospDet> Gridlist;

    public HospitalGridAdapter(Context c, ArrayList<CityCovidHospDet> pGridlst) {
        this.c = c;
        this.Gridlist = pGridlst;
    }

    @Override
    public int getCount() {
        return Gridlist.size();
    }

    @Override
    public Object getItem(int i) {
        return Gridlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.grid_adapter,viewGroup,false);
        }

        TextView hosName= (TextView) view.findViewById(R.id.txthosName);
        TextView hosCity= (TextView) view.findViewById(R.id.txthosCity);
        TextView hosOwner= (TextView) view.findViewById(R.id.txthosOwner);
        TextView hosBeds= (TextView) view.findViewById(R.id.txthosBeds);


        CityCovidHospDet lstHosp= (CityCovidHospDet) this.getItem(i);

        hosName.setText(lstHosp.name);
        hosCity.setText(lstHosp.city);
        hosOwner.setText(lstHosp.ownership);
        hosBeds.setText(lstHosp.hospitalBeds);

        return view;
    }
}
