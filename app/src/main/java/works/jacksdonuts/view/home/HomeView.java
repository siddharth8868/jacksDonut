package works.jacksdonuts.view.home;

import java.util.List;

import works.jacksdonuts.core.BaseView;
import works.jacksdonuts.core.initialdata.Location;

/**
 * Created by siddharth on 6/20/18.
 */

public interface HomeView extends BaseView{
    void startSlideShow();

    void updateListView(List<Location> locations);

    void moveToMenu();
}
