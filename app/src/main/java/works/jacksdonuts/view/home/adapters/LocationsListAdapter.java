package works.jacksdonuts.view.home.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import works.jacksdonuts.R;
import works.jacksdonuts.core.initialdata.Location;

/**
 * Created by siddharth on 6/24/18.
 */

public class LocationsListAdapter extends BaseAdapter {

    private Context context;
    private List<Location> locations;
    private Delegate delegate;

    public LocationsListAdapter(Context context, List<Location> locations, Delegate delegate) {
        this.context = context;
        this.locations = locations;
        this.delegate = delegate;
    }

    @Override
    public int getCount() {
        return locations.size();
    }

    @Override
    public Object getItem(int position) {
        return locations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Location location = (Location) getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_location_fragment_list_row_item, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(location.getName());
        final String addVal = location.getAddress() +", "+ location.getCity() +", "+ location.getState() +" "+location.getZip();
        viewHolder.location.setText(addVal);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.onListItemSelected(location);
            }
        });

        return convertView;
    }

    private class ViewHolder {

        private TextView title, location;
        LinearLayout divider;

        ViewHolder(@NonNull View view) {
            title = view.findViewById(R.id.fragment_locations_list_row_item_name);
            location = view.findViewById(R.id.fragment_locations_list_row_item_address);
            divider = view.findViewById(R.id.fragment_locations_list_row_item_divider);

            divider.setVisibility(View.GONE);
            title.setTextColor(context.getResources().getColor(R.color.white));
            location.setTextColor(context.getResources().getColor(R.color.white));
        }
    }

    public interface Delegate {
        void onListItemSelected(Location location);
    }
}
