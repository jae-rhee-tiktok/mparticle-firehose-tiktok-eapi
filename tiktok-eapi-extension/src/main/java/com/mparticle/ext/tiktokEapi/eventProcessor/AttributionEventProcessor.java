package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.AdData;
import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.EventName;
import com.mparticle.sdk.model.eventprocessing.AttributionEvent;

public class AttributionEventProcessor extends EventProcessor {
    public AttributionEventProcessor(AttributionEvent event) {
        super(event);
        this.setPropertiesContextData(PropertiesData.buildPropertiesContextData(event));
        setAdContextData(AdData.buildAdContextData(event));
    }

    @Override
    public String getTikTokEventName() {
        assert getEvent() instanceof AttributionEvent;
        AttributionEvent attributionEvent = ((AttributionEvent) getEvent());
        String eventName = attributionEvent.getName().toLowerCase();
        switch (eventName) {
            case "attribution":
                return EventName.AppName.InstallApp.toString(); // TODO: app events
            case "re-engagement":
                return EventName.AppName.LaunchAPP.toString(); // TODO: app events
            default:
                return captureTikTokEvent(eventName);
        }
    }

    @Override
    public String captureTikTokEvent(String eventName) {
        switch (eventName) {
            case "achieve_level":
            case "achievelevel":
                return EventName.AppName.AchieveLevel.toString();
            case "add_payment_info":
            case "addpaymentinfo":
                return EventName.AppName.AddPaymentInfo.toString();
            case "add_to_cart":
            case "addtocart":
                return EventName.AppName.AddToCart.toString();
            case "add_to_wishlist":
            case "addtowishlist":
                return EventName.AppName.AddToWishlist.toString();
            case "checkout":
                return EventName.AppName.Checkout.toString();
            case "complete_tutorial":
            case "completetutorial":
                return EventName.AppName.CompleteTutorial.toString();
            case "create_group":
            case "creategroup":
                return EventName.AppName.CreateGroup.toString();
            case "create_role":
            case "createrole":
                return EventName.AppName.CreateRole.toString();
            case "generate_lead":
            case "generatelead":
                return EventName.AppName.GenerateLead.toString();
            case "in_app_ad_click":
            case "inappadclick":
                return EventName.AppName.InAppADClick.toString();
            case "in_app_ad_impr":
            case "inappadimpr":
                return EventName.AppName.InAppAdImpr.toString();
            case "install_app":
            case "installapp":
                return EventName.AppName.InstallApp.toString();
            case "join_group":
            case "joingroup":
                return EventName.AppName.JoinGroup.toString();
            case "launch_app":
            case "launchapp":
                return EventName.AppName.LaunchAPP.toString();
            case "loan_application":
            case "loanapplication":
                return EventName.AppName.LoanApplication.toString();
            case "loan_approval":
            case "loanapproval":
                return EventName.AppName.LoanApproval.toString();
            case "loan_disbursal":
            case "loandisbursal":
                return EventName.AppName.LoanDisbursal.toString();
            case "login":
                return EventName.AppName.Login.toString();
            case "purchase":
                return EventName.AppName.Purchase.toString();
            case "rate":
                return EventName.AppName.Rate.toString();
            case "registration":
                return EventName.AppName.Registration.toString();
            case "search":
                return EventName.AppName.Search.toString();
            case "spend_credits":
            case "spendcredits":
                return EventName.AppName.SpendCredits.toString();
            case "start_trial":
            case "starttrial":
                return EventName.AppName.StartTrial.toString();
            case "subscribe":
                return EventName.AppName.Subscribe.toString();
            case "unlock_achievement":
            case "unlockachievement":
                return EventName.AppName.UnlockAchievement.toString();
            case "view_content":
            case "viewcontent":
                return EventName.AppName.ViewContent.toString();
            default:
                return eventName.toLowerCase();
        }
    }
}


