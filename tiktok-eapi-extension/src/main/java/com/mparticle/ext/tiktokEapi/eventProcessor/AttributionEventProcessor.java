package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.ext.tiktokEapi.utils.UserData;
import com.mparticle.sdk.model.eventprocessing.AttributionEvent;

public class AttributionEventProcessor extends EventProcessor {
    public AttributionEventProcessor(AttributionEvent event) {
        super(event);
        this.propertiesContextData = PropertiesData.buildPropertiesContextData(event);
    }
}
