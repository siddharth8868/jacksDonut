package works.jacksdonuts.view.account;

import works.jacksdonuts.core.BaseView;

/**
 * Created by siddharth on 6/24/18.
 */

public interface AccountSummeryView extends BaseView {
    void updateWelcomeMsg(String username);

    void moveToAccountFragment();
}
