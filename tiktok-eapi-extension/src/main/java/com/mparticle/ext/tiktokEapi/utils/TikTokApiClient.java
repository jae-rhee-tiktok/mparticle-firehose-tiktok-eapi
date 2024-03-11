package com.mparticle.ext.tiktokEapi.utils;

import com.mparticle.sdk.model.eventprocessing.Event;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class TikTokApiClient {

    private static final String url = "https://business-api.tiktok.com/open_api/v1.3/event/track/";
    private final String apiKey;
    private final String eventSourceId;
    private final String eventSource;
    private final Event event;

    public TikTokApiClient(Event event) {
        this.apiKey = event.getRequest().getAccount().getStringSetting(AccountSettings.SETTINGS_ACCESS_TOKEN, true, null);
        this.eventSource = event.getRequest().getAccount().getStringSetting(AccountSettings.SETTINGS_EVENT_SOURCE, true, "web");
        this.eventSourceId = event.getRequest().getAccount().getStringSetting(AccountSettings.SETTINGS_EVENT_SOURCE_ID, true, null);
        this.event = event;
    }

    public void sendRequest(String payload) throws IOException {
        String result = "";
        HttpPost post = new HttpPost(url);
        StringEntity reqBodyString = new StringEntity(payload);
        post.setEntity(reqBodyString);
        post.setHeader("Access-token", apiKey);
        post.setHeader("Content-type", "application/json");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            // handle result
            result = EntityUtils.toString(response.getEntity());
        }

//        return result;
    }

}
