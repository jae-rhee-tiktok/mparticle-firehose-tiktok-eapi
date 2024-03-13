package com.mparticle.ext.tiktokEapi.utils;

import java.util.Map;

public class Helper {
    public static String getAttributeOrEmptyString(String keyName, Map<String, String> attributes) {
        if (attributes == null) {
            return "";
        }
        if (attributes.get(keyName) == null) {
            return "";
        }
        return attributes.get(keyName);
    }
}
