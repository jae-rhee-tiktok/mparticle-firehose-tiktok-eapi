package com.mparticle.ext.tiktokEapi.utils.tiktokApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventContext {

    @SerializedName("event_source")
    private String eventSource;

    @SerializedName("event_source_id")
    private String eventSourceId;

    @SerializedName("partner_name")
    private final String partnerName = "MParticle";

    @SerializedName("data")
    private List<EventDataContext> data;

    public EventContext() {

    }

    public String getPartnerName() {
        return partnerName;
    }

    public String getEventSource() {
        return eventSource;
    }

    public void setEventSource(String eventSource) {
        this.eventSource = eventSource;
    }

    public String getEventSourceId() {
        return eventSourceId;
    }

    public void setEventSourceId(String eventSourceId) {
        this.eventSourceId = eventSourceId;
    }

    public List<EventDataContext> getData() {
        return data;
    }

    public void setData(List<EventDataContext> data) {
        this.data = data;
    }
}
