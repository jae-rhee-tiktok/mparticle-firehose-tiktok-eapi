package com.mparticle.ext.tiktokEapi.utils.tiktokApi;

import com.google.gson.annotations.SerializedName;

public class LeadContext {
    @SerializedName("lead_id")
    private String leadId;

    @SerializedName("lead_event_source")
    private String leadEventSource;

    public LeadContext() {

    }

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }

    public String getLeadEventSource() {
        return leadEventSource;
    }

    public void setLeadEventSource(String leadEventSource) {
        this.leadEventSource = leadEventSource;
    }
}
