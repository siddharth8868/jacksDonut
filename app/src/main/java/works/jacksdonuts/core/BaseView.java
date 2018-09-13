package works.jacksdonuts.core;

import android.content.Context;

/**
 * Created by siddharth on 6/20/18.
 */

public interface BaseView {

    Context getContext();

    void showProgressDialog();

    void dismissProgressDialog();
}
