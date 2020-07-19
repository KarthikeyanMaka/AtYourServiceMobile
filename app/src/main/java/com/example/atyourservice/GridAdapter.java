package com.example.atyourservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.atyourservice.StateHelplinelist;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter{

    Context c;
    ArrayList<StateHelplinelist> helplinelists;
    boolean isCentral;

    public GridAdapter(Context c, ArrayList<StateHelplinelist> phelplinelists, boolean pisCentral) {
        this.c = c;
        this.helplinelists = phelplinelists;
        this.isCentral=pisCentral;
    }

    @Override
    public int getCount() {
        return helplinelists.size();
    }

    @Override
    public Object getItem(int i) {
        return helplinelists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view=LayoutInflater.from(c).inflate(R.layout.grid_adapter,viewGroup,false);
        }

        TextView helplineNum= (TextView) view.findViewById(R.id.txthelpPhon);
        TextView helplineEmail= (TextView) view.findViewById(R.id.txthelpemailId);
        TextView helpTollFree= (TextView) view.findViewById(R.id.txtTollfree);

        if(isCentral)
        {
            helplineEmail.setVisibility(View.INVISIBLE);
        }

        StateHelplinelist lstHelp= (StateHelplinelist) this.getItem(i);

        helplineNum.setText(lstHelp.helpline_number);
        helplineEmail.setText(lstHelp.helpline_email);
        helpTollFree.setText(lstHelp.toll_free);

        return view;
    }
}