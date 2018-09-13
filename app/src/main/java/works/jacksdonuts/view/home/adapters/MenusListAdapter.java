package works.jacksdonuts.view.home.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import works.jacksdonuts.R;
import works.jacksdonuts.core.initialdata.Menu;
import works.jacksdonuts.util.GeneralUtil;

/**
 * Created by siddharth on 6/27/18.
 */

public class MenusListAdapter extends BaseAdapter {

    private Context context;

    private List<Menu> menus;

    private MenuListDelegate delegate;

    public MenusListAdapter(Context context, List<Menu> menus, MenuListDelegate delegate) {
        this.context = context;
        this.menus = menus;
        this.delegate =delegate;
    }

    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public Object getItem(int position) {
        return menus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Menu menu = (Menu) getItem(position);
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_menus_list_row_item, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(menu.getName());

        int resourceID = GeneralUtil.getResId(menu.getIconFilename(), Drawable.class);
        if(resourceID != -1) {
            viewHolder.layout.setBackground(context.getResources().getDrawable(resourceID));
        }
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.onItemClicked(menu);
            }
        });

        return convertView;
    }


    private class ViewHolder {
        private RelativeLayout layout;
        private TextView name;

        ViewHolder(View view) {
          layout = view.findViewById(R.id.menu_list_row_item);
            name = view.findViewById(R.id.menu_list_row_item_name);
        }
    }

    public interface MenuListDelegate {
        void onItemClicked(Menu menu);
    }
}

