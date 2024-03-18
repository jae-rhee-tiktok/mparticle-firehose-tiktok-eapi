package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.*;
import com.mparticle.sdk.model.eventprocessing.CustomEvent;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.EventName;

public class CustomEventProcessor extends EventProcessor {

    public CustomEventProcessor(CustomEvent event) {
        super(event);
        this.setPropertiesContextData(PropertiesData.buildPropertiesContextData(event));
        this.setPageContextData(PageData.buildPageContextData(event));

        if (checkAppSource()) {
            this.setAppContextData(AppData.buildAppContextData(event));
            this.setAdContextData(AdData.buildAdContextData(event));
        }

        if (checkCrmSource()) {
            this.setLeadContextData(LeadData.buildLeadContextData(event));
        }

        UserData.updateUserData(event, getUserContextData());
    }

    @Override
    public String getTikTokEventName() {
        assert getEvent() instanceof CustomEvent;
        CustomEvent customEvent = ((CustomEvent) getEvent());
        String eventName = customEvent.getName().toLowerCase();
        switch (customEvent.getCustomType()) {
            case MEDIA:
                return EventName.Name.ViewContent.toString();
            case SEARCH:
                return EventName.Name.Search.toString();
            case SOCIAL:
                return EventName.Name.Contact.toString();
            case UNKNOWN:
            case OTHER:
                return captureTikTokEvent(eventName);
//            case LOCATION: // TODO: find default mappings - oEAPI?
            case NAVIGATION:
                return EventName.Name.ClickButton.toString();
            case TRANSACTION:
                return EventName.Name.PlaceAnOrder.toString();
            case USER_CONTENT:
                return EventName.Name.SubmitForm.toString();
            case USER_PREFERENCE:
                return EventName.Name.CompleteRegistration.toString();
            default:
                return eventName;
        }
    }
}
