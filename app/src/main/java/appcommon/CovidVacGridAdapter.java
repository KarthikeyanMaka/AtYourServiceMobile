package appcommon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.atyourservice.R;

import java.util.ArrayList;

public class CovidVacGridAdapter extends BaseAdapter {
    Context c;
    ArrayList<VaccineModel> vaccineModels;

    public CovidVacGridAdapter(Context pc, ArrayList<VaccineModel> pvacModel){
        this.c=pc;
        this.vaccineModels=pvacModel;
    }

    @Override
    public int getCount() {
        return vaccineModels.size();
    }

    @Override
    public Object getItem(int i) {
        return vaccineModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.activity_covid_vac_grid,viewGroup,false);
        }
        TextView vacName= (TextView) view.findViewById(R.id.grdcovidvacname);
        TextView vacorg= (TextView) view.findViewById(R.id.grdcovidvacorg);
        TextView vacphase= (TextView) view.findViewById(R.id.grdCovidvaccurrphase);
        TextView vacdate= (TextView) view.findViewById(R.id.grdCovidlstdate);

        VaccineModel vaccineModel = (VaccineModel) this.getItem(i);

        vacName.setText(vaccineModel.vaccineName);
        vacorg.setText(vaccineModel.organisation);
        vacphase.setText(vaccineModel.currentPhase);
        vacdate.setText(vaccineModel.lastUpdatedDate);

        return view;

    }
}
