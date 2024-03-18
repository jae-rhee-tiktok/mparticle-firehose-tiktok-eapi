package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.mparticle.ext.tiktokEapi.utils.AdData;
import com.mparticle.ext.tiktokEapi.utils.PropertiesData;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.EventName;
import com.mparticle.sdk.model.eventprocessing.AttributionEvent;
import com.mparticle.sdk.model.eventprocessing.CustomEvent;

public class AttributionEventProcessor extends EventProcessor {
    public AttributionEventProcessor(AttributionEvent event) {
        super(event);
        this.setPropertiesContextData(PropertiesData.buildPropertiesContextData(event));
        setAdContextData(AdData.buildAdContextData(event));
    }

    @Override
    public String getTikTokEventName() {
        assert getEvent() instanceof AttributionEvent;
        AttributionEvent attributionEvent = ((AttributionEvent) getEvent());
        String eventName = attributionEvent.getName().toLowerCase();
        switch (eventName) {
            case "attribution":
                return EventName.Name.ViewContent.toString(); // TODO: app events
            case "re-engagement":
                return EventName.Name.Search.toString(); // TODO: app events
            default:
                return captureTikTokEvent(eventName);
        }
    }
}
