package com.mparticle.tiktokEapi;

import com.google.gson.annotations.SerializedName;

public class EventDataContext {

    @SerializedName("event")
    public String eventSource;

    @SerializedName("event_time")
    public String eventSourceId;

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
