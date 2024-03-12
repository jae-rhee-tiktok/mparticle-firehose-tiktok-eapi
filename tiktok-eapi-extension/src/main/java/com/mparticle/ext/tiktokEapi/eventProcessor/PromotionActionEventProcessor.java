package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.sdk.model.eventprocessing.PromotionActionEvent;

public class PromotionActionEventProcessor extends EventProcessor {
    public PromotionActionEventProcessor(PromotionActionEvent event) {
        super(event);
        this.propertiesContextData = PropertiesData.buildPropertiesContextData(event);
    }
}
