package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.AccountSettings;
import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.ext.tiktokEapi.utils.TikTokApiClient;
import com.mparticle.sdk.model.eventprocessing.ProductActionEvent;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.EventName;

import java.io.IOException;

public class ProductActionEventProcessor extends EventProcessor {

    public ProductActionEventProcessor(ProductActionEvent event) {
        super(event);
        this.propertiesContextData = PropertiesData.buildPropertiesContextData(event);
    }

    @Override
    public String getTikTokEventName() {
        assert event instanceof ProductActionEvent;
        ProductActionEvent.Action action = ((ProductActionEvent)event).getAction();
        switch (action) {
            case CLICK:
                return EventName.Name.ClickButton.toString();
            case REFUND: // TODO: find default mappings
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
            case CHECKOUT_OPTION: // TODO: find default mappings
            case REMOVE_FROM_CART: // TODO: find default mappings
            case REMOVE_FROM_WISH_LIST: // TODO: find default mappings
            default:
                return action.toString();
        }
    }

    private void processAddToCartEvent() {

    }

    private void processAddToWishListEvent() {

    }

    private void processCheckoutEvent() {

    }

    private void processCheckoutOptionEvent() {

    }

    private void processClickEvent() {

    }

    private void processPurchaseEvent() {

    }

    private void processRefundEvent() {

    }

    private void processRemoveFromCartEvent() {

    }

    private void processRemoveFromWishListEvent() {

    }

    private void processViewDetailEvent() {

    }
}
