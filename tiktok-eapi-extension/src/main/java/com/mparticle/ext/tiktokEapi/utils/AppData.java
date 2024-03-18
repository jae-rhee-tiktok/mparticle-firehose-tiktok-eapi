package com.mparticle.ext.tiktokEapi.utils;

import com.mparticle.ext.tiktokEapi.utils.tiktokApi.AppContext;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.UserContext;
import com.mparticle.sdk.model.eventprocessing.*;

import java.util.Map;

import static com.mparticle.ext.tiktokEapi.utils.Helper.getAttributeOrEmptyString;

public class AppData {

    public static AppContext buildAppContextData(ProductActionEvent event) {
        AppContext appContext = new AppContext();
        setAppContextData(event.getRequest().getRuntimeEnvironment(), appContext);
        return appContext;
    }

    public static AppContext buildAppContextData(PromotionActionEvent event) {
        AppContext appContext = new AppContext();
        setAppContextData(event.getRequest().getRuntimeEnvironment(), appContext);
        return appContext;
    }

    public static AppContext buildAppContextData(CustomEvent event) {
        AppContext appContext = new AppContext();
        setAppContextData(event.getRequest().getRuntimeEnvironment(), appContext);
        return appContext;
    }

    public static AppContext buildAppContextData(ImpressionEvent event) {
        AppContext appContext = new AppContext();
        setAppContextData(event.getRequest().getRuntimeEnvironment(), appContext);
        return appContext;
    }

    public static AppContext buildAppContextData(AttributionEvent event) {
        AppContext appContext = new AppContext();
        setAppContextData(event.getRequest().getRuntimeEnvironment(), appContext);
        return appContext;
    }

    private static void setAppContextData(RuntimeEnvironment runtimeEnvironment, AppContext appContext) {
        String appId, appName, appVersion;
        switch (runtimeEnvironment.getType()) {
            case IOS:
                IosRuntimeEnvironment iosEnv = (IosRuntimeEnvironment)runtimeEnvironment;
                appId = iosEnv.getApplicationPackage();
                appName = iosEnv.getApplicationName();
                appVersion = iosEnv.getApplicationVersion();
                break;
            case ANDROID:
                AndroidRuntimeEnvironment androidEnv = (AndroidRuntimeEnvironment)runtimeEnvironment;
                appId = androidEnv.getApplicationPackage();
                appName = androidEnv.getApplicationName();
                appVersion = androidEnv.getApplicationVersion();
                break;
            case FIRETV:
                FireTVRuntimeEnvironment fireTvEnv = (FireTVRuntimeEnvironment) runtimeEnvironment;
                appId = fireTvEnv.getApplicationPackage();
                appName = fireTvEnv.getApplicationName();
                appVersion = fireTvEnv.getApplicationVersion();
                break;
            case ROKU:
                RokuRuntimeEnvironment rokuEnv = (RokuRuntimeEnvironment) runtimeEnvironment;
                appId = rokuEnv.getApplicationPackage();
                appName = rokuEnv.getApplicationName();
                appVersion = rokuEnv.getApplicationVersion();
                break;
            case TVOS:
                TVOSRuntimeEnvironment tvOsEvn = (TVOSRuntimeEnvironment) runtimeEnvironment;
                appId = tvOsEvn.getApplicationPackage();
                appName = tvOsEvn.getApplicationName();
                appVersion = tvOsEvn.getApplicationVersion();
                break;
            case XBOX:
                XboxRuntimeEnvironment xboxEnv = (XboxRuntimeEnvironment) runtimeEnvironment;
                appId = xboxEnv.getApplicationPackage();
                appName = xboxEnv.getApplicationName();
                appVersion = xboxEnv.getApplicationVersion();
                break;
            case ALEXA:
            case SMARTTV:
            case UNKNOWN:
                GenericRuntimeEnvironment genericEnv = (GenericRuntimeEnvironment) runtimeEnvironment;
                appId = genericEnv.getApplicationPackage();
                appName = genericEnv.getApplicationName();
                appVersion = genericEnv.getApplicationVersion();
                break;
            default:
                appId = "";
                appName = "";
                appVersion = "";
        }

        appContext.setAppId(appId);
        appContext.setAppName(appName);
        appContext.setAppVersion(appVersion);

    }
}
