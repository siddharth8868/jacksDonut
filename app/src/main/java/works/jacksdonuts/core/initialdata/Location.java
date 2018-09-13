
package works.jacksdonuts.core.initialdata;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location implements Serializable {

    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("zip")
    @Expose
    private String zip;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("instagram")
    @Expose
    private String instagram;
    @SerializedName("yelp")
    @Expose
    private String yelp;
    @SerializedName("hours")
    @Expose
    private List<String> hours = null;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getYelp() {
        return yelp;
    }

    public void setYelp(String yelp) {
        this.yelp = yelp;
    }

    public List<String> getHours() {
        return hours;
    }

    public void setHours(List<String> hours) {
        this.hours = hours;
    }



    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Location)) {
            return false;
        }
        Location location = (Location) o;
        return location.getPhone().equals(this.getAddress()) &&
                location.getCity().equals(this.getCity()) &&
                location.getFax().equals(this.getFax()) &&
                location.getAddress().equals(this.getAddress()) &&
                location.getName().equals(this.getName()) &&
                location.getEmail().equals(this.getEmail()) &&
                location.getZip().equals(this.getZip()) &&
                location.getTitle().equals(this.getTitle()) &&
                location.getState().equals(this.getState()) &&
                location.getLat().equals(this.getLat()) &&
                location.getLng().equals(this.getLng()) &&
                location.getFacebook().equals(this.getFacebook()) &&
                location.getTwitter().equals(this.getTwitter()) &&
                location.getInstagram().equals(this.getInstagram()) &&
                location.getYelp().equals(this.getYelp()) &&
                location.getHours().equals(this.getHours());
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
