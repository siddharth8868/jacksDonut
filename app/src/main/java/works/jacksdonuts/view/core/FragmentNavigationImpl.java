package works.jacksdonuts.view.core;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by siddharth on 6/19/18.
 */

public final class FragmentNavigationImpl implements FragmentNavigation {

    @NonNull
    private final FragmentManager fragmentManager;

    @IdRes
    private final int containerViewId;

    public FragmentNavigationImpl(@NonNull FragmentManager fragmentManager, @IdRes int containerViewId) {
        this.fragmentManager = fragmentManager;
        this.containerViewId = containerViewId;
    }

    @Nullable
    @Override
    public Fragment getFragment() {
        return fragmentManager.findFragmentById(containerViewId);
    }

    @Override
    public int getFragmentStackCount() {
        return fragmentManager.getBackStackEntryCount() + 1;
    }

    @Override
    public void pushFragment(@NonNull Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(containerViewId, fragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void pushFragmentByRemovingCurrent(@NonNull Fragment fragment) {
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction()
                .replace(containerViewId, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void popFragment() {
        fragmentManager.popBackStack();
    }

    @Override
    public void setFragment(@NonNull Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(containerViewId, fragment)
                .commit();
    }
}
