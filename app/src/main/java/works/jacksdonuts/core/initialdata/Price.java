
package works.jacksdonuts.core.initialdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Price implements Serializable {

    @SerializedName("Each")
    @Expose
    private String each;
    @SerializedName("Dozen")
    @Expose
    private String dozen;
    @SerializedName("12oz")
    @Expose
    private String _12oz;
    @SerializedName("16oz")
    @Expose
    private String _16oz;
    @SerializedName("20oz")
    @Expose
    private String _20oz;

    public String getEach() {
        return each;
    }

    public void setEach(String each) {
        this.each = each;
    }

    public String getDozen() {
        return dozen;
    }

    public void setDozen(String dozen) {
        this.dozen = dozen;
    }

    public String get12oz() {
        return _12oz;
    }

    public void set12oz(String _12oz) {
        this._12oz = _12oz;
    }

    public String get16oz() {
        return _16oz;
    }

    public void set16oz(String _16oz) {
        this._16oz = _16oz;
    }

    public String get20oz() {
        return _20oz;
    }

    public void set20oz(String _20oz) {
        this._20oz = _20oz;
    }

}
