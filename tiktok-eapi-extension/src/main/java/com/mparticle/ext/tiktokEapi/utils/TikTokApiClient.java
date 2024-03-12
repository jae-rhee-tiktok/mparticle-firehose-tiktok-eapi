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
//    private final String accessToken;
//    private final String eventSourceId;
//    private final String eventSource;

    public TikTokApiClient() {
//        this.accessToken = accessToken;
//        this.eventSource = eventSource;
//        this.eventSourceId = eventSourceId;
    }

    public void sendRequest(String accessToken, String payload) throws IOException {
        String result = "";
        HttpPost post = new HttpPost(url);
        StringEntity reqBodyString = new StringEntity(payload);
        post.setEntity(reqBodyString);
        post.setHeader("Access-token", accessToken);
        post.setHeader("Content-type", "application/json");

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post);
            // handle result
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            // TODO: loggging
        } finally {
            // TODO: loggging
        }

//        return result;
    }

}
