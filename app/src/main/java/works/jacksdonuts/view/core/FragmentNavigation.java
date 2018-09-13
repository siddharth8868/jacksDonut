package works.jacksdonuts.view.core;

import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by siddharth on 6/19/18.
 */

public interface FragmentNavigation {

    int getFragmentStackCount();

    @Nullable
    Fragment getFragment();

    void pushFragment(@NonNull Fragment fragment);

    void pushFragmentByRemovingCurrent(@NonNull Fragment fragment);

    void popFragment();

    void setFragment(@NonNull Fragment fragment);
}
