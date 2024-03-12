package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.*;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.EventName;
import com.mparticle.sdk.model.eventprocessing.ImpressionEvent;

public class ImpressionEventProcessor extends EventProcessor {
    public ImpressionEventProcessor(ImpressionEvent event) {
        super(event);
        this.setPropertiesContextData(PropertiesData.buildPropertiesContextData(event));
        this.setPageContextData(PageData.buildPageContextData(event));

        if (checkAppSource()) {
            this.setAppContextData(AppData.buildAppContextData(event));
            this.setAdContextData(AdData.buildAdContextData(event));
        }

        UserData.updateUserData(event, getUserContextData());
    }

    @Override
    public String getTikTokEventName() {
        return EventName.Name.ViewContent.toString();
    }
}
