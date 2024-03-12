package com.mparticle.ext.tiktokEapi.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class TikTokApiClient {

    final static Logger logger = LogManager.getLogger(TikTokApiClient.class);

    private static final String url = "https://business-api.tiktok.com/open_api/v1.3/event/track/";
//    private final String accessToken;
//    private final String eventSourceId;
//    private final String eventSource;

    public TikTokApiClient() {
//        this.accessToken = accessToken;
//        this.eventSource = eventSource;
//        this.eventSourceId = eventSourceId;
    }

    public void sendPostRequest(String accessToken, String payload) throws IOException {
        logger.debug("sendRequest accessToken: " + accessToken + " payload: " + payload);

        HttpPost post = new HttpPost(url);
        post.setHeader("Access-token", accessToken);
        post.setHeader("Content-type", "application/json");
        StringEntity reqBodyString = new StringEntity(payload);
        post.setEntity(reqBodyString);

        try {
//            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpClient httpClient = HttpClientBuilder.create()
                    .setRetryHandler(new DefaultHttpRequestRetryHandler(5, false))
                    .build();
            CloseableHttpResponse response = httpClient.execute(post);
            // handle result
            String result = EntityUtils.toString(response.getEntity());
            logger.info("sendRequest res: " + result);
        } catch (IOException e) {
            logger.error("sendRequest error msg: ", e);
            throw new IOException(e);
        }
    }

}
