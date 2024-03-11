package com.mparticle.tiktokEapi;

import com.google.gson.annotations.SerializedName;

public class PageContext {

    @SerializedName("url")
    public String url;

    @SerializedName("referrer")
    public String referrer;

    public PageContext() {

    }
}
