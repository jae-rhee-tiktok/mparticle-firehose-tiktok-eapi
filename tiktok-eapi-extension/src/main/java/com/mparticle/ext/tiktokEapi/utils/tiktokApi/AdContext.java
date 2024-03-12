package com.mparticle.ext.tiktokEapi.utils.tiktokApi;

import com.google.gson.annotations.SerializedName;

public class AdContext {

    @SerializedName("callback")
    private String callback;

    @SerializedName("campaign_id")
    private String campaignId;

    @SerializedName("ad_id")
    private String adId;

    @SerializedName("creative_id")
    private String creativeId;

    @SerializedName("is_retargeting")
    private boolean isRetargeting;

    @SerializedName("attributed")
    private boolean attributed;

    @SerializedName("attribution_type")
    private String attributionType;

    @SerializedName("attribution_provider")
    private String attributionProvider;

    public AdContext() {

    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(String creativeId) {
        this.creativeId = creativeId;
    }

    public boolean getIsRetargeting() {
        return isRetargeting;
    }

    public void setIsRetargeting(boolean isRetargeting) {
        this.isRetargeting = isRetargeting;
    }

    public boolean getAttributed() {
        return attributed;
    }

    public void setAttributed(boolean attributed) {
        this.attributed = attributed;
    }

    public String getAttributionType() {
        return attributionType;
    }

    public void setAttributionType(String attributionType) {
        this.attributionType = attributionType;
    }

    public String getAttributionProvider() {
        return attributionProvider;
    }

    public void setAttributionProvider(String attributionProvider) {
        this.attributionProvider = attributionProvider;
    }
}
