package co.pamobile.pacore.Dialog.Exit;

import java.io.Serializable;

public class FeatureBanner implements Serializable {
    private String url;
    private String destination;
    private String type;

    public FeatureBanner(String url, String destination, String type) {
        this.url = url;
        this.destination = destination;
        this.type = type;
    }

    public FeatureBanner(){

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
