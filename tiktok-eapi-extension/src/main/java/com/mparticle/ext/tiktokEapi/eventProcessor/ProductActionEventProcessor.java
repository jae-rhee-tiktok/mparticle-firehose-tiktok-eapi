package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.ext.tiktokEapi.utils.UserData;
import com.mparticle.sdk.model.eventprocessing.ProductActionEvent;
import com.mparticle.tiktokEapi.PropertiesContext;

public class ProductActionEventProcessor extends EventProcessor {

    public ProductActionEventProcessor(ProductActionEvent event) {
        this.event = event;
        this.userContextData = UserData.buildUserContextData(event);
        this.propertiesContextData = PropertiesData.buildPropertiesContextData(event);
    }

    @Override
    public void sendTikTokEvent() {

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
