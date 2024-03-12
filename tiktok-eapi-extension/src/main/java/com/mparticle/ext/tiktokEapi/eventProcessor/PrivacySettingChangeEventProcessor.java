package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.sdk.model.eventprocessing.PrivacySettingChangeEvent;

public class PrivacySettingChangeEventProcessor extends EventProcessor {
    public PrivacySettingChangeEventProcessor(PrivacySettingChangeEvent event) {
        super(event);
        this.propertiesContextData = PropertiesData.buildPropertiesContextData(event);
    }
}
