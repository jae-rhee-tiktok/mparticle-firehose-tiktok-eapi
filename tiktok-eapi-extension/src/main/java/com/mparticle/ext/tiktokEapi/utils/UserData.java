package com.mparticle.ext.tiktokEapi.utils;

import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.ext.tiktokEapi.utils.tiktokApi.UserContext;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mparticle.ext.tiktokEapi.utils.Helper.getAttributeOrEmptyString;

public class UserData {

    private static Logger logger = LogManager.getLogger(UserData.class);

    public static UserContext buildUserContextData(Event event) {
        List<UserIdentity> userIdentities = event.getRequest().getUserIdentities();
        String eventSource = event.getRequest().getAccount().getStringSetting(AccountSettings.SETTINGS_EVENT_SOURCE, true, "web");
        RuntimeEnvironment runtimeEnvironment = event.getRequest().getRuntimeEnvironment();

        UserContext userData = new UserContext();

        List<String> emailArray = new ArrayList<>();
        List<String> phoneArray = new ArrayList<>();
        List<String> extIdArray = new ArrayList<>();
        extIdArray.add(checkAndReturnHash(event.getRequest().getMpId())); // add mP user ID

        try {
            for (UserIdentity userIdentity : userIdentities) {
                switch (userIdentity.getType()) {
                    case EMAIL:
                    case OTHER2: // EMAIL_ALIAS_1
                    case OTHER3: // EMAIL_ALIAS_2
                    case OTHER4: // EMAIL_ALIAS_3
                    case OTHER5: // EMAIL_ALIAS_4
                        emailArray.add(checkAndReturnHash(userIdentity.getValue()));
                        break;
                    case MOBILE_NUMBER:
                    case PHONE_NUMBER_2:
                    case PHONE_NUMBER_3:
                        phoneArray.add(checkAndReturnHash(userIdentity.getValue()));
                        break;
                    case CUSTOMER:
                        extIdArray.add(checkAndReturnHash(userIdentity.getValue()));
                        break;
                }
            }

            userData.setEmail(emailArray);
            userData.setPhone(phoneArray);
            userData.setExtId(extIdArray);

            if (eventSource.equalsIgnoreCase("app")) {
                List<DeviceIdentity> deviceIdentities = event.getRequest().getRuntimeEnvironment().getIdentities();
                for(DeviceIdentity deviceIdentity : deviceIdentities) {
                    DeviceIdentity.Type type = deviceIdentity.getType();
                    switch (type) {
                        case IOS_ADVERTISING_ID:
                            userData.setIdfa(deviceIdentity.getValue().toUpperCase());
                            break;
                        case IOS_VENDOR_ID:
                            userData.setIdfv(deviceIdentity.getValue());
                            break;
                        case GOOGLE_ADVERTISING_ID:
                            userData.setGaid(deviceIdentity.getValue().toLowerCase());
                            break;
                    }
                }
                userData.setAttStatus(getAttStatus(runtimeEnvironment));
            }

            if (eventSource.equalsIgnoreCase("web") || eventSource.equalsIgnoreCase("app")) { // TODO: update
                RuntimeEnvironment runtimeEnv = event.getRequest().getRuntimeEnvironment();
                userData.setIp(runtimeEnv.getClientIpAddress());
                userData.setUserAgent(runtimeEnv.getUserAgent());
                userData.setLocale(getLocale(runtimeEnvironment));
            }

        } catch (Exception e) {
            logger.error("buildUserContextData error msg: ", e);

            UserContext baseUserData = new UserContext();
            baseUserData.setExtId(extIdArray);

            return baseUserData;
        }

        return userData;
    }

    private static void setUserContextData(Map<String, String> eventAttributes, Map<String, String> userAttributes, UserContext userContext) {
        String url = getAttributeOrEmptyString("url", eventAttributes);
        String ttclid = "";
        List<NameValuePair> params = URLEncodedUtils.parse(url, StandardCharsets.UTF_8);
        for (NameValuePair param : params) {
            if (param.getName().endsWith("ttclid")) {
                ttclid = param.getValue();
            }
        }
        userContext.setClickId(ttclid);
        userContext.setCookieId(getAttributeOrEmptyString("ttp", eventAttributes));

        // update additional user info
        userContext.setFirstName(getAttributeOrEmptyString("$firstname", userAttributes));
        userContext.setLastName(getAttributeOrEmptyString("$lastname", userAttributes));
        userContext.setCity(getAttributeOrEmptyString("$city", userAttributes));
        userContext.setCountry(getAttributeOrEmptyString("$country", userAttributes));
        userContext.setRegion(getAttributeOrEmptyString("$state", userAttributes));
        userContext.setZip(getAttributeOrEmptyString("$zip", userAttributes));
    }

