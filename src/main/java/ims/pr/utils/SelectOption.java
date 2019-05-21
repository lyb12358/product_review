package ims.pr.utils;

import java.io.Serializable;

public class SelectOption implements Serializable {

    private static final long serialVersionUID = 1469718220350429747L;
    private String label;
    private Integer value;
    private Integer parentId;
    private String sublabel;
    private String image;
    private String avatar;
    private String icon;
    private Byte status;
    private Boolean isSync;
    private Boolean isDel;
    private Integer orderId;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSublabel() {
        return sublabel;
    }

    public void setSublabel(String sublabel) {
        this.sublabel = sublabel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
