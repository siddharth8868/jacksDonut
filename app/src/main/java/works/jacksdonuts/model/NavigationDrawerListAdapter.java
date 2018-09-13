package works.jacksdonuts.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import works.jacksdonuts.R;

/**
 * Created by siddharth on 6/20/18.
 */

public class NavigationDrawerListAdapter extends BaseAdapter {

    private static final NavigationItem[] NAV_ITEMS = {
            NavigationItem.NAV_HOME,
            NavigationItem.NAV_PROMOTIONS,
            NavigationItem.NAV_ACCOUNT,
            NavigationItem.NAV_ORDER_HISTORY,
            NavigationItem.NAV_CONTACT_US
    };

    private Context context;

    private Delegate delegate;

    private List<NavigationItem> list;

    public NavigationDrawerListAdapter(Context context, Delegate delegate) {
        this.context = context;
        this.delegate = delegate;

        list = Arrays.asList(NAV_ITEMS);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        final NavigationItem navigationItem = (NavigationItem) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.adapter_navigation_drawer_row_item, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.onNavigationItemSelected(navigationItem);
            }
        });

        viewHolder.textView.setText(context.getString(navigationItem.getNameId()));

        switch (navigationItem.getNameId()) {
            case R.string.nav_item_account:
                viewHolder.imageView.setImageResource(R.drawable.ic_account_black_24dp);
                break;

            case R.string.nav_item_contact_us:
                viewHolder.imageView.setImageResource(R.drawable.ic_map_black_24dp);
                break;

            case R.string.nav_item_home:
                viewHolder.imageView.setImageResource(R.drawable.ic_home_black_24dp);
                break;

            case R.string.nav_item_order_history:
                viewHolder.imageView.setImageResource(R.drawable.ic_order_history_black_24dp);
                break;

            case R.string.nav_item_promotions:
                viewHolder.imageView.setImageResource(R.drawable.ic_promotiont_chart_black_24dp);
                break;

        }
        return convertView;
    }

    private class ViewHolder {

        private ImageView imageView;
        private TextView textView;
        private RelativeLayout layout;

        ViewHolder(View convertView) {
            imageView = convertView.findViewById(R.id.navigation_drawer_row_image);
            textView = convertView.findViewById(R.id.navigation_drawer_row_name);
            layout =  convertView.findViewById(R.id.navigation_drawer_row_layout);
        }
    }

    public interface Delegate {
        void onNavigationItemSelected(NavigationItem item);
    }


}
