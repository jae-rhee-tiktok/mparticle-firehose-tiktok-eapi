package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.ext.tiktokEapi.utils.UserData;
import com.mparticle.sdk.model.eventprocessing.ImpressionEvent;

public class ImpressionEventProcessor extends EventProcessor {
    public ImpressionEventProcessor(ImpressionEvent event) {
        super(event);
        this.propertiesContextData = PropertiesData.buildPropertiesContextData(event);
    }
}
