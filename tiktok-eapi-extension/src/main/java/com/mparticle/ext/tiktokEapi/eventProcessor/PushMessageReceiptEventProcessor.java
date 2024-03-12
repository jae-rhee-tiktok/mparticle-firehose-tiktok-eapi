package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.sdk.model.eventprocessing.PushMessageReceiptEvent;

public class PushMessageReceiptEventProcessor extends EventProcessor {
    public PushMessageReceiptEventProcessor(PushMessageReceiptEvent event) {
        super(event);
        this.propertiesContextData = PropertiesData.buildPropertiesContextData(event);
    }
}