    public static void updateUserData(CustomEvent event, UserContext userContext) {

        Map<String, String> eventAttributes = event.getAttributes();
        Map<String, String> userAttributes = event.getRequest().getUserAttributes();

        setUserContextData(eventAttributes, userAttributes, userContext);
    }

    public static void updateUserData(ProductActionEvent event, UserContext userContext) {

        Map<String, String> eventAttributes = event.getAttributes();
        Map<String, String> userAttributes = event.getRequest().getUserAttributes();

        setUserContextData(eventAttributes, userAttributes, userContext);
    }

    public static void updateUserData(ImpressionEvent event, UserContext userContext) {

        Map<String, String> eventAttributes = event.getAttributes();
        Map<String, String> userAttributes = event.getRequest().getUserAttributes();

        setUserContextData(eventAttributes, userAttributes, userContext);
    }

    public static void updateUserData(PromotionActionEvent event, UserContext userContext) {

        Map<String, String> eventAttributes = event.getAttributes();
        Map<String, String> userAttributes = event.getRequest().getUserAttributes();

        setUserContextData(eventAttributes, userAttributes, userContext);
    }

    /**
     * @param runtimeEnvironment
     * @return
     *
     * Supported TIkTok Enums:
     * - AUTHORIZED
     * - DENIED
     * - NOT_DETERMINED
     * - RESTRICTED
     * - NOT_APPLICABLE (not supported by mParticle)
     */
    private static String getAttStatus(RuntimeEnvironment runtimeEnvironment) {
        if (runtimeEnvironment.getType().equals(RuntimeEnvironment.Type.IOS)) {
            return ((IosRuntimeEnvironment)runtimeEnvironment).getAttAuthorizationStatus().toString();
        }
        return "";
    }

    private static String getLocale(RuntimeEnvironment runtimeEnvironment) {
        String languageLocale;
        String countryLocale;
        switch (runtimeEnvironment.getType()) {
            case IOS:
                IosRuntimeEnvironment iosEnv = (IosRuntimeEnvironment)runtimeEnvironment;
                languageLocale = iosEnv.getLocaleLanguage();
                countryLocale = iosEnv.getLocaleCountry();
                break;
            case ANDROID:
                AndroidRuntimeEnvironment androidEnv = (AndroidRuntimeEnvironment)runtimeEnvironment;
                languageLocale = androidEnv.getLocaleLanguage();
                countryLocale = androidEnv.getLocaleCountry();
                break;
            case MOBILEWEB:
                WebRuntimeEnvironment webEnv = (WebRuntimeEnvironment)runtimeEnvironment;
                languageLocale = webEnv.getLocaleLanguage();
                countryLocale = webEnv.getLocaleCountry();
                break;
            case FIRETV:
                FireTVRuntimeEnvironment fireTvEnv = (FireTVRuntimeEnvironment) runtimeEnvironment;
                languageLocale = fireTvEnv.getLocaleLanguage();
                countryLocale = fireTvEnv.getLocaleCountry();
                break;
            case ROKU:
                RokuRuntimeEnvironment rokuEnv = (RokuRuntimeEnvironment) runtimeEnvironment;
                languageLocale = rokuEnv.getLocaleLanguage();
                countryLocale = rokuEnv.getLocaleCountry();
                break;
            case TVOS:
                TVOSRuntimeEnvironment tvOsEvn = (TVOSRuntimeEnvironment) runtimeEnvironment;
                languageLocale = tvOsEvn.getLocaleLanguage();
                countryLocale = tvOsEvn.getLocaleCountry();
                break;
            default:
                return "";
        }
        return languageLocale + "-" + countryLocale;
    }

    private static String checkAndReturnHash(String input) {
        String lcInput = input.toLowerCase();
        if (lcInput.length() != 64) {
            return DigestUtils.sha256Hex(lcInput);
        } else {
            if (lcInput.contains("@")) {
                return DigestUtils.sha256Hex(lcInput);
            }
            if (lcInput.contains("+")) {
                return DigestUtils.sha256Hex(lcInput);
            }
        }
        return lcInput;
    }
}
