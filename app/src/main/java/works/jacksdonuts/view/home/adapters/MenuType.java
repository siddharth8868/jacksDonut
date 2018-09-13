package works.jacksdonuts.view.home.adapters;

import android.support.annotation.StringRes;

import works.jacksdonuts.R;

/**
 * Created by siddharth on 6/27/18.
 */

public enum MenuType {

    DONUTS(R.string.menu_type_donuts),
    DRINKS(R.string.menu_type_drinks);

    @StringRes
    private final int typeId;

    MenuType(int typeId) {
        this.typeId = typeId;
    }


    public int getTypeId() {
        return typeId;
    }

}