package com.mparticle.ext.tiktokEapi.utils;

import com.mparticle.ext.tiktokEapi.utils.tiktokApi.AppContext;
import com.mparticle.sdk.model.eventprocessing.*;

import java.util.Map;

import static com.mparticle.ext.tiktokEapi.utils.Helper.getAttributeOrEmptyString;

public class AppData {

    private static void setAppContextData(Map<String, String> eventAttributes, AppContext appContext) {
        appContext.setAppId(getAttributeOrEmptyString("app_id", eventAttributes));
        appContext.setAppName(getAttributeOrEmptyString("app_name", eventAttributes));
        appContext.setAppVersion(getAttributeOrEmptyString("app_version", eventAttributes));
    }

    public static AppContext buildAppContextData(ProductActionEvent event) {
        Map<String, String> eventAttributes = event.getAttributes();
        AppContext appContext = new AppContext();
        setAppContextData(eventAttributes, appContext);
        return appContext;
    }

    public static AppContext buildAppContextData(PromotionActionEvent event) {
        Map<String, String> eventAttributes = event.getAttributes();
        AppContext appContext = new AppContext();
        setAppContextData(eventAttributes, appContext);
        return appContext;
    }

    public static AppContext buildAppContextData(CustomEvent event) {
        Map<String, String> eventAttributes = event.getAttributes();
        AppContext appContext = new AppContext();
        setAppContextData(eventAttributes, appContext);
        return appContext;
    }

    public static AppContext buildAppContextData(ImpressionEvent event) {
        Map<String, String> eventAttributes = event.getAttributes();
        AppContext appContext = new AppContext();
        setAppContextData(eventAttributes, appContext);
        return appContext;
    }
}
