package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.sdk.model.eventprocessing.UserIdentityChangeEvent;

public class UserIdentityChangeEventProcessor extends EventProcessor {
    public UserIdentityChangeEventProcessor(UserIdentityChangeEvent event) {
        super(event);
        this.setPropertiesContextData(PropertiesData.buildPropertiesContextData(event));
    }
}
