package works.jacksdonuts.view.home;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import works.jacksdonuts.R;
import works.jacksdonuts.core.initialdata.Menu;
import works.jacksdonuts.presenter.home.MenuItemsPresenter;
import works.jacksdonuts.presenter.home.MenuItemsPresenterImpl;
import works.jacksdonuts.view.home.adapters.MenuItemsListAdapter;

/**
 * Created by siddharth on 6/27/18.
 */

public class MenuItemsFragment extends Fragment implements MenuItemsView {

    private static final String MENU_VALUE = "menu_value";
    private HomeActivity activity;
    private MenuItemsPresenter presenter;
    private View view;
    private Menu menu;

    /**
     * this should <b>NEVER</b> be used, always use the newInstance method instead
     */
    @Deprecated
    public MenuItemsFragment() {
        //Empty Constructor
    }
    public static MenuItemsFragment newInstance(@NonNull Menu menu) {
        MenuItemsFragment menuItemsFragment = new MenuItemsFragment();
        menuItemsFragment.setArguments(createBundle(menu));
        return menuItemsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (HomeActivity) getActivity();
        presenter = new MenuItemsPresenterImpl();
        presenter.bind(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu_items, container, false);

        extractBundle(getArguments());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter.initializeWork();
    }

    private void extractBundle(Bundle bundle) {
        menu = (Menu) bundle.get(MENU_VALUE);
    }

    public static Bundle createBundle(Menu menu) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(MENU_VALUE, menu);
        return bundle;
    }

    @Override
    public void showProgressDialog() {
        activity.showProgressDialog();
    }

    @Override
    public void dismissProgressDialog() {
        activity.showProgressDialog();
    }

    @Override
    public Menu getMenu() {
        return this.menu;
    }

    @Override
    public void setPageDescription(String description) {
        final ListView listView = view.findViewById(R.id.fragment_menu__items_list);
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_menu_items_header, null);
        listView.addHeaderView(headerView);
        LinearLayout headerLayout = listView.findViewById(R.id.fragment_menu__items_header_layout);
        TextView descriptionTextView = listView.findViewById(R.id.fragment_menu__items_header_description);
        if (description != null && !description.equals("")) {
            descriptionTextView.setText(description);
            headerLayout.setVisibility(View.VISIBLE);
        } else {
            headerLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void setListView(MenuItemsListAdapter adapter) {
        final ListView listView = view.findViewById(R.id.fragment_menu__items_list);
        if (listView == null) {
            return;
        }
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
