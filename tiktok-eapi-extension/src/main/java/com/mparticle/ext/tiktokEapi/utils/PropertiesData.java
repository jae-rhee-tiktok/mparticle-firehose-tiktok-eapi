package com.mparticle.ext.tiktokEapi.utils;

import com.google.gson.Gson;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.Content;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.PropertiesContext;

import java.util.ArrayList;
import java.util.List;

public class PropertiesData {

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

        return propertiesData;
    }

    public static PropertiesContext buildPropertiesContextData(PrivacySettingChangeEvent event) {
        PropertiesContext propertiesData = new PropertiesContext();

        return propertiesData;
    }

    public static PropertiesContext buildPropertiesContextData(PromotionActionEvent event) {
        PropertiesContext propertiesData = new PropertiesContext();

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
        PropertiesContext propertiesData = new PropertiesContext();

        return propertiesData;
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

        propertiesData.setContents(contentArr);
        propertiesData.setCurrency(event.getCurrencyCode());
        propertiesData.setValue(event.getTotalAmount().floatValue());
        propertiesData.setOrderId(event.getTransactionId());
        propertiesData.setShopId(event.getAffiliation()); // TODO: ???
        propertiesData.setDescription(event.getAttributes().get("description")); // TODO: ???
        propertiesData.setQuery(event.getAttributes().get("query")); // TODO: ???

        return propertiesData;
    }

    public static PropertiesContext buildPropertiesContextData(CustomEvent event) {
        PropertiesContext propertiesData = new PropertiesContext();

        propertiesData.setContentType(event.getRequest().getAccount().getStringSetting(AccountSettings.SETTINGS_CONTENT_TYPE, true, "product"));
        List<Content> contentsArr = buildProductsFromAttributes(event.getAttributes().get("products"));

        propertiesData.setContents(contentsArr);
        propertiesData.setCurrency(event.getAttributes().get("currency"));
        propertiesData.setValue(Float.parseFloat(event.getAttributes().get("value")));
        propertiesData.setOrderId(event.getAttributes().get("orderId")); // TODO: ???
        propertiesData.setShopId(event.getAttributes().get("shopId")); // TODO: ???
        propertiesData.setDescription(event.getAttributes().get("description")); // TODO: ???
        propertiesData.setQuery(event.getAttributes().get("query")); // TODO: ???

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

        }

        return contentsArr;
    }

}
