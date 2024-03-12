package com.mparticle.ext.tiktokEapi.utils;

import com.mparticle.ext.tiktokEapi.utils.tiktokApi.AdContext;
import com.mparticle.sdk.model.eventprocessing.CustomEvent;
import com.mparticle.sdk.model.eventprocessing.ImpressionEvent;
import com.mparticle.sdk.model.eventprocessing.ProductActionEvent;
import com.mparticle.sdk.model.eventprocessing.PromotionActionEvent;

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

}
