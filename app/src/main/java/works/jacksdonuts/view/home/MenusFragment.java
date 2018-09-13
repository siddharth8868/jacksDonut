package works.jacksdonuts.view.home;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import works.jacksdonuts.R;
import works.jacksdonuts.core.initialdata.Menu;
import works.jacksdonuts.presenter.home.MenusPresenter;
import works.jacksdonuts.presenter.home.MenusPresenterImpl;
import works.jacksdonuts.view.home.adapters.MenusListAdapter;

/**
 * Created by siddharth on 6/27/18.
 */

public class MenusFragment extends Fragment implements MenusView {

    private HomeActivity activity;
    private MenusPresenter presenter;
    private View view;
    private ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (HomeActivity) getActivity();
        presenter = new MenusPresenterImpl();
        presenter.bind(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menus, container, false);
        listView = view.findViewById(R.id.fragment_menu_list_view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter.initializeWork();
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
    public void setListView(MenusListAdapter adapter) {
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void moveToMenuItems(Menu menu) {
        MenuItemsFragment fragment = MenuItemsFragment.newInstance(menu);
        activity.pushFragment(fragment);
    }
}
