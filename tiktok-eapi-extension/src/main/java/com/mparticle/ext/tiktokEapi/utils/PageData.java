package com.mparticle.ext.tiktokEapi.utils;

import com.mparticle.ext.tiktokEapi.utils.tiktokApi.PageContext;
import com.mparticle.sdk.model.eventprocessing.*;

import java.util.Map;

import static com.mparticle.ext.tiktokEapi.utils.Helper.getAttributeOrEmptyString;

public class PageData {

    private static void setPageContextData(Map<String,String> eventAttributes, PageContext pageContextData) {
        pageContextData.setUrl(getAttributeOrEmptyString("url", eventAttributes));
        pageContextData.setReferrer(getAttributeOrEmptyString("referrer", eventAttributes));
    }

    public static PageContext buildPageContextData(String url, String referrer) {
        PageContext pageContextData = new PageContext();
        pageContextData.setUrl(url);
        pageContextData.setReferrer(referrer);
        return pageContextData;
    }

    public static PageContext buildPageContextData(ScreenViewEvent event) {
        Map<String,String> eventAttributes = event.getAttributes();
        PageContext pageContextData = new PageContext();
        setPageContextData(eventAttributes, pageContextData);
        return pageContextData;
    }

    public static PageContext buildPageContextData(ProductActionEvent event) {
        Map<String,String> eventAttributes = event.getAttributes();
        PageContext pageContextData = new PageContext();
        setPageContextData(eventAttributes, pageContextData);
        return pageContextData;
    }

    public static PageContext buildPageContextData(CustomEvent event) {
        Map<String,String> eventAttributes = event.getAttributes();
        PageContext pageContextData = new PageContext();
        setPageContextData(eventAttributes, pageContextData);
        return pageContextData;
    }

    public static PageContext buildPageContextData(ImpressionEvent event) {
        Map<String,String> eventAttributes = event.getAttributes();
        PageContext pageContextData = new PageContext();
        setPageContextData(eventAttributes, pageContextData);
        return pageContextData;
    }
}
