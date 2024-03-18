package com.mparticle.ext.tiktokEapi.utils;

import com.mparticle.ext.tiktokEapi.utils.tiktokApi.AdContext;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.AppContext;
import com.mparticle.sdk.model.eventprocessing.*;

import java.util.Map;

import static com.mparticle.ext.tiktokEapi.utils.Helper.getAttributeOrEmptyString;

public class AdData {

    private static void setAdContextData(Map<String, String> eventAttributes, AdContext adContext) {
        adContext.setAdId(getAttributeOrEmptyString("ad_id", eventAttributes));
        adContext.setAttributed(getAttributeOrEmptyString("attributed", eventAttributes).equalsIgnoreCase("true"));
        adContext.setCallback(getAttributeOrEmptyString("callback", eventAttributes));
        adContext.setAttributionProvider(getAttributeOrEmptyString("attribution_provider", eventAttributes));
        adContext.setCampaignId(getAttributeOrEmptyString("campaign_id", eventAttributes));
        adContext.setCreativeId(getAttributeOrEmptyString("creative_id", eventAttributes));
        adContext.setAttributionType(getAttributeOrEmptyString("attribution_type", eventAttributes));
        adContext.setIsRetargeting(getAttributeOrEmptyString("is_targeting", eventAttributes).equalsIgnoreCase("true"));
    }

    private static void setAppContextDataForIos(RuntimeEnvironment runtimeEnvironment, AdContext adContext) { // Apple Search Ads Attribution Support
        if (runtimeEnvironment.getType().equals(RuntimeEnvironment.Type.IOS)) {
            IosRuntimeEnvironment iosEnv = (IosRuntimeEnvironment)runtimeEnvironment;
            Map<String, String> appleSearchAdsAttribution = iosEnv.getAppleSearchAdsAttribution().get(0);

            adContext.setAdId(getAttributeOrEmptyString("ad_id", appleSearchAdsAttribution));
            adContext.setAttributed(getAttributeOrEmptyString("iad-attribution", appleSearchAdsAttribution).equalsIgnoreCase("true"));
//            adContext.setCallback(""); // TODO
//            adContext.setAttributionProvider(""); // TODO
            adContext.setCampaignId(getAttributeOrEmptyString("iad-campaign-id", appleSearchAdsAttribution));
            adContext.setCreativeId(getAttributeOrEmptyString("iad-lineitem-id", appleSearchAdsAttribution));
            adContext.setAttributionType(getAttributeOrEmptyString("iad-conversion-type", appleSearchAdsAttribution));
//                adContext.setIsRetargeting(getAttributeOrEmptyString("is_targeting", appleSearchAdsAttribution).equalsIgnoreCase("true")); // TODO
        }
    }

    public static AdContext buildAdContextData(ProductActionEvent event) {
        Map<String, String> eventAttributes = event.getAttributes();
        AdContext adContext = new AdContext();
        setAdContextData(eventAttributes, adContext);
        return adContext;
    }

    public static AdContext buildAdContextData(PromotionActionEvent event) {
        Map<String, String> eventAttributes = event.getAttributes();
        AdContext adContext = new AdContext();
        setAdContextData(eventAttributes, adContext);
        return adContext;
    }

    public static AdContext buildAdContextData(CustomEvent event) {
        Map<String, String> eventAttributes = event.getAttributes();
        AdContext adContext = new AdContext();
        setAdContextData(eventAttributes, adContext);
        return adContext;
    }

    public static AdContext buildAdContextData(ImpressionEvent event) {
        Map<String, String> eventAttributes = event.getAttributes();
        AdContext adContext = new AdContext();
        setAdContextData(eventAttributes, adContext);
        return adContext;
    }

    public static AdContext buildAdContextData(AttributionEvent event) {
        Map<String, String> eventAttributes = event.getAttributes();
        AdContext adContext = new AdContext();
        setAdContextData(eventAttributes, adContext);
        setAppContextDataForIos(event.getRequest().getRuntimeEnvironment(), adContext);
        return adContext;
    }

}
