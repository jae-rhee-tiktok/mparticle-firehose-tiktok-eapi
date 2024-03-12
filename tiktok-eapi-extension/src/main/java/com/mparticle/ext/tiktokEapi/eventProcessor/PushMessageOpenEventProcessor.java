package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.sdk.model.eventprocessing.PushMessageOpenEvent;

public class PushMessageOpenEventProcessor extends EventProcessor {
    public PushMessageOpenEventProcessor(PushMessageOpenEvent event) {
        super(event);
        this.propertiesContextData = PropertiesData.buildPropertiesContextData(event);
    }
}
