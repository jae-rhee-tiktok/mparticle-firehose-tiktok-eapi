package com.mparticle.ext.tiktokEapi.utils;

import com.mparticle.ext.tiktokEapi.utils.tiktokApi.LeadContext;
import com.mparticle.sdk.model.eventprocessing.CustomEvent;

import java.util.Map;

import static com.mparticle.ext.tiktokEapi.utils.Helper.getAttributeOrEmptyString;

public class LeadData {

    private static void setLeadContextData(Map<String, String> eventAttributes, LeadContext leadContext) {
        leadContext.setLeadId(getAttributeOrEmptyString("lead_id", eventAttributes));
        leadContext.setLeadEventSource(getAttributeOrEmptyString("lead_event_source", eventAttributes));
    }

    public static LeadContext buildLeadContextData(CustomEvent event) {
        Map<String, String> eventAttributes = event.getAttributes();
        LeadContext leadContext = new LeadContext();
        setLeadContextData(eventAttributes, leadContext);
        return leadContext;
    }
}
