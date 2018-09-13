package works.jacksdonuts.view.home;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import works.jacksdonuts.R;
import works.jacksdonuts.core.initialdata.Location;
import works.jacksdonuts.presenter.home.HomePresenter;
import works.jacksdonuts.presenter.home.HomePresenterImpl;
import works.jacksdonuts.view.home.adapters.LocationsListAdapter;

/**
 * Created by siddharth on 6/20/18.
 */

public class HomeFragment extends Fragment implements HomeView {

    private HomePresenter presenter;
    private View view;
    private ListView listView;
    private HomeActivity activity;
    private ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] ROTATION_IMAGES = {R.drawable.rotation_image_1,
            R.drawable.rotation_image_2,
            R.drawable.rotation_image_3,
            R.drawable.rotation_image_4,
            R.drawable.rotation_image_5
    };
    private ArrayList<Integer> RotationImagesArray = new ArrayList<Integer>();

    public HomeFragment () {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (HomeActivity) getActivity();
        presenter = new HomePresenterImpl();
        presenter.bind(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = view.findViewById(R.id.home_fragment_locations_list_view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter.initializeWork();
    }

    @Override
    public void startSlideShow() {
        RotationImagesArray.addAll(Arrays.asList(ROTATION_IMAGES));

        mPager = (ViewPager) view.findViewById(R.id.home_fragment_pager);
        mPager.setAdapter(new RotationImageAdapter(activity, RotationImagesArray));
        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.home_fragment_indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == ROTATION_IMAGES.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 4000, 4000);
    }

    @Override
    public void updateListView(List<Location> locations) {
        LocationsListAdapter adapter = new LocationsListAdapter(getContext(), locations, new LocationsListAdapter.Delegate() {
            @Override
            public void onListItemSelected(Location location) {
                //Should show Menu add call on location selected
                presenter.onListItemSelected(location);
            }
        });
        listView.setAdapter(adapter);
    }

    @Override
    public void moveToMenu() {
        MenusFragment fragment = new MenusFragment();
        activity.pushFragment(fragment);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissProgressDialog() {

    }
}
