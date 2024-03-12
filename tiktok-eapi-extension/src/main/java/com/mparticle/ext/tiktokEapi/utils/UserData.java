package com.mparticle.ext.tiktokEapi.utils;

import com.mparticle.sdk.model.eventprocessing.Event;
import com.mparticle.sdk.model.eventprocessing.UserIdentity;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.UserContext;

import java.util.ArrayList;
import java.util.List;

public class UserData {

    public static UserContext buildUserContextData(Event event) {
        List<UserIdentity> userIdentities = event.getRequest().getUserIdentities();
        String eventSource = event.getRequest().getAccount().getStringSetting(AccountSettings.SETTINGS_EVENT_SOURCE, true, "web");

        UserContext userData = new UserContext();

        List<String> emailArray = new ArrayList<>();
        List<String> phoneArray = new ArrayList<>();
        List<String> extIdArray = new ArrayList<>();

        try {
            for (UserIdentity userIdentity : userIdentities) {
                switch (userIdentity.getType()) {
                    case EMAIL:
                        emailArray.add(userIdentity.getValue());
                        break;
                    case MOBILE_NUMBER:
                    case PHONE_NUMBER_2:
                    case PHONE_NUMBER_3:
                        phoneArray.add(userIdentity.getValue());
                        break;
                    case CUSTOMER:
                        extIdArray.add(userIdentity.getValue());
                        break;
                }
            }

            userData.email = emailArray;
            userData.phone = phoneArray;
            userData.extId = extIdArray;

            if (eventSource.equalsIgnoreCase("web")) {
                userData.clickId = "";
                userData.cookieId = "";
            }

            if (eventSource.equalsIgnoreCase("app")) {
                userData.idfa = "";
                userData.idfv = "";
                userData.gaid = "";
                userData.attStatus = "";
            }

            if (eventSource.equalsIgnoreCase("web") || eventSource.equalsIgnoreCase("app")) {
                userData.ip = "";
                userData.userAgent = "";
                userData.locale = "";
            }

        } catch (Exception e) {
//            throw new RuntimeException(e);
        }

        return userData;
    }
}
