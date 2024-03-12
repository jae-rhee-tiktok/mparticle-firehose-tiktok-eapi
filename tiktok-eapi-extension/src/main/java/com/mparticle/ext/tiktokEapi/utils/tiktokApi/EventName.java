package com.mparticle.ext.tiktokEapi.utils.tiktokApi;

import com.mparticle.sdk.model.eventprocessing.CustomEvent;
import com.mparticle.sdk.model.eventprocessing.Event;
import com.mparticle.sdk.model.eventprocessing.ProductActionEvent;

public class EventName {
    public static enum Name {

        PageView,
        AddPaymentInfo,
        AddToCart,
        AddToWishlist,
        ClickButton,
        CompletePayment,
        CompleteRegistration,
        Contact,
        Download,
        InitiateCheckout,
        PlaceAnOrder,
        Search,
        SubmitForm,
        Subscribe,
        ViewContent;

        private Name() {
        }

        public String toString() {
            return this.name();
        }


    }
}
