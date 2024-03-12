package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.sdk.model.eventprocessing.PushSubscriptionEvent;

public class PushSubscriptionEventProcessor extends EventProcessor {
    public PushSubscriptionEventProcessor(PushSubscriptionEvent event) {
        super(event);
        this.propertiesContextData = PropertiesData.buildPropertiesContextData(event);
    }
}
