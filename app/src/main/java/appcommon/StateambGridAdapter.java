package appcommon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.atyourservice.R;

import java.util.ArrayList;

public class StateambGridAdapter extends BaseAdapter {
    Context c;
    ArrayList<StateAmbulanceModel> helplinelists;

    public StateambGridAdapter(Context pc, ArrayList<StateAmbulanceModel> phelpline){
        this.c=pc;
        this.helplinelists=phelpline;
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
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.activity_amb_grid,viewGroup,false);
        }

        TextView StateName= (TextView) view.findViewById(R.id.grdambstatename);
        TextView AmbContact= (TextView) view.findViewById(R.id.grdambcontact);


        StateAmbulanceModel lstHelp= (StateAmbulanceModel) this.getItem(i);

        AmbContact.setText(lstHelp.ambulanceContact);
        StateName.setText(lstHelp.name);

        return view;
    }
}
