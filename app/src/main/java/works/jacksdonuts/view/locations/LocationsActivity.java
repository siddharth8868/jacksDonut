package works.jacksdonuts.view.locations;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;

import works.jacksdonuts.R;
import works.jacksdonuts.core.NavigationDrawerActivity;

public class LocationsActivity extends NavigationDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocationsFragment fragment = new LocationsFragment();
        setFragment(fragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(R.string.contact_us);
    }
}
