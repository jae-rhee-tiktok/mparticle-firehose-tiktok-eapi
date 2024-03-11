package com.mparticle.ext.tiktokEapi.utils;

import com.mparticle.sdk.model.eventprocessing.Event;
import com.mparticle.sdk.model.eventprocessing.Product;
import com.mparticle.sdk.model.eventprocessing.ProductActionEvent;
import com.mparticle.tiktokEapi.Content;
import com.mparticle.tiktokEapi.PropertiesContext;

import java.util.ArrayList;
import java.util.List;

public class PropertiesData {

    public static PropertiesContext buildPropertiesContextData(ProductActionEvent event) {
        PropertiesContext propertiesData = new PropertiesContext();

        propertiesData.contentType = event.getRequest().getAccount().getStringSetting(AccountSettings.SETTINGS_CONTENT_TYPE, true, "product");

        List<Content> contentArr = new ArrayList<>();

        for (Product product : event.getProducts()) {
            Content content = new Content();
            content.contentId = product.getId();
            content.contentCategory = product.getCategory();
            content.contentName = product.getName();
            content.price = product.getPrice().floatValue();
            content.quantity = product.getQuantity().intValue();
            content.brand = product.getBrand();

            contentArr.add(content);
        }

        propertiesData.contents = contentArr;
        propertiesData.currency = event.getCurrencyCode();
        propertiesData.value = event.getTotalAmount().floatValue();
        propertiesData.orderId = event.getTransactionId();
        propertiesData.shopId = event.getAffiliation(); // TODO: ???
        propertiesData.description = event.getAttributes().get("description"); // TODO: ???
        propertiesData.query = event.getAttributes().get("query"); // TODO: ???

        return propertiesData;
    }

}
