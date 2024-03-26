package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mparticle.ext.tiktokEapi.utils.AccountSettings;
import com.mparticle.ext.tiktokEapi.utils.TikTokApiClient;
import com.mparticle.ext.tiktokEapi.utils.UserData;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.*;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.eventprocessing.consent.CCPAConsent;
import com.mparticle.sdk.model.eventprocessing.consent.GDPRConsent;
import com.mparticle.sdk.model.registration.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mparticle.sdk.model.eventprocessing.consent.ConsentState.DEFAULT_CCPA_CONSENT_PURPOSE;

public class EventProcessor {

    private Logger logger = LogManager.getLogger(EventProcessor.class);
    private Event event;
    private UserContext userContextData;
    private PropertiesContext propertiesContextData;
    private PageContext pageContextData;
    private AppContext appContextData;
    private AdContext adContextData;
    private LeadContext leadContextData;

    public EventProcessor(Event event) {
        this.setEvent(event);
        this.setUserContextData(UserData.buildUserContextData(event));
    }

    public void execute() throws IOException {
        if (!checkGDPRParental()) {
            logger.info("GDPR Parental Consent: false provided");
            return;
        }
        if (!checkCCPA()) {
            logger.info("CCPA Consent: false provided");
            return;
        }

        try {
            TikTokApiClient tikTokApiClient = new TikTokApiClient();
            tikTokApiClient.sendPostRequest(
                    getEvent().getRequest().getAccount().getStringSetting(AccountSettings.SETTINGS_ACCESS_TOKEN, true, null),
                    buildEventPayload(),
                    0
            );
        } catch (IOException e) {
            logger.error("execute error msg: ", e);
            throw new IOException(e);
        }

    }

    public boolean checkGDPRLocation() {
        try {
            return getEvent().getRequest().getConsentState().getGDPR().get("location_collection").isConsented();
        } catch (NullPointerException e) {
            return true;
        }
    }

    public boolean checkGDPRParental() {
        try {
            return getEvent().getRequest().getConsentState().getGDPR().get("parental").isConsented();
        } catch (NullPointerException e) {
            return true;
        }
    }

    public boolean checkCCPA() {
        try {
            return getEvent().getRequest().getConsentState().getCCPA().get("data_sale_opt_out").isConsented();
        } catch (NullPointerException e) {
            return true;
        }
    }

    public boolean checkWebSource() {
        String eventSource = getEvent().getRequest().getAccount().getStringSetting(AccountSettings.SETTINGS_EVENT_SOURCE, true, null);
        return eventSource.equalsIgnoreCase("web");
    }

    public boolean checkOfflineSource() {
        String eventSource = getEvent().getRequest().getAccount().getStringSetting(AccountSettings.SETTINGS_EVENT_SOURCE, true, null);
        return eventSource.equalsIgnoreCase("offline");
    }

    public boolean checkAppSource() {
        String eventSource = getEvent().getRequest().getAccount().getStringSetting(AccountSettings.SETTINGS_EVENT_SOURCE, true, null);
        return eventSource.equalsIgnoreCase("app");
    }

    public boolean checkCrmSource() {
        String eventSource = getEvent().getRequest().getAccount().getStringSetting(AccountSettings.SETTINGS_EVENT_SOURCE, true, null);
        return eventSource.equalsIgnoreCase("crm");
    }

    public String getTikTokEventName() {
        return getEvent().getType().toString();
    }

    public String buildEventPayload() {

        EventContext eventPayload = new EventContext();

        Account accountInfo = getEvent().getRequest().getAccount();
        eventPayload.setEventSource(accountInfo.getStringSetting(AccountSettings.SETTINGS_EVENT_SOURCE, true, "web"));
        eventPayload.setEventSourceId(accountInfo.getStringSetting(AccountSettings.SETTINGS_EVENT_SOURCE_ID, true, null));
        List<EventDataContext> dataArray = new ArrayList<>();

        EventDataContext eventData = new EventDataContext();
        eventData.setEvent(getTikTokEventName());
        eventData.setEventTime((int)getEvent().getTimestamp());
        eventData.setEventId(getEvent().getId().toString()); // TODO: check dual setup values
        eventData.setUser(getUserContextData());
        eventData.setProperties(getPropertiesContextData());

        if(checkWebSource()) {
            eventData.setPage(getPageContextData());
        }

        if(checkAppSource()) {
            eventData.setApp(getAppContextData());
            eventData.setAd(getAdContextData());
        }

        if(checkCrmSource()) {
            eventData.setLead(getLeadContextData());
        }

        eventData.setLdu(getEvent().getRequest().getAccount().getBooleanSetting(AccountSettings.SETTINGS_LDU, false, false));

        dataArray.add(eventData);
        eventPayload.setData(dataArray);

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(eventPayload);
    }

    String captureTikTokEvent(String eventName) {
        switch (eventName) {
            case "add_payment_info":
                return EventName.Name.AddPaymentInfo.toString();
            case "add_to_cart":
                return EventName.Name.AddToCart.toString();
            case "add_to_wishlist":
                return EventName.Name.AddToWishlist.toString();
            case "click":
            case "click_button":
                return EventName.Name.ClickButton.toString();
            case "purchase":
            case "complete_payment":
                return EventName.Name.CompletePayment.toString();
            case "initiate_checkout":
                return EventName.Name.InitiateCheckout.toString();
            case "view_content":
                return EventName.Name.ViewContent.toString();
            case "page_view":
                return EventName.Name.PageView.toString();
            case "complete_registration":
            case "account_creation":
                return EventName.Name.CompleteRegistration.toString();
            case "contact":
                return EventName.Name.Contact.toString();
            case "download":
                return EventName.Name.Download.toString();
            case "place_an_order":
                return EventName.Name.PlaceAnOrder.toString();
            case "submit_form":
                return EventName.Name.SubmitForm.toString();
            case "subscribe":
                return EventName.Name.Subscribe.toString();
        }
        return eventName;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public UserContext getUserContextData() {
        return userContextData;
    }

    public void setUserContextData(UserContext userContextData) {
        this.userContextData = userContextData;
    }

    public PropertiesContext getPropertiesContextData() {
        return propertiesContextData;
    }

    public void setPropertiesContextData(PropertiesContext propertiesContextData) {
        this.propertiesContextData = propertiesContextData;
    }

    public PageContext getPageContextData() {
        return pageContextData;
    }

    public void setPageContextData(PageContext pageContextData) {
        this.pageContextData = pageContextData;
    }

    public AppContext getAppContextData() {
        return appContextData;
    }

    public void setAppContextData(AppContext appContextData) {
        this.appContextData = appContextData;
    }

    public AdContext getAdContextData() {
        return adContextData;
    }

    public void setAdContextData(AdContext adContextData) {
        this.adContextData = adContextData;
    }

    public LeadContext getLeadContextData() {
        return leadContextData;
    }

    public void setLeadContextData(LeadContext leadContextData) {
        this.leadContextData = leadContextData;
    }
}
