package works.jacksdonuts.presenter.home;

import android.support.annotation.NonNull;

import java.util.List;

import works.jacksdonuts.R;
import works.jacksdonuts.state.Session;
import works.jacksdonuts.state.SessionManager;
import works.jacksdonuts.core.BaseView;
import works.jacksdonuts.core.initialdata.InitialData;
import works.jacksdonuts.core.initialdata.Location;
import works.jacksdonuts.util.AlertDialogUtil;
import works.jacksdonuts.view.home.HomeView;

/**
 * Created by siddharth on 6/20/18.
 */

public class HomePresenterImpl implements HomePresenter {

    private HomeView view;

    @Override
    public void bind(@NonNull BaseView view) {
        this.view = (HomeView) view;
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
        view.startSlideShow();

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
        view.updateListView(locations);

    }

    @Override
    public void onListItemSelected(final Location location) {

        final Session  session = SessionManager.getInstance().getSession();
        if (session == null) {
            return;
        }
        Location storeLocation = session.getStoreLocation();
        if (storeLocation != null && !storeLocation.equals(location)) {
             AlertDialogUtil.getAlertDialog(view.getContext(), R.string.fragment_home_location_change_alert_msg, null, new AlertDialogUtil.Delegate() {

                @Override
                public void onPositiveButtonClicked() {
                    session.setStoreLocation(location);
                    view.moveToMenu();
                }

                @Override
                public void onNegativeButtonClicked() {
                    // do nothing
                }
            }).show();
        } else {
            session.setStoreLocation(location);
            view.moveToMenu();
        }
    }



}
