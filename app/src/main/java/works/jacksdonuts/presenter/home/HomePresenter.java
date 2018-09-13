package works.jacksdonuts.presenter.home;

import works.jacksdonuts.core.BasePresenter;
import works.jacksdonuts.core.initialdata.Location;

/**
 * Created by siddharth on 6/20/18.
 */

public interface HomePresenter extends BasePresenter{
    void onListItemSelected(Location location);
}
