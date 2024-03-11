package com.mparticle.tiktokEapi;

import com.google.gson.annotations.SerializedName;

public class AdContext {

    @SerializedName("callback")
    public String callback;

    @SerializedName("campaign_id")
    public String campaignId;

    @SerializedName("ad_id")
    public String adId;

    @SerializedName("creative_id")
    public String creativeId;

    @SerializedName("is_retargeting")
    public String isRetargeting;

    @SerializedName("attributed")
    public String attributed;

    @SerializedName("attribution_type")
    public String attributionType;

    @SerializedName("attribution_provider")
    public String attributionProvider;

    public AdContext() {

    }
}
