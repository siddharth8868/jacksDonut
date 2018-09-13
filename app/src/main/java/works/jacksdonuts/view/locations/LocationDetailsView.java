package works.jacksdonuts.view.locations;

import works.jacksdonuts.core.BaseView;
import works.jacksdonuts.core.initialdata.Location;

/**
 * Created by siddharth on 6/23/18.
 */

public interface LocationDetailsView extends BaseView {

    Location getLocationDetails();

    void updateLocationList(Location location);
}
