package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.PageData;
import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.EventName;
import com.mparticle.sdk.model.eventprocessing.ScreenViewEvent;

public class ScreenViewEventProcessor extends EventProcessor {
    public ScreenViewEventProcessor(ScreenViewEvent event) {
        super(event);
        this.setPageContextData(PageData.buildPageContextData(event));
        this.setPropertiesContextData(PropertiesData.buildPropertiesContextData(event));
    }

    @Override
    public String getTikTokEventName() {
        return EventName.Name.PageView.toString();
    }
}
