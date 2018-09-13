package works.jacksdonuts.view.home.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import works.jacksdonuts.R;
import works.jacksdonuts.core.initialdata.MenuItem;
import works.jacksdonuts.core.initialdata.Price;
import works.jacksdonuts.util.GeneralUtil;

/**
 * Created by siddharth on 6/27/18.
 */

public class MenuItemsListAdapter extends BaseAdapter {

    private Context context;
    private List<MenuItem> menuItemList;
    private String menuType;
    private MenuItemsDelegate delegate;

    public MenuItemsListAdapter(Context context, List<MenuItem> menuItemList, String menuType, MenuItemsDelegate delegate) {
        this.context = context;
        this.menuItemList = menuItemList;
        this.menuType = menuType;
        this.delegate = delegate;
    }

    @Override
    public int getCount() {
        return menuItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return menuItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final MenuItem menuItem = (MenuItem) getItem(position);
        ViewHolder viewHolder = null;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_menu_items_list_row_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        int resourceID = GeneralUtil.getResId(menuItem.getIconFilename(), Drawable.class);
        if(resourceID != -1) {
            viewHolder.imageView.setImageResource(resourceID);
        }
        viewHolder.name.setText(menuItem.getName());

        final Price price = GeneralUtil.getPrice(menuItem.getPrice());

        if (context.getString(MenuType.DONUTS.getTypeId()).equals(menuType)) {
            viewHolder.price.setText("$" + price.getDozen());
        } else if (context.getString(MenuType.DRINKS.getTypeId()).equals(menuType)) {
            viewHolder.price.setText("$" + price.get20oz());
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.onItemClicked(menuItem);
            }
        });

        return convertView;
    }

    public static class ViewHolder {
        private ImageView imageView;
        private TextView name, price;

        ViewHolder(View view) {
            imageView = view.findViewById(R.id.menu_items_list_row_name_image);
            name = view.findViewById(R.id.menu_items_list_row_name);
            price = view.findViewById(R.id.menu_items_list_row_price);
        }
    }

    public interface MenuItemsDelegate {
        void onItemClicked(MenuItem menuItem);
    }
}
