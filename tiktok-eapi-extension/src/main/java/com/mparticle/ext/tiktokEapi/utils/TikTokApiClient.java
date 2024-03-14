package com.mparticle.ext.tiktokEapi.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.ApiResponseBody;
import org.apache.http.HttpEntity;
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
import java.util.concurrent.TimeUnit;

public class TikTokApiClient {

    final static Logger logger = LogManager.getLogger(TikTokApiClient.class);

    private static final String url = "https://business-api.tiktok.com/open_api/v1.3/event/track/";

    public TikTokApiClient() {

    }

    public void sendPostRequest(String accessToken, String payload) throws IOException {
        logger.debug("sendRequest accessToken: " + accessToken + " payload: " + payload);

        HttpPost post = new HttpPost(url);
        post.setHeader("Access-token", accessToken);
        post.setHeader("Content-type", "application/json");
        StringEntity reqBodyString = new StringEntity(payload);
        post.setEntity(reqBodyString);

        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create()
                    .setRetryHandler(new DefaultHttpRequestRetryHandler(5, false))
                    .build();
            CloseableHttpResponse response = httpClient.execute(post);

            // handle result
            String result = EntityUtils.toString(response.getEntity());
            logger.info("sendPostRequest res: " + result);
            handleTikTokApi200Response(result, accessToken, payload);
        } catch (IOException e) {
            logger.error("sendPostRequest error msg: ", e);
            throw new IOException(e);
        }
    }

    private void handleTikTokApi200Response(String response, String accessToken, String reqBody) throws IOException {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        ApiResponseBody responseBody = gson.fromJson(response, ApiResponseBody.class);
        if (responseBody.getCode() != 0) {
            if (responseBody.getCode() == 40100) {
                try {
                    TimeUnit.SECONDS.sleep(5); // wait for QPS to reset
                } catch (InterruptedException e) {
                    logger.error("TikTokApiClient retry interrupted!");
                    logger.debug(String.format("accessToken: %s reqBody: %s", accessToken, reqBody));
                    throw new RuntimeException(e);
                }
                sendPostRequest(accessToken, reqBody);
                return;
            }
            throw new IOException("TikTok API HTTP response code error: " + responseBody.getCode() + ' ' + responseBody.getMessage());
        }
    }

}
