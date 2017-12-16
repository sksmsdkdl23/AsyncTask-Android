package ca.bcit.ass2.assignment2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Phili on 10/11/2017.
 */

public class CountryAdapter extends BaseAdapter  {

    private final ArrayList arrayList;
    Context _context;

    public CountryAdapter(Context context, Map<String, List<Country>> map) {
        arrayList = new ArrayList();
        arrayList.addAll(map.entrySet());
        _context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Map.Entry<String, List<Country>> getItem(int i) {
        return (Map.Entry) arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View covertView, ViewGroup parent) {
        final View result;
        final Activity activity = (Activity) _context;

        if(covertView == null) {
            covertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_layout, parent, false);
        }

        TextView continent = (TextView) covertView.findViewById(R.id.region);

        Map.Entry<String, List<Country>> item = getItem(i);

        continent.setText(item.getKey());
        return continent;
    }
}
