package works.jacksdonuts.view.home;

import works.jacksdonuts.core.BaseView;
import works.jacksdonuts.core.initialdata.Menu;
import works.jacksdonuts.view.home.adapters.MenusListAdapter;

/**
 * Created by siddharth on 6/27/18.
 */

public interface MenusView extends BaseView {
    void setListView(MenusListAdapter adapter);

    void moveToMenuItems(Menu menu);
}
