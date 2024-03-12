package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.sdk.model.eventprocessing.UserAttributeChangeEvent;

public class UserAttributeChangeEventProcessor extends EventProcessor {
    public UserAttributeChangeEventProcessor(UserAttributeChangeEvent event) {
        super(event);
        this.setPropertiesContextData(PropertiesData.buildPropertiesContextData(event));
    }
}
