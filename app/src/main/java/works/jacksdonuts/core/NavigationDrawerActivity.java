package works.jacksdonuts.core;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import works.jacksdonuts.BuildConfig;
import works.jacksdonuts.R;
import works.jacksdonuts.model.NavigationItem;
import works.jacksdonuts.view.account.AccountActivity;
import works.jacksdonuts.view.core.FragmentNavigation;
import works.jacksdonuts.view.core.FragmentNavigationImpl;
import works.jacksdonuts.view.core.NavigationDrawerFragment;
import works.jacksdonuts.view.home.HomeActivity;
import works.jacksdonuts.view.locations.LocationsActivity;


public abstract class NavigationDrawerActivity extends MobileBaseActivity
        implements FragmentNavigation, NavigationDrawerFragment.NavigationDrawerCallback {

    @LayoutRes
    private final int contentViewId;
    @Nullable
    private NavigationDrawerFragment navigationDrawerFragment;
    @Nullable
    private FragmentNavigation fragmentNavigation;
    @Nullable
    private ActionBarDrawerToggle drawerToggle;
    @Nullable
    private Toolbar toolbar;

    @Nullable
    private DrawerLayout drawerLayout;

    private LinearLayout contentFrame;

    private NavigationItem navigationItem;

    protected NavigationDrawerActivity(int contentViewId) {
        this.contentViewId = contentViewId;
    }

    protected NavigationDrawerActivity() {
        this(R.layout.activity_navigation_drawer);
    }

    protected final void closeDrawer() {
        if ((drawerLayout != null) && (navigationDrawerFragment != null)) {
            drawerLayout.closeDrawer(GravityCompat.START, true);
        } else if (BuildConfig.DEBUG) {
            throw new AssertionError();
        }
    }

    protected final void openDrawer() {
        if ((drawerLayout != null) && (navigationDrawerFragment != null)) {
            drawerLayout.openDrawer(GravityCompat.START, true);
        } else if (BuildConfig.DEBUG) {
            throw new AssertionError();
        }

    }

    public final void setNavigationIcon(@DrawableRes int drawableId) {
        if (toolbar != null) {
            toolbar.setNavigationIcon(drawableId);
        } else if (BuildConfig.DEBUG) {
            throw new AssertionError("Toolbar unavailable");
        }
    }

    public final void updateNavigationIcon() {
        setNavigationIcon(R.drawable.ic_menu_white_24dp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentViewId);
        drawerLayout = findViewById(R.id.drawer_layout);
        contentFrame = findViewById(R.id.content_frame);

        navigationDrawerFragment = new NavigationDrawerFragment();
        fragmentNavigation = new FragmentNavigationImpl(getFragmentManager(), R.id.layout_container);
        updateNavigationFragment();

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
        } else if (BuildConfig.DEBUG) {
            throw new AssertionError("Missing action bar!");
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if ((drawerLayout != null) && (toolbar != null)) {

            drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

/*            drawerToggle = new ActionBarDrawerToggle(
                    this,
                    drawerLayout,
                    toolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close
            );*/

            drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
            {
                @SuppressLint("NewApi")
                public void onDrawerSlide(View drawerView, float slideOffset)
                {
                    super.onDrawerSlide(drawerView, slideOffset);
                    float moveFactor = (findViewById(R.id.layout_navigation).getWidth() * slideOffset);
                        contentFrame.setTranslationX(moveFactor);
                }
            };

            final DrawerLayout.SimpleDrawerListener drawerListener = new DrawerLayout.SimpleDrawerListener() {

                @Override
                public void onDrawerOpened(View drawerView) {
                    final View currentFocus = getCurrentFocus();
                    if (currentFocus != null) {
                        final InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
                    }
                    supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
                }

            };
            drawerToggle.setDrawerIndicatorEnabled(false);
            drawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openDrawer();
                }
            });

            drawerLayout.addDrawerListener(drawerToggle);
            drawerLayout.addDrawerListener(drawerListener);

            // Defer code dependent on restoration of previous instance state.
            drawerLayout.post(new Runnable() {
                @Override
                public void run() {
                    drawerToggle.syncState();
                }
            });

            drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
                @Override
                public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

                }

                @Override
                public void onDrawerOpened(@NonNull View drawerView) {

                }

                @Override
                public void onDrawerClosed(@NonNull View drawerView) {
                    // Handle navigation view item clicks here.
                    if (navigationItem == null) {
                        return;
                    }
                    switch (navigationItem.getNameId()) {
                        case R.string.nav_item_account:
                            showAccount();
                            break;

                        case R.string.nav_item_contact_us:
                            showLocations();
                            break;

                        case R.string.nav_item_home:
                            showHome();
                            break;

                        case R.string.nav_item_order_history:
                            break;

                        case R.string.nav_item_promotions:
                            break;
                    }
                    navigationItem = null;
                }

                @Override
                public void onDrawerStateChanged(int newState) {

                }
            });

        } else if (BuildConfig.DEBUG) {
            throw new AssertionError("Missing toolbar and drawer layout!");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (navigationDrawerFragment != null) {
            navigationDrawerFragment.setNavigationDrawerCallback(this);
        }

        updateNavigationIcon();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return ((drawerToggle != null) && drawerToggle.onOptionsItemSelected(item)) ||
                super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if ((fragmentNavigation != null) && (fragmentNavigation.getFragmentStackCount() > 1)) {
            fragmentNavigation.popFragment();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (drawerToggle != null) {
            drawerToggle.onConfigurationChanged(newConfig);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (navigationDrawerFragment != null) {
            navigationDrawerFragment.setNavigationDrawerCallback(null);
        }
        fragmentNavigation = null;
    }

    @Override
    public int getFragmentStackCount() {
        if (fragmentNavigation != null) {
            return fragmentNavigation.getFragmentStackCount();
        } else {
            return 0;
        }
    }

    @Nullable
    @Override
    public Fragment getFragment() {
        if (fragmentNavigation != null) {
            return fragmentNavigation.getFragment();
        } else {
            return null;
        }
    }

    @Override
    public void pushFragment(@NonNull Fragment fragment) {
        if (fragmentNavigation != null) {
            fragmentNavigation.pushFragment(fragment);
        }
    }

    @Override
    public void pushFragmentByRemovingCurrent(@NonNull Fragment fragment) {
        if (fragmentNavigation != null) {
            fragmentNavigation.pushFragmentByRemovingCurrent(fragment);
        }
    }

    @Override
    public void popFragment() {
        if (fragmentNavigation != null) {
            fragmentNavigation.popFragment();
        }
    }

    @Override
    public void setFragment(@NonNull Fragment fragment) {
        if (fragmentNavigation != null) {
            fragmentNavigation.setFragment(fragment);
        }
    }


    @Nullable
    public FragmentNavigation getFragmentNavigation() {
        return fragmentNavigation;
    }

    protected final void updateNavigationFragment() {

        getFragmentManager().beginTransaction()
                .replace(R.id.layout_navigation, navigationDrawerFragment)
                .commit();
    }

    @Override
    public void onNavigationItemSelected(@NonNull NavigationItem item) {
        closeDrawer();

        navigationItem = item;
    }

    private void showAccount(){
        getActivityHelper().startActivityForIntent(this, AccountActivity.class,
                (Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    private void showHome(){
        getActivityHelper().startActivityForIntent(this, HomeActivity.class,
                (Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    private void showLocations(){
        getActivityHelper().startActivityForIntent(this, LocationsActivity.class,
                (Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }
}
