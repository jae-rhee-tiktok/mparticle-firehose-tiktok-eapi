package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.google.gson.Gson;
import com.mparticle.ext.tiktokEapi.utils.AccountSettings;
import com.mparticle.ext.tiktokEapi.utils.TikTokApiClient;
import com.mparticle.ext.tiktokEapi.utils.UserData;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.*;
import com.mparticle.sdk.model.eventprocessing.Event;
import com.mparticle.sdk.model.registration.Account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventProcessor {
    public Event event;
    public UserContext userContextData;
    public PropertiesContext propertiesContextData;
    public PageContext pageContextData;
    public AppContext appContextData;
    public AdContext adContextData;
    public LeadContext leadContextData;

    public EventProcessor(Event event) {
        this.event = event;
        this.userContextData = UserData.buildUserContextData(event);
    }

    public void execute() {
        try {
            TikTokApiClient tikTokApiClient = new TikTokApiClient();
            tikTokApiClient.sendRequest(
                    event.getRequest().getAccount().getStringSetting(AccountSettings.SETTINGS_ACCESS_TOKEN, true, null),
                    buildEventPayload()
            );
        } catch (IOException e) {
            throw new RuntimeException(e); // TODO: update catch logic & log
        }

    }

    public String getTikTokEventName() {
        return event.getType().toString();
    }

    public String buildEventPayload() {
        EventContext eventPayload = new EventContext();

        Account acc = event.getRequest().getAccount();
        eventPayload.eventSource = acc.getStringSetting(AccountSettings.SETTINGS_EVENT_SOURCE, true, "web");
        eventPayload.eventSourceId = acc.getStringSetting(AccountSettings.SETTINGS_EVENT_SOURCE_ID, true, null);

        List<EventDataContext> eventDataArray = new ArrayList<>();
        EventDataContext eventData = new EventDataContext();
        eventData.event = getTikTokEventName();
        eventData.eventTime = (int)event.getTimestamp();
        eventData.eventId = event.getId().toString(); // TODO: check dual setup values
        eventData.user = userContextData;
        eventData.properties = propertiesContextData;

        if(eventPayload.eventSource.equalsIgnoreCase("web")) {
            eventData.page = pageContextData;
        }

        if(eventPayload.eventSource.equalsIgnoreCase("app")) {
            eventData.app = appContextData;
            eventData.ad = adContextData;
        }

        if(eventPayload.eventSource.equalsIgnoreCase("crm")) {
            eventData.lead = leadContextData;
        }

        Gson gson = new Gson();
        return gson.toJson(eventPayload);
    }
}
