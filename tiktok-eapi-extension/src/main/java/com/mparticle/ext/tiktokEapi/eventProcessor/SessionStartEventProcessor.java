package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.sdk.model.eventprocessing.SessionStartEvent;

public class SessionStartEventProcessor extends EventProcessor {
    public SessionStartEventProcessor(SessionStartEvent event) {
        super(event);
        this.setPropertiesContextData(PropertiesData.buildPropertiesContextData(event));
    }
}
