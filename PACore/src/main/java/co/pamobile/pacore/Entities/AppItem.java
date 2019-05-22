package co.pamobile.pacore.Entities;

import java.io.Serializable;

/**
 * Created by Dev04 on 9/29/2016.
 */

public class AppItem implements Serializable {

    private String name;
    private String icon;
    private String packageName;

    public AppItem(String name, String icon, String packageName) {
        this.name = name;
        this.icon = icon;
        this.packageName = packageName;
    }

    public AppItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
