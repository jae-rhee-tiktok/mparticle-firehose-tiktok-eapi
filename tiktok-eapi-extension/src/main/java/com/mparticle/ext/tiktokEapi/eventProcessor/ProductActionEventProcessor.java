package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.*;
import com.mparticle.sdk.model.eventprocessing.ProductActionEvent;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.EventName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.mparticle.sdk.model.eventprocessing.consent.ConsentState.DEFAULT_CCPA_CONSENT_PURPOSE;

public class ProductActionEventProcessor extends EventProcessor {

    private Logger logger = LogManager.getLogger(ProductActionEventProcessor.class);

    public ProductActionEventProcessor(ProductActionEvent event) {
        super(event);
        this.setPropertiesContextData(PropertiesData.buildPropertiesContextData(event));
        this.setPageContextData(PageData.buildPageContextData(event));
        if (checkAppSource()) {
            this.setAppContextData(AppData.buildAppContextData(event));
            this.setAdContextData(AdData.buildAdContextData(event));
        }
        UserData.updateUserData(event, getUserContextData());
    }

    @Override
    public String getTikTokEventName() {
        assert getEvent() instanceof ProductActionEvent;
        ProductActionEvent.Action action = ((ProductActionEvent) getEvent()).getAction();
        switch (action) {
            case CLICK:
                return EventName.Name.ClickButton.toString();
            case CHECKOUT:
                return EventName.Name.InitiateCheckout.toString();
            case PURCHASE:
                return EventName.Name.CompletePayment.toString();
            case ADD_TO_CART:
                return EventName.Name.AddToCart.toString();
            case VIEW_DETAIL:
                return EventName.Name.ViewContent.toString();
            case ADD_TO_WISHLIST:
                return EventName.Name.AddToWishlist.toString();
            default:
                return action.toString();
        }
    }
}
