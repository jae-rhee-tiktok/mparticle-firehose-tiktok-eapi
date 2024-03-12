package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.ext.tiktokEapi.utils.UserData;
import com.mparticle.sdk.model.eventprocessing.ApplicationStateTransitionEvent;

public class ApplicationStateTransitionEventProcessor extends EventProcessor {
    public ApplicationStateTransitionEventProcessor(ApplicationStateTransitionEvent event) {
        super(event);
        this.propertiesContextData = PropertiesData.buildPropertiesContextData(event);
    }
}