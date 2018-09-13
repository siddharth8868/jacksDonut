package works.jacksdonuts.view.locations;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.google.android.gms.maps.model.MarkerOptions;

import works.jacksdonuts.R;
import works.jacksdonuts.core.BaseView;
import works.jacksdonuts.core.initialdata.Location;
import works.jacksdonuts.presenter.locations.LocationDetailsPresenter;
import works.jacksdonuts.presenter.locations.LocationDetailsPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationDetailsFragment extends Fragment implements LocationDetailsView, OnMapReadyCallback {

    private static final String LOCATION_KEY = "location_key";
    private Location location;
    private View view;
    private Object listLayout;
    private GoogleMap mMap;
    private LocationDetailsPresenter presenter;

    public LocationDetailsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LocationDetailsPresenterImpl();
        presenter.bind(this);
    }

    public static LocationDetailsFragment getInstance(@NonNull Location location) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(LOCATION_KEY, location);

        LocationDetailsFragment fragment = new LocationDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private void readBundle(Bundle bundle) {
        this.location = (Location) bundle.getSerializable(LOCATION_KEY);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_location_details, null);

        readBundle(getArguments());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listLayout = getActivity().findViewById(R.id.fragment_location_details_list_layout);

        FragmentManager fm = getChildFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentByTag("mapFragment");
        if (mapFragment == null) {
            mapFragment = new MapFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_location_details_maps_frame, mapFragment, "mapFragment");
            ft.commit();
            fm.executePendingTransactions();
        }

/*        MapFragment mapFragment = (MapFragment) getChildFragmentManager()
                .findFragmentById(R.id.fragment_location_details_maps_frame);*/

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        if (presenter == null) {
            return;
        }
        presenter.initializeWork();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissProgressDialog() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng area = new LatLng(Double.valueOf(location.getLat()), Double.valueOf(location.getLng()));
        mMap.addMarker(new MarkerOptions().position(area).title(location.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(area, 16));

    }

    @Override
    public Location getLocationDetails() {
        return location;
    }

    @Override
    public void updateLocationList(final Location location) {
        if (listLayout == null) {
            return;
        }

        TextView address = view.findViewById(R.id.fragment_location_details_address);
        final TextView phone = view.findViewById(R.id.fragment_location_details_phone);
        TextView facebook = view.findViewById(R.id.fragment_location_details_facebook);
        TextView instagram = view.findViewById(R.id.fragment_location_details_instagram);
        TextView twitter = view.findViewById(R.id.fragment_location_details_twitter);

        // add values to text views
        final String addVal = location.getAddress() +", "+ location.getCity() +", "+ location.getState() +" "+location.getZip();
        address.setText(addVal);
        phone.setText(location.getPhone());


        // set clicker listener
        ((LinearLayout)address.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String label = location.getName();
                String uriBegin = "geo:"+location.getLat()+","+location.getLng()+"";
                String query = "12,34(" + label + ")";
                String encodedQuery = Uri.encode( query  );
                String uriString = uriBegin + "?q=" + encodedQuery;
                Uri uri = Uri.parse( uriString );
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri );
                startActivity( intent );
            }
        });

        ((LinearLayout)phone.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone.getText().toString())));
            }
        });

        ((LinearLayout)facebook.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(location.getFacebook())));
            }
        });

        ((LinearLayout)instagram.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(location.getInstagram())));
            }
        });

        ((LinearLayout)twitter.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(location.getTwitter())));
            }
        });


        // set visibility
        if (location.getFacebook() == null || location.getFacebook().equals("")) {
            ((LinearLayout) facebook.getParent()).setVisibility(View.GONE);
        }

        if (location.getInstagram() == null || location.getInstagram().equals("")) {
            ((LinearLayout) instagram.getParent()).setVisibility(View.GONE);
        }

        if (location.getTwitter() == null || location.getTwitter().equals("")) {
            ((LinearLayout) twitter.getParent()).setVisibility(View.GONE);
        }

    }
}
