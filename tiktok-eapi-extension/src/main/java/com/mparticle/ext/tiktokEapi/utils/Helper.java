package com.mparticle.ext.tiktokEapi.utils;

import java.util.Map;

public class Helper {
    public static String getAttributeOrEmptyString(String keyName, Map<String, String> userAttributes) {
        return !userAttributes.get(keyName).isEmpty() ? userAttributes.get(keyName) : "";
    }
}
