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

        Account accountInfo = event.getRequest().getAccount();
        eventPayload.setEventSource(accountInfo.getStringSetting(AccountSettings.SETTINGS_EVENT_SOURCE, true, "web"));
        eventPayload.setEventSourceId(accountInfo.getStringSetting(AccountSettings.SETTINGS_EVENT_SOURCE_ID, true, null));
        List<EventDataContext> dataArray = new ArrayList<>();

        EventDataContext eventData = new EventDataContext();
        eventData.setEvent(getTikTokEventName());
        eventData.setEventTime((int)event.getTimestamp());
        eventData.setEventId(event.getId().toString()); // TODO: check dual setup values
        eventData.setUser(userContextData);
        eventData.setProperties(propertiesContextData);

        if(eventPayload.getEventSource().equalsIgnoreCase("web")) {
            eventData.setPage(pageContextData);
        }

        if(eventPayload.getEventSource().equalsIgnoreCase("app")) {
            eventData.setApp(appContextData);
            eventData.setAd(adContextData);
        }

        if(eventPayload.getEventSource().equalsIgnoreCase("crm")) {
            eventData.setLead(leadContextData);
        }

        dataArray.add(eventData);
        eventPayload.setData(dataArray);

        Gson gson = new Gson();
        return gson.toJson(eventPayload);
    }
}
