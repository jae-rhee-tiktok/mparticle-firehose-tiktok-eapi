package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.*;
import com.mparticle.sdk.model.eventprocessing.CustomEvent;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.EventName;

import static com.mparticle.ext.tiktokEapi.utils.LeadData.buildLeadContextData;

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
            this.setLeadContextData(buildLeadContextData(event));
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

    private String captureTikTokEvent(String eventName) {
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



}
