package appcommon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.atyourservice.R;

import java.util.ArrayList;

public class CovidDashboadAdapter extends BaseAdapter {

    Context c;
    ArrayList<RecoveredCountsModel> recoveredCountsModels;

    public CovidDashboadAdapter(Context pc, ArrayList<RecoveredCountsModel> precoveredCounts){
        this.c=pc;
        this.recoveredCountsModels=precoveredCounts;
    }

    @Override
    public int getCount() {
        return recoveredCountsModels.size();
    }

    @Override
    public Object getItem(int i) {
        return recoveredCountsModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.activity_covid_dashgrid,viewGroup,false);
        }
        TextView recovered= (TextView) view.findViewById(R.id.grdcovidRec);
        TextView total= (TextView) view.findViewById(R.id.grdcovidTot);
        TextView active= (TextView) view.findViewById(R.id.grdCovidActive);
        TextView deceased= (TextView) view.findViewById(R.id.grdCovidDece);

        RecoveredCountsModel recCounts = (RecoveredCountsModel) this.getItem(i);

        recovered.setText(recCounts.recovered);
        total.setText(recCounts.confirmed);
        active.setText(recCounts.active);
        deceased.setText(recCounts.deceased);

        return view;

    }
}
