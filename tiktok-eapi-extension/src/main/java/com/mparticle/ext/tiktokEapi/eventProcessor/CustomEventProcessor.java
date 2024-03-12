package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.sdk.model.eventprocessing.CustomEvent;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.EventName;

public class CustomEventProcessor extends EventProcessor {

    public CustomEventProcessor(CustomEvent event) {
        super(event);
        this.propertiesContextData = PropertiesData.buildPropertiesContextData(event);
    }

    @Override
    public String getTikTokEventName() {
        assert event instanceof CustomEvent;
        CustomEvent customEvent = ((CustomEvent)event);
        String eventName = customEvent.getName().toLowerCase();
        switch (customEvent.getCustomType()) { // TODO: find default mappings
//            case MEDIA: // TODO: find default mappings
            case SEARCH:
                return EventName.Name.Search.toString();
//            case SOCIAL: // TODO: find default mappings
            case UNKNOWN: // TODO: find default mappings
                switch (eventName) {
                    case "add_payment_info":
                        return EventName.Name.AddPaymentInfo.toString();
                    case "complete_registration":
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
            case OTHER: // TODO: find default mappings
                switch (eventName) {
                    case "add_payment_info":
                        return EventName.Name.AddPaymentInfo.toString();
                    case "complete_registration":
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
//            case LOCATION: // TODO: find default mappings
//            case NAVIGATION: // TODO: find default mappings
//            case TRANSACTION: // TODO: find default mappings
//            case USER_CONTENT: // TODO: find default mappings
//            case USER_PREFERENCE: // TODO: find default mappings
            default:
                return eventName;

        }
    }

}
