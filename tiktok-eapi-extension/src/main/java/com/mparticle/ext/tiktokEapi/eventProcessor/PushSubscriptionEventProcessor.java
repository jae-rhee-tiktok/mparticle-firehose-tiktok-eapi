package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.AppData;
import com.mparticle.ext.tiktokEapi.utils.PageData;
import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.ext.tiktokEapi.utils.UserData;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.EventName;
import com.mparticle.sdk.model.eventprocessing.PushSubscriptionEvent;

import static com.mparticle.sdk.model.eventprocessing.PushSubscriptionEvent.Action.SUBSCRIBE;

public class PushSubscriptionEventProcessor extends EventProcessor {
    public PushSubscriptionEventProcessor(PushSubscriptionEvent event) {
        super(event);
        this.setPropertiesContextData(PropertiesData.buildPropertiesContextData(event));
    }

    @Override
    public String getTikTokEventName() {
        assert getEvent() instanceof PushSubscriptionEvent;
        PushSubscriptionEvent.Action action = ((PushSubscriptionEvent) getEvent()).getAction();
        if (action.equals(SUBSCRIBE)) {
            return EventName.Name.Subscribe.toString();
        }
        return action.toString();
    }
}
