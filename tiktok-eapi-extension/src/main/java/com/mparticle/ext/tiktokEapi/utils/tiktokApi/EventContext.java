package com.mparticle.ext.tiktokEapi.utils.tiktokApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventContext {

    @SerializedName("event_source")
    public String eventSource;

    @SerializedName("event_source_id")
    public String eventSourceId;

    @SerializedName("partner_name")
    public static final String partnerName = "MParticle";

    @SerializedName("data")
    public List<EventDataContext> data;

    public EventContext() {

    }
}
