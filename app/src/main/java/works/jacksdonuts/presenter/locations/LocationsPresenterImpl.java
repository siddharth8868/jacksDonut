package works.jacksdonuts.presenter.locations;

import android.support.annotation.NonNull;

import java.util.List;

import works.jacksdonuts.state.Session;
import works.jacksdonuts.state.SessionManager;
import works.jacksdonuts.core.BaseView;
import works.jacksdonuts.core.initialdata.InitialData;
import works.jacksdonuts.core.initialdata.Location;
import works.jacksdonuts.view.locations.LocationsView;

/**
 * Created by siddharth on 6/22/18.
 */

public class LocationsPresenterImpl implements LocationsPresenter {

    private LocationsView view;

    @Override
    public void bind(@NonNull BaseView view) {
        this.view = (LocationsView) view;
    }

    @Override
    public void unBind() {
        view = null;
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

        List<Location> locations = initialData.getLocations();

        if (locations == null) {
            return;
        }

        for (Location location : locations) {
            view.addLocationRow(location);
        }
    }


}
