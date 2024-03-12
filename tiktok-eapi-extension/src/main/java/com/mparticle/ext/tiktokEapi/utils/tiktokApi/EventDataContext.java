package com.mparticle.ext.tiktokEapi.utils.tiktokApi;

import com.google.gson.annotations.SerializedName;

public class EventDataContext {

    @SerializedName("event")
    private String event;

    @SerializedName("event_time")
    private int eventTime;

    @SerializedName("event_id")
    private String eventId;

    @SerializedName("user")
    private UserContext user;

    @SerializedName("properties")
    private PropertiesContext properties;

    @SerializedName("page")
    private PageContext page;

    @SerializedName("app")
    private AppContext app;

    @SerializedName("ad")
    private AdContext ad;

    @SerializedName("limited_data_use")
    private boolean ldu;

    @SerializedName("lead")
    private LeadContext lead;

    public EventDataContext() {

    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getEventTime() {
        return eventTime;
    }

    public void setEventTime(int eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public UserContext getUser() {
        return user;
    }

    public void setUser(UserContext user) {
        this.user = user;
    }

    public PropertiesContext getProperties() {
        return properties;
    }

    public void setProperties(PropertiesContext properties) {
        this.properties = properties;
    }

    public PageContext getPage() {
        return page;
    }

    public void setPage(PageContext page) {
        this.page = page;
    }

    public AppContext getApp() {
        return app;
    }

    public void setApp(AppContext app) {
        this.app = app;
    }

    public AdContext getAd() {
        return ad;
    }

    public void setAd(AdContext ad) {
        this.ad = ad;
    }

    public boolean isLdu() {
        return ldu;
    }

    public void setLdu(boolean ldu) {
        this.ldu = ldu;
    }

    public LeadContext getLead() {
        return lead;
    }

    public void setLead(LeadContext lead) {
        this.lead = lead;
    }
}
