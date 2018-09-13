package works.jacksdonuts.core;

import android.Manifest;
import android.content.Context;
import android.support.annotation.RequiresPermission;

/**
 * Created by siddharth on 6/19/18.
 */

public class DeviceHardwareUtility {
    private static final String MAC_ID = "MID";
    /**
     * Singleton instance
     */
    private static DeviceHardwareUtility instance = new DeviceHardwareUtility();

    private static ActivityHelper activityHelper;
    private Context context = null;

    /**
     * Constructor
     */
    private DeviceHardwareUtility() {
    }

    public static DeviceHardwareUtility getInstance(Context context) {
        instance.context = context;
        // get reference to ActivityHelper singleton
        activityHelper = ActivityHelper.getInstance(context);
        return instance;
    }

    /**
     * store MAC Address so that we don't have to retrieve it everytime, that way
     * we'll still have access to it later in case WIFI is disabled
     * @macAddress
     */
    @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
    public String getMacAddress() {
        String macAddress = activityHelper.getPreferencesAttribute(MAC_ID, null);
        return macAddress;
    }
}
