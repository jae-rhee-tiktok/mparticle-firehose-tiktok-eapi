package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mparticle.ext.tiktokEapi.utils.AccountSettings;
import com.mparticle.ext.tiktokEapi.utils.PageData;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.EventContext;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.EventDataContext;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.EventName;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.registration.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class PageViewEventProcessor extends EventProcessor {

    private Logger logger = LogManager.getLogger(PageViewEventProcessor.class);

    public PageViewEventProcessor(CustomEvent event) {
        super(event);
        PageData.buildPageContextData(event);
    }

    public PageViewEventProcessor(ProductActionEvent event) {
        super(event);
        PageData.buildPageContextData(event);
    }

    public PageViewEventProcessor(ImpressionEvent event) {
        super(event);
        PageData.buildPageContextData(event);
    }

    public PageViewEventProcessor(ScreenViewEvent event) {
        super(event);
        this.setPageContextData(PageData.buildPageContextData(event));
    }

    public PageViewEventProcessor(Event event) {
        super(event);
    }

    public PageViewEventProcessor(EventProcessingRequest request) {
        super(request.getEvents().get(0)); // TODO: check for array of events
    }

    @Override
    public String getTikTokEventName() {
        return EventName.Name.PageView.toString();
    }

    @Override
    public String buildEventPayload() {

        EventContext eventPayload = new EventContext();

        Account accountInfo = getEvent().getRequest().getAccount();
        eventPayload.setEventSource(accountInfo.getStringSetting(AccountSettings.SETTINGS_EVENT_SOURCE, true, "web"));
        eventPayload.setEventSourceId(accountInfo.getStringSetting(AccountSettings.SETTINGS_EVENT_SOURCE_ID, true, null));
        List<EventDataContext> dataArray = new ArrayList<>();

        EventDataContext eventData = new EventDataContext();
        eventData.setEvent(getTikTokEventName());
        eventData.setEventTime((int) getEvent().getTimestamp());
        eventData.setEventId(getEvent().getId().toString()); // TODO: check dual setup values
//        eventData.setUser(userContextData);
//        eventData.setProperties(propertiesContextData);

        if(eventPayload.getEventSource().equalsIgnoreCase("web")) {
            eventData.setPage(getPageContextData());
        }

//        if(eventPayload.getEventSource().equalsIgnoreCase("app")) {
//            eventData.setApp(appContextData);
//            eventData.setAd(adContextData);
//        }
//
//        if(eventPayload.getEventSource().equalsIgnoreCase("crm")) {
//            eventData.setLead(leadContextData);
//        }

        dataArray.add(eventData);
        eventPayload.setData(dataArray);

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String res = gson.toJson(eventPayload);

        logger.info("PageView payload: " + res);

        return res;
    }
}
