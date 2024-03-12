package com.mparticle.ext.tiktokEapi.utils.tiktokApi;

import com.google.gson.annotations.SerializedName;

public class AppContext {
    @SerializedName("app_id")
    private String appId;

    @SerializedName("app_name")
    private String appName;

    @SerializedName("app_version")
    private String appVersion;

    public AppContext() {

    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
