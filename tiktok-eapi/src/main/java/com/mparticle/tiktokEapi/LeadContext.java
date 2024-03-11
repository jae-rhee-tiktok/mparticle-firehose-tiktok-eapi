package com.mparticle.tiktokEapi;

import com.google.gson.annotations.SerializedName;

public class LeadContext {
    @SerializedName("lead_id")
    public String leadId;

    @SerializedName("lead_event_source")
    public String leadEventSource;

    public LeadContext() {

    }
}
