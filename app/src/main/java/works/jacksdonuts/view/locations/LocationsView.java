package works.jacksdonuts.view.locations;

import works.jacksdonuts.core.BaseView;
import works.jacksdonuts.core.initialdata.Location;

/**
 * Created by siddharth on 6/22/18.
 */

public interface LocationsView extends BaseView {
    void addLocationRow(Location location);

    void showMarkersOnMap();
}
