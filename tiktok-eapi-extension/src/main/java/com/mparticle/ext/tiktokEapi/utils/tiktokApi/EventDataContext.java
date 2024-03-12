package com.mparticle.ext.tiktokEapi.utils.tiktokApi;

import com.google.gson.annotations.SerializedName;

public class EventDataContext {

    @SerializedName("event")
    public String event;

    @SerializedName("event_time")
    public int eventTime;

    @SerializedName("event_id")
    public String eventId;

    @SerializedName("user")
    public UserContext user;

    @SerializedName("properties")
    public PropertiesContext properties;

    @SerializedName("page")
    public PageContext page;

    @SerializedName("app")
    public AppContext app;

    @SerializedName("ad")
    public AdContext ad;

    @SerializedName("limited_data_use")
    public boolean ldu;

    @SerializedName("lead")
    public LeadContext lead;

    public EventDataContext() {

    }

}
