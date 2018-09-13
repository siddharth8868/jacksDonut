package works.jacksdonuts.state;

import works.jacksdonuts.core.initialdata.InitialData;
import works.jacksdonuts.core.initialdata.Location;

/**
 * Created by siddharth on 6/23/18.
 */

public class Session {

    private InitialData initialData;

    private Location storeLocation = null;

    public InitialData getInitialData() {
        return initialData;
    }

    public void setInitialData(InitialData initialData) {
        this.initialData = initialData;
    }

    public Location getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(Location storeLocation) {
        this.storeLocation = storeLocation;
    }

}
