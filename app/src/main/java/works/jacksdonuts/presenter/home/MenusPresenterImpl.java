package works.jacksdonuts.presenter.home;

import android.support.annotation.NonNull;

import java.util.List;

import works.jacksdonuts.core.BaseView;
import works.jacksdonuts.core.initialdata.InitialData;
import works.jacksdonuts.core.initialdata.Menu;
import works.jacksdonuts.state.Session;
import works.jacksdonuts.state.SessionManager;
import works.jacksdonuts.view.home.adapters.MenusListAdapter;
import works.jacksdonuts.view.home.MenusView;

/**
 * Created by siddharth on 6/27/18.
 */

public class MenusPresenterImpl implements MenusPresenter {

    private MenusView view;

    @Override
    public void bind(@NonNull BaseView view) {
        this.view = (MenusView) view;
    }

    @Override
    public void unBind() {
        this.view = null;
    }

    @Override
    public void initializeWork() {
        if (view == null) {
            return;
        }

        Session session = SessionManager.getInstance().getSession();
        if (session == null) {
            return;
        }
        InitialData initialData = session.getInitialData();
        if (initialData == null) {
            return;
        }
        List<Menu> menus = initialData.getMenus();

        MenusListAdapter adapter = new MenusListAdapter(view.getContext(), menus, new MenusListAdapter.MenuListDelegate() {
            @Override
            public void onItemClicked(Menu menu) {
                view.moveToMenuItems(menu);
            }
        });

        view.setListView(adapter);
    }
}
