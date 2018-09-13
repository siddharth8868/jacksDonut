package works.jacksdonuts.core.rest;

/**
 * Created by siddharth on 6/19/18.
 */

public class NetworkCallManager {
    private static NetworkCallManager ourInstance;

    public static NetworkCallManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new NetworkCallManager();
        }
        return ourInstance;
    }

}
