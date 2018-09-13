package works.jacksdonuts.model;

import android.support.annotation.StringRes;

import works.jacksdonuts.R;

/**
 * Created by siddharth on 6/20/18.
 */

public enum NavigationItem {

    NAV_HOME(R.string.nav_item_home),
    NAV_PROMOTIONS(R.string.nav_item_promotions),
    NAV_ACCOUNT(R.string.nav_item_account),
    NAV_ORDER_HISTORY(R.string.nav_item_order_history),
    NAV_CONTACT_US(R.string.nav_item_contact_us);

    @StringRes
    private final int nameId;

    NavigationItem(int nameId) {
        this.nameId = nameId;
    }


    public int getNameId() {
        return nameId;
    }

}
