package works.jacksdonuts.presenter.home;

import android.support.annotation.NonNull;

import works.jacksdonuts.core.BaseView;
import works.jacksdonuts.core.initialdata.Menu;
import works.jacksdonuts.core.initialdata.MenuItem;
import works.jacksdonuts.view.home.MenuItemsView;
import works.jacksdonuts.view.home.adapters.MenuItemsListAdapter;

/**
 * Created by siddharth on 6/27/18.
 */

public class MenuItemsPresenterImpl implements MenuItemsPresenter {

    private MenuItemsView view;
    @Override
    public void bind(@NonNull BaseView view) {
        this.view = (MenuItemsView) view;
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

        Menu menu = view.getMenu();
        String description = menu.getDescription();
        view.setPageDescription(description);

        MenuItemsListAdapter adapter = new MenuItemsListAdapter(view.getContext(), menu.getData(), menu.getType(), new MenuItemsListAdapter.MenuItemsDelegate() {
            @Override
            public void onItemClicked(MenuItem menuItem) {
                //Should work from here
            }
        });
        view.setListView(adapter);
    }
}
