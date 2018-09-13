
package works.jacksdonuts.core.initialdata;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InitialData {

    @SerializedName("app")
    @Expose
    private App app;
    @SerializedName("locations")
    @Expose
    private List<Location> locations = null;
    @SerializedName("rotations")
    @Expose
    private List<String> rotations = null;
    @SerializedName("menus")
    @Expose
    private List<Menu> menus = null;

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<String> getRotations() {
        return rotations;
    }

    public void setRotations(List<String> rotations) {
        this.rotations = rotations;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

}
