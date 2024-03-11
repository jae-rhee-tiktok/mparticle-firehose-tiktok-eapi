package com.mparticle.ext.tiktokEapi.eventProcessor;

import com.google.gson.Gson;
import com.mparticle.sdk.model.eventprocessing.Event;
import com.mparticle.tiktokEapi.PropertiesContext;
import com.mparticle.tiktokEapi.UserContext;

public class EventProcessor {
    public Event event;
    public UserContext userContextData;
    public PropertiesContext propertiesContextData;

    public

    public void sendTikTokEvent() {
        Gson gson = new Gson();
        String userContextDataStr = gson.toJson(userContextData);

    }
}
