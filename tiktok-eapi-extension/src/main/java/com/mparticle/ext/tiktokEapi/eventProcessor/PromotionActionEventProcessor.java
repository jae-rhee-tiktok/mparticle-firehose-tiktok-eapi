package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.AdData;
import com.mparticle.ext.tiktokEapi.utils.AppData;
import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.ext.tiktokEapi.utils.UserData;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.EventName;
import com.mparticle.sdk.model.eventprocessing.PromotionActionEvent;

public class PromotionActionEventProcessor extends EventProcessor {
    public PromotionActionEventProcessor(PromotionActionEvent event) {
        super(event);
        this.setPropertiesContextData(PropertiesData.buildPropertiesContextData(event));
        if (checkAppSource()) {
            this.setAppContextData(AppData.buildAppContextData(event));
            this.setAdContextData(AdData.buildAdContextData(event));
        }
        UserData.updateUserData(event, getUserContextData());
    }

    @Override
    public String getTikTokEventName() {
        assert getEvent() instanceof PromotionActionEvent;
        if (((PromotionActionEvent) getEvent()).getAction().equals(PromotionActionEvent.Action.CLICK)) {
            return EventName.Name.ClickButton.toString();
        }
        return EventName.Name.ViewContent.toString();
    }
}
