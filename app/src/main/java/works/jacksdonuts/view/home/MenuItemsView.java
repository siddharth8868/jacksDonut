package works.jacksdonuts.view.home;

import works.jacksdonuts.core.BaseView;
import works.jacksdonuts.core.initialdata.Menu;
import works.jacksdonuts.view.home.adapters.MenuItemsListAdapter;

/**
 * Created by siddharth on 6/27/18.
 */

public interface MenuItemsView extends BaseView {
    Menu getMenu();

    void setPageDescription(String description);

    void setListView(MenuItemsListAdapter adapter);
}
