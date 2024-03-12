package com.mparticle.ext.tiktokEapi.utils;

import com.google.gson.Gson;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.Content;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.PropertiesContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mparticle.ext.tiktokEapi.utils.Helper.getAttributeOrEmptyString;

public class PropertiesData {

    private static Logger logger = LogManager.getLogger(PropertiesData.class);

    public static PropertiesContext buildPropertiesContextData(ApplicationStateTransitionEvent event) {
        PropertiesContext propertiesData = new PropertiesContext();

        return propertiesData;
    }

    public static PropertiesContext buildPropertiesContextData(AttributionEvent event) {
        PropertiesContext propertiesData = new PropertiesContext();

        return propertiesData;
    }

    public static PropertiesContext buildPropertiesContextData(ErrorEvent event) {
        PropertiesContext propertiesData = new PropertiesContext();

        return propertiesData;
    }

    public static PropertiesContext buildPropertiesContextData(ImpressionEvent event) {
        PropertiesContext propertiesData = new PropertiesContext();

        propertiesData.setContentType(event.getRequest().getAccount().getStringSetting(AccountSettings.SETTINGS_CONTENT_TYPE, true, "product"));

        List<Content> contentArr = new ArrayList<>();

        StringBuilder description = new StringBuilder();

        for (Impression impression : event.getImpressions()) {
            description.append(impression.getListName()).append("\n");
            for (Product product : impression.getProducts()) {
                Content content = new Content();
                content.setContentId(product.getId());
                content.setContentCategory(product.getCategory());
                content.setContentName(product.getName());
                content.setPrice(product.getPrice().floatValue());
                content.setQuantity(product.getQuantity().intValue());
                content.setBrand(product.getBrand());

                contentArr.add(content);
            }
        }

        propertiesData.setContents(contentArr);

        Map<String, String> eventAttributes = event.getAttributes();
        propertiesData.setDescription(getAttributeOrEmptyString("description", eventAttributes));
        if (propertiesData.getDescription().equalsIgnoreCase("")) {
            propertiesData.setDescription(description.toString());
        }
        propertiesData.setQuery(getAttributeOrEmptyString("query", eventAttributes)); // TODO: ???

        return propertiesData;
    }

    public static PropertiesContext buildPropertiesContextData(PrivacySettingChangeEvent event) {
        PropertiesContext propertiesData = new PropertiesContext();

        return propertiesData;
    }

    public static PropertiesContext buildPropertiesContextData(PromotionActionEvent event) {
        PropertiesContext propertiesData = new PropertiesContext();

        List<com.mparticle.ext.tiktokEapi.utils.tiktokApi.Promotion> promotions = buildPromotions(event.getPromotions());
        propertiesData.setPromotions(promotions);

        Map<String, String> eventAttributes = event.getAttributes();
        propertiesData.setDescription(getAttributeOrEmptyString("currency", eventAttributes));
        propertiesData.setQuery(getAttributeOrEmptyString("value", eventAttributes));
        propertiesData.setDescription(getAttributeOrEmptyString("orderId", eventAttributes));
        propertiesData.setQuery(getAttributeOrEmptyString("shopId", eventAttributes));
        propertiesData.setDescription(getAttributeOrEmptyString("description", eventAttributes));
        propertiesData.setQuery(getAttributeOrEmptyString("query", eventAttributes));

        return propertiesData;
    }

    public static PropertiesContext buildPropertiesContextData(PushMessageOpenEvent event) {
        PropertiesContext propertiesData = new PropertiesContext();

        return propertiesData;
    }

    public static PropertiesContext buildPropertiesContextData(PushMessageReceiptEvent event) {
        PropertiesContext propertiesData = new PropertiesContext();

        return propertiesData;
    }

    public static PropertiesContext buildPropertiesContextData(PushSubscriptionEvent event) {
        return new PropertiesContext();
    }

    public static PropertiesContext buildPropertiesContextData(ScreenViewEvent event) {
        PropertiesContext propertiesData = new PropertiesContext();

        return propertiesData;
    }

    public static PropertiesContext buildPropertiesContextData(SessionEndEvent event) {
        PropertiesContext propertiesData = new PropertiesContext();

        return propertiesData;
    }

    public static PropertiesContext buildPropertiesContextData(SessionStartEvent event) {
        PropertiesContext propertiesData = new PropertiesContext();

        return propertiesData;
    }

