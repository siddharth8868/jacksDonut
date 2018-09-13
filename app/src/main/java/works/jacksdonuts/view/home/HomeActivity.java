package works.jacksdonuts.view.home;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import works.jacksdonuts.R;
import works.jacksdonuts.core.NavigationDrawerActivity;

public class HomeActivity extends NavigationDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomeFragment fragment = new HomeFragment();
        setFragment(fragment);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(R.string.home);
    }
}
