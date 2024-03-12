package com.mparticle.ext.tiktokEapi.utils.tiktokApi;

import com.google.gson.annotations.SerializedName;

public class AppContext {
    @SerializedName("app_id")
    public String appId;

    @SerializedName("app_name")
    public String appName;

    @SerializedName("app_version")
    public String appVersion;

    public AppContext() {

    }
}
