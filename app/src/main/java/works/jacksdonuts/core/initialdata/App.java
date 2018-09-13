
package works.jacksdonuts.core.initialdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class App {

    @SerializedName("cms_url")
    @Expose
    private String cmsUrl;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("version")
    @Expose
    private Integer version;
    @SerializedName("cms_ttl")
    @Expose
    private String cmsTtl;
    @SerializedName("moddate")
    @Expose
    private String moddate;
    @SerializedName("schema")
    @Expose
    private Integer schema;
    @SerializedName("about_filename")
    @Expose
    private String aboutFilename;
    @SerializedName("rotation_speed")
    @Expose
    private Integer rotationSpeed;
    @SerializedName("rotation_clickable")
    @Expose
    private Boolean rotationClickable;
    @SerializedName("rotation_fade")
    @Expose
    private Double rotationFade;
    @SerializedName("topper_overhang")
    @Expose
    private Integer topperOverhang;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("terms")
    @Expose
    private String terms;

    public String getCmsUrl() {
        return cmsUrl;
    }

    public void setCmsUrl(String cmsUrl) {
        this.cmsUrl = cmsUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCmsTtl() {
        return cmsTtl;
    }

    public void setCmsTtl(String cmsTtl) {
        this.cmsTtl = cmsTtl;
    }

    public String getModdate() {
        return moddate;
    }

    public void setModdate(String moddate) {
        this.moddate = moddate;
    }

    public Integer getSchema() {
        return schema;
    }

    public void setSchema(Integer schema) {
        this.schema = schema;
    }

    public String getAboutFilename() {
        return aboutFilename;
    }

    public void setAboutFilename(String aboutFilename) {
        this.aboutFilename = aboutFilename;
    }

    public Integer getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(Integer rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public Boolean getRotationClickable() {
        return rotationClickable;
    }

    public void setRotationClickable(Boolean rotationClickable) {
        this.rotationClickable = rotationClickable;
    }

    public Double getRotationFade() {
        return rotationFade;
    }

    public void setRotationFade(Double rotationFade) {
        this.rotationFade = rotationFade;
    }

    public Integer getTopperOverhang() {
        return topperOverhang;
    }

    public void setTopperOverhang(Integer topperOverhang) {
        this.topperOverhang = topperOverhang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

}
