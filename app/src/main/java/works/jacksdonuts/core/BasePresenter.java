package works.jacksdonuts.core;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by siddharth on 6/20/18.
 */

public interface BasePresenter {

    void bind(@NonNull BaseView view);

    void unBind();

    void initializeWork();
}