    public static PropertiesContext buildPropertiesContextData(UserAttributeChangeEvent event) {
        PropertiesContext propertiesData = new PropertiesContext();

        return propertiesData;
    }

    public static PropertiesContext buildPropertiesContextData(UserIdentityChangeEvent event) {
        PropertiesContext propertiesData = new PropertiesContext();

        return propertiesData;
    }

    public static PropertiesContext buildPropertiesContextData(ProductActionEvent event) {
        PropertiesContext propertiesData = new PropertiesContext();

        propertiesData.setContentType(event.getRequest().getAccount().getStringSetting(AccountSettings.SETTINGS_CONTENT_TYPE, true, "product"));

        List<Content> contentArr = getContents(event);

        propertiesData.setContents(contentArr);
        propertiesData.setCurrency(event.getCurrencyCode());
        propertiesData.setValue(event.getTotalAmount().floatValue());
        propertiesData.setOrderId(event.getTransactionId());
        propertiesData.setShopId(event.getAffiliation()); // TODO: ???

        Map<String, String> eventAttributes = event.getAttributes();
        propertiesData.setDescription(getAttributeOrEmptyString("description", eventAttributes));
        propertiesData.setQuery(getAttributeOrEmptyString("query", eventAttributes)); // TODO: ???

        return propertiesData;
    }

    private static List<Content> getContents(ProductActionEvent event) {
        List<Content> contentArr = new ArrayList<>();

        for (Product product : event.getProducts()) {
            Content content = new Content();
            content.setContentId(product.getId());
            content.setContentCategory(product.getCategory());
            content.setContentName(product.getName());
            content.setPrice(product.getPrice().floatValue());
            content.setQuantity(product.getQuantity().intValue());
            content.setBrand(product.getBrand());

            contentArr.add(content);
        }
        return contentArr;
    }

    public static PropertiesContext buildPropertiesContextData(CustomEvent event) {
        PropertiesContext propertiesData = new PropertiesContext();

        propertiesData.setContentType(event.getRequest().getAccount().getStringSetting(AccountSettings.SETTINGS_CONTENT_TYPE, true, "product"));
        List<Content> contentsArr = buildProductsFromAttributes(event.getAttributes().get("products"));

        propertiesData.setContents(contentsArr);

        Map<String, String> eventAttributes = event.getAttributes();
        propertiesData.setDescription(getAttributeOrEmptyString("currency", eventAttributes));
        propertiesData.setQuery(getAttributeOrEmptyString("value", eventAttributes));
        propertiesData.setDescription(getAttributeOrEmptyString("orderId", eventAttributes));
        propertiesData.setQuery(getAttributeOrEmptyString("shopId", eventAttributes));
        propertiesData.setDescription(getAttributeOrEmptyString("description", eventAttributes));
        propertiesData.setQuery(getAttributeOrEmptyString("query", eventAttributes));

        return propertiesData;
    }

    private static List<Content> buildProductsFromAttributes(String inputArrString) {
        List<Content> contentsArr = new ArrayList<>();

        try {
            Gson gson = new Gson();
            Product[] productsArr = gson.fromJson(inputArrString, Product[].class);

            for (Product product : productsArr) {
                Content content = new Content();
                content.setContentId(product.getId());
                content.setContentCategory(product.getCategory());
                content.setContentName(product.getName());
                content.setPrice(product.getPrice().floatValue());
                content.setQuantity(product.getQuantity().intValue());
                content.setBrand(product.getBrand());

                contentsArr.add(content);
            }
        } catch (Exception e) {
            logger.error("Gson error. Returning empty array: ", e);
        }

        return contentsArr;
    }

    private static List<com.mparticle.ext.tiktokEapi.utils.tiktokApi.Promotion> buildPromotions(List<Promotion> mParticlePromotions) {
        List<com.mparticle.ext.tiktokEapi.utils.tiktokApi.Promotion> tiktokPromos = new ArrayList<>();
        for (Promotion promo : mParticlePromotions) {
            com.mparticle.ext.tiktokEapi.utils.tiktokApi.Promotion tiktikPromo = new com.mparticle.ext.tiktokEapi.utils.tiktokApi.Promotion();

            tiktikPromo.setId(promo.getId());
            tiktikPromo.setName(promo.getName());
            tiktikPromo.setCreative(promo.getCreative());
            tiktikPromo.setPosition(promo.getPosition());

            tiktokPromos.add(tiktikPromo);
        }
        return tiktokPromos;
    }

}
