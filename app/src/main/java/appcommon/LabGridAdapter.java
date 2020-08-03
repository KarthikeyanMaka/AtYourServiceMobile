package appcommon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.atyourservice.R;

import java.util.ArrayList;

public class LabGridAdapter extends BaseAdapter {

    Context c;
    ArrayList<CovidLabModel> covidLabModels;

    public LabGridAdapter(Context pc, ArrayList<CovidLabModel> pcovidlab){
        this.c=pc;
        this.covidLabModels=pcovidlab;
    }

    @Override
    public int getCount() {
        return covidLabModels.size();
    }

    @Override
    public Object getItem(int i) {
        return covidLabModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.activity_lab_grid,viewGroup,false);
        }
        TextView labname= (TextView) view.findViewById(R.id.grdlabname);
        TextView labowner= (TextView) view.findViewById(R.id.grdlabowner);
        TextView labcontact= (TextView) view.findViewById(R.id.grdlabcontact);


        CovidLabModel labModel = (CovidLabModel) this.getItem(i);

        labname.setText(labModel.nameoftheorganisation);
        labowner.setText(labModel.descriptionandorserviceprovided);
        labcontact.setText(labModel.phonenumber);

        return view;
    }
}
