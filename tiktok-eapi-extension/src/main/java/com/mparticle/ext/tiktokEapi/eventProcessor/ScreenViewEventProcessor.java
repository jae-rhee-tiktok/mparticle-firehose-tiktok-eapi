package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.sdk.model.eventprocessing.ScreenViewEvent;

public class ScreenViewEventProcessor extends EventProcessor {
    public ScreenViewEventProcessor(ScreenViewEvent event) {
        super(event);
        this.propertiesContextData = PropertiesData.buildPropertiesContextData(event);
    }
}
