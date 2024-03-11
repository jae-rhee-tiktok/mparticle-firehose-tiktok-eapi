package com.mparticle.tiktokEapi;

import com.google.gson.annotations.SerializedName;

public class EventContext {

    @SerializedName("event_source")
    public String eventSource;

    @SerializedName("event_source_id")
    public String eventSourceId;

    @SerializedName("partner_name")
    public static final String partnerName = "MParticle";

    @SerializedName("data")
    public String data;

    public EventContext() {

    }
}
