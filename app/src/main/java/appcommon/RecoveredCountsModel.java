package appcommon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.atyourservice.R;

import java.util.ArrayList;

public class RecoveredCountsModel {
    public String notes;
    public String dtRecordedDate;
    public String active;
    public String confirmed;
    public String deceased;
    public String recovered;
    public String districtName;
    public String stateName;

    public static class HospitalGrid extends BaseAdapter {
        Context c;
        ArrayList<CovidHospitallist> hosplist;

        public HospitalGrid(Context c, ArrayList<CovidHospitallist> phoslist) {
            this.c = c;
            this.hosplist = phoslist;
        }


        @Override
        public int getCount() {
            return hosplist.size();
        }

        @Override
        public Object getItem(int i) {
            return hosplist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null)
            {
                view= LayoutInflater.from(c).inflate(R.layout.activity_hospital_grid,viewGroup,false);
            }
            TextView hosname= (TextView) view.findViewById(R.id.grdhosname);
            TextView hoscity= (TextView) view.findViewById(R.id.grdhoscity);
            TextView hosowner= (TextView) view.findViewById(R.id.grdhosowner);
            TextView hosbeds= (TextView) view.findViewById(R.id.grdhosbeds);

            CovidHospitallist covidHospitallist = (CovidHospitallist) this.getItem(i);

            hosname.setText(covidHospitallist.name);
            hoscity.setText(covidHospitallist.city);
            hosowner.setText(covidHospitallist.ownership);
            hosbeds.setText(covidHospitallist.hospitalBeds);

            return view;

        }
    }
}
