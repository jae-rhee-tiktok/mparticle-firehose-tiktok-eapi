package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.ext.tiktokEapi.utils.UserData;
import com.mparticle.sdk.model.eventprocessing.ErrorEvent;

public class ErrorEventProcessor extends EventProcessor {
    public ErrorEventProcessor(ErrorEvent event) {
        super(event);
        this.propertiesContextData = PropertiesData.buildPropertiesContextData(event);
    }
}
