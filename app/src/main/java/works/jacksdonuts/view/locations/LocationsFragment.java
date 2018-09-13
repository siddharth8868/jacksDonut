package works.jacksdonuts.view.locations;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import works.jacksdonuts.BuildConfig;
import works.jacksdonuts.R;
import works.jacksdonuts.core.initialdata.Location;
import works.jacksdonuts.presenter.locations.LocationsPresenter;
import works.jacksdonuts.presenter.locations.LocationsPresenterImpl;

/**
 * Created by siddharth on 6/22/18.
 */

public class LocationsFragment extends Fragment implements LocationsView, OnMapReadyCallback {

    private LocationsActivity activity;
    private LocationsPresenter presenter;
    private View view;
    private FragmentActivity fragmentActivityContext;
    private GoogleMap mMap;
    private LinearLayout listLayout;
    LatLngBounds.Builder builder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (LocationsActivity) getActivity();
        fragmentActivityContext=(FragmentActivity) getActivity();
        presenter = new LocationsPresenterImpl();
        presenter.bind(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_locations, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listLayout = activity.findViewById(R.id.fragment_locations_list_layout);

        builder = new LatLngBounds.Builder();

        FragmentManager fm = getChildFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentByTag("mapFragment1");
        if (mapFragment == null) {
            mapFragment = new MapFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_locations_maps_frame, mapFragment, "mapFragment1");
            ft.commit();
            fm.executePendingTransactions();
        }
        mapFragment.getMapAsync(this);
    }

    @Override
    public void showProgressDialog() {
        activity.showProgressDialog();
    }

    @Override
    public void dismissProgressDialog() {
        activity.dismissProgressDialog();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(39.140522,-103.645660), 18));

        if (presenter == null) {
            return;
        }
        presenter.initializeWork();

        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                showMarkersOnMap();
            }
        });
    }

    @Override
    public void addLocationRow(final Location location) {
        if (listLayout == null) {
            return;
        }

        View rowView = LayoutInflater.from(activity).inflate(R.layout.adapter_location_fragment_list_row_item, null);
        TextView name = rowView.findViewById(R.id.fragment_locations_list_row_item_name);
        TextView address = rowView.findViewById(R.id.fragment_locations_list_row_item_address);

        name.setText(location.getName());
        final String addVal = location.getAddress() +", "+ location.getCity() +", "+ location.getState() +" "+location.getZip();
        address.setText(addVal);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LocationDetailsFragment fragment = LocationDetailsFragment.getInstance(location);
                activity.pushFragment(fragment);
            }
        });

        if (mMap != null) {

            LatLng loc = new LatLng(Double.valueOf(location.getLat()), Double.valueOf(location.getLng()));
            Marker marker = mMap.addMarker(new MarkerOptions().position(loc).title(location.getName()));
            builder.include(marker.getPosition());
        }
        listLayout.addView(rowView);
    }

    @Override
    public void showMarkersOnMap() {
        if (mMap == null) {
            return;
        }
        LatLngBounds bounds = builder.build();

        FragmentManager fm = getChildFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentByTag("mapFragment1");

        int width = mapFragment.getView().getWidth();
        int height = mapFragment.getView().getHeight();
        int padding = (int) (width * 0.12);
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding));
    }
}
