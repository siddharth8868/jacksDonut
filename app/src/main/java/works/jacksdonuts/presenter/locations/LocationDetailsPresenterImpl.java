package works.jacksdonuts.presenter.locations;

import android.support.annotation.NonNull;

import works.jacksdonuts.core.BaseView;
import works.jacksdonuts.core.initialdata.Location;
import works.jacksdonuts.view.locations.LocationDetailsView;

/**
 * Created by siddharth on 6/23/18.
 */

public class LocationDetailsPresenterImpl implements LocationDetailsPresenter {

    private LocationDetailsView view;

    @Override
    public void bind(@NonNull BaseView view) {
        this.view = (LocationDetailsView) view;
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

        final Location location = view.getLocationDetails();

        view.updateLocationList(location);
    }
}
