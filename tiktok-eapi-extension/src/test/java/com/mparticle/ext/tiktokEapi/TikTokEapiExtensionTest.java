package com.mparticle.ext.tiktokEapi;

import com.mparticle.ext.tiktokEapi.utils.AccountSettings;
import com.mparticle.sdk.model.MessageSerializer;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.eventprocessing.consent.CCPAConsent;
import com.mparticle.sdk.model.eventprocessing.consent.ConsentState;
import com.mparticle.sdk.model.eventprocessing.consent.GDPRConsent;
import com.mparticle.sdk.model.registration.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests are fun! Here's an empty test class to get you started.
 *
 * These tests can be run by executing the 'test' gradle task via `./gradlew test`
 */
public class TikTokEapiExtensionTest  {

    private final Logger logger = LogManager.getLogger(TikTokEapiExtensionTest.class);

    MessageSerializer serializer = new MessageSerializer();

    private final String userAgentName = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";

    private EventProcessingRequest createGenericEnvRequest() {
        EventProcessingRequest request = new EventProcessingRequest();

        request.setUserAttributes(createUserAttributes());
        request.setUserIdentities(createUserIdentities());
        request.setMpId("test_mp_id");
        request.setAccount(createWebAccount());

        GenericRuntimeEnvironment genericRuntimeEnvironment = new GenericRuntimeEnvironment();
        genericRuntimeEnvironment.setLocaleCountry("US");
        genericRuntimeEnvironment.setLocaleLanguage("en");
        genericRuntimeEnvironment.setClientIpAddress("0.0.0.0");
        genericRuntimeEnvironment.setApplicationName("test_gen_env_app_name");
        genericRuntimeEnvironment.setApplicationPackage("test_gen_env_pp_package_id");
        genericRuntimeEnvironment.setApplicationVersion("test_gen_env_app_verision");

        request.setRuntimeEnvironment(genericRuntimeEnvironment);

        return request;
    }

    private EventProcessingRequest createUnknownEnvRequest() {
        EventProcessingRequest request = new EventProcessingRequest();

        request.setUserAttributes(createUserAttributes());
        request.setUserIdentities(createUserIdentities());
        request.setMpId("test_mp_id");
        request.setAccount(createOfflineAccount());

        UnknownRuntimeEnvironment unknownRuntimeEnvironment = new UnknownRuntimeEnvironment();
        unknownRuntimeEnvironment.setClientIpAddress("0.0.0.0");
        unknownRuntimeEnvironment.setUserAgent(userAgentName);

        request.setRuntimeEnvironment(unknownRuntimeEnvironment);

        return request;
    }

    private EventProcessingRequest createIosAppRequest() {
        EventProcessingRequest request = new EventProcessingRequest();
        request.setUserAttributes(createUserAttributes());
        request.setUserIdentities(createUserIdentities());
        request.setMpId("test_mp_id");
        request.setAccount(createAppAccount());

        IosRuntimeEnvironment iosRuntimeEnvironment = new IosRuntimeEnvironment();
        iosRuntimeEnvironment.setClientIpAddress("0.0.0.0");
        iosRuntimeEnvironment.setUserAgent(userAgentName);
        iosRuntimeEnvironment.setLocaleCountry("US");
        iosRuntimeEnvironment.setLocaleLanguage("en");
        iosRuntimeEnvironment.setApplicationName("test_ios_app_name");
        iosRuntimeEnvironment.setApplicationPackage("test_ios_app_package_id");
        iosRuntimeEnvironment.setApplicationVersion("test_ios_app_version");

        request.setRuntimeEnvironment(iosRuntimeEnvironment);

        return request;
    }

    private EventProcessingRequest createWebRequest() {
        EventProcessingRequest request = new EventProcessingRequest();

        request.setUserAttributes(createUserAttributes());
        request.setUserIdentities(createUserIdentities());
        request.setMpId("test_mp_id");
        request.setAccount(createWebAccount());

        WebRuntimeEnvironment webRuntimeEnvironment = new WebRuntimeEnvironment();
        webRuntimeEnvironment.setClientIpAddress("0.0.0.0");
        webRuntimeEnvironment.setUserAgent(userAgentName);
        webRuntimeEnvironment.setLocaleCountry("US");
        webRuntimeEnvironment.setLocaleLanguage("en");

        request.setRuntimeEnvironment(webRuntimeEnvironment);

        return request;
    }

    private EventProcessingRequest createWebRequestWithCCPA() {
        EventProcessingRequest request = new EventProcessingRequest();

        request.setUserAttributes(createUserAttributes());
        request.setUserIdentities(createUserIdentities());
        request.setMpId("test_mp_id");

        ConsentState consentState = new ConsentState();
        consentState.setCCPA(createCCPAConsent());
        request.setConsentState(consentState);

        request.setAccount(createWebAccount());

        WebRuntimeEnvironment webRuntimeEnvironment = new WebRuntimeEnvironment();
        webRuntimeEnvironment.setClientIpAddress("0.0.0.0");
        webRuntimeEnvironment.setUserAgent(userAgentName);
        webRuntimeEnvironment.setLocaleCountry("US");
        webRuntimeEnvironment.setLocaleLanguage("en");

        request.setRuntimeEnvironment(webRuntimeEnvironment);

        return request;
    }

    private EventProcessingRequest createWebRequestWithGDPRLocation() {
        EventProcessingRequest request = new EventProcessingRequest();

        request.setUserAttributes(createUserAttributes());
        request.setUserIdentities(createUserIdentities());
        request.setMpId("test_mp_id");

        ConsentState consentState = new ConsentState();
        Map<String, GDPRConsent> gdprConsentMap = new HashMap<>();
        gdprConsentMap.put("location_collection", createLocationGDPRConsent());
        request.setConsentState(consentState);

        request.setAccount(createWebAccount());

        WebRuntimeEnvironment webRuntimeEnvironment = new WebRuntimeEnvironment();
        webRuntimeEnvironment.setClientIpAddress("0.0.0.0");
        webRuntimeEnvironment.setUserAgent(userAgentName);
        webRuntimeEnvironment.setLocaleCountry("US");
        webRuntimeEnvironment.setLocaleLanguage("en");

        request.setRuntimeEnvironment(webRuntimeEnvironment);

        return request;
    }

    private EventProcessingRequest createWebRequestWithGDPRParental() {
        EventProcessingRequest request = new EventProcessingRequest();

        request.setUserAttributes(createUserAttributes());
        request.setUserIdentities(createUserIdentities());
        request.setMpId("test_mp_id");

        ConsentState consentState = new ConsentState();
        Map<String, GDPRConsent> gdprConsentMap = new HashMap<>();
        gdprConsentMap.put("parental", createParentalGDPRConsent());
        request.setConsentState(consentState);

        request.setAccount(createWebAccount());

        WebRuntimeEnvironment webRuntimeEnvironment = new WebRuntimeEnvironment();
        webRuntimeEnvironment.setClientIpAddress("0.0.0.0");
        webRuntimeEnvironment.setUserAgent(userAgentName);
        webRuntimeEnvironment.setLocaleCountry("US");
        webRuntimeEnvironment.setLocaleLanguage("en");

        request.setRuntimeEnvironment(webRuntimeEnvironment);

        return request;
    }

    private EventProcessingRequest createWebRequestWithGDPRBoth() {
        EventProcessingRequest request = new EventProcessingRequest();

        request.setUserAttributes(createUserAttributes());
        request.setUserIdentities(createUserIdentities());
        request.setMpId("test_mp_id");

        ConsentState consentState = new ConsentState();
        Map<String, GDPRConsent> gdprConsentMap = new HashMap<>();
        gdprConsentMap.put("parental", createParentalGDPRConsent());
        gdprConsentMap.put("location_collection", createLocationGDPRConsent());
        request.setConsentState(consentState);

        request.setAccount(createWebAccount());

        WebRuntimeEnvironment webRuntimeEnvironment = new WebRuntimeEnvironment();
        webRuntimeEnvironment.setClientIpAddress("0.0.0.0");
        webRuntimeEnvironment.setUserAgent(userAgentName);
        webRuntimeEnvironment.setLocaleCountry("US");
        webRuntimeEnvironment.setLocaleLanguage("en");

        request.setRuntimeEnvironment(webRuntimeEnvironment);

        return request;
    }

    private ConsentState createConsentState() {
        ConsentState consentState = new ConsentState();
        consentState.setCCPA(createCCPAConsent());
        Map<String, GDPRConsent> gdprConsentMap = new HashMap<>();
        gdprConsentMap.put("parental", createParentalGDPRConsent());
        gdprConsentMap.put("location_collection", createLocationGDPRConsent());
        return consentState;
    }

    private CCPAConsent createCCPAConsent() {
        CCPAConsent ccpaConsent = new CCPAConsent();
        ccpaConsent.setConsented(false);
        return ccpaConsent;
    }

    private GDPRConsent createLocationGDPRConsent() {
        GDPRConsent gdprConsent = new GDPRConsent();
        gdprConsent.setConsented(false);
        return gdprConsent;
    }

    private GDPRConsent createParentalGDPRConsent() {
        GDPRConsent gdprConsent = new GDPRConsent();
        gdprConsent.setConsented(false);
        return gdprConsent;
    }

    private Account createWebAccount() {
        Account account = new Account();
        HashMap<String, String> accSettings = new HashMap<>();
        accSettings.put(AccountSettings.SETTINGS_ACCESS_TOKEN, "b674415eb3a55f1444edfadaebbeaddd98c65070");
        accSettings.put(AccountSettings.SETTINGS_EVENT_SOURCE, "web");
        accSettings.put(AccountSettings.SETTINGS_EVENT_SOURCE_ID, "CNP1VUBC77U6D6GPFSC0");
        accSettings.put(AccountSettings.SETTINGS_CONTENT_TYPE, "product");
        account.setAccountSettings(accSettings);
        return account;
    }

    private Account createAppAccount() {
        Account account = new Account();
        HashMap<String, String> accSettings = new HashMap<>();
        accSettings.put(AccountSettings.SETTINGS_ACCESS_TOKEN, "b674415eb3a55f1444edfadaebbeaddd98c65070");
        accSettings.put(AccountSettings.SETTINGS_EVENT_SOURCE, "app"); // TODO: need to check
        accSettings.put(AccountSettings.SETTINGS_EVENT_SOURCE_ID, "CNP1VUBC77U6D6GPFSC0");
        accSettings.put(AccountSettings.SETTINGS_CONTENT_TYPE, "product");
        account.setAccountSettings(accSettings);
        return account;
    }

    private Account createOfflineAccount() {
        Account account = new Account();
        HashMap<String, String> accSettings = new HashMap<>();
        accSettings.put(AccountSettings.SETTINGS_ACCESS_TOKEN, "0622960e65db5d201266004faa9038212e06cb2d");
        accSettings.put(AccountSettings.SETTINGS_EVENT_SOURCE, "offline");
        accSettings.put(AccountSettings.SETTINGS_EVENT_SOURCE_ID, "7346003541326053377");
        accSettings.put(AccountSettings.SETTINGS_CONTENT_TYPE, "product");
        account.setAccountSettings(accSettings);
        return account;
    }

    private List<UserIdentity> createUserIdentities() {
        List<UserIdentity> userIdentities = new ArrayList<>();
        userIdentities.add(new UserIdentity(UserIdentity.Type.EMAIL, Identity.Encoding.SHA256, "test1@test.com"));
        userIdentities.add(new UserIdentity(UserIdentity.Type.OTHER2, Identity.Encoding.SHA256, "test2@test.com"));
        userIdentities.add(new UserIdentity(UserIdentity.Type.OTHER3, Identity.Encoding.SHA256, "test3@test.com"));
        userIdentities.add(new UserIdentity(UserIdentity.Type.OTHER4, Identity.Encoding.SHA256, "test4@test.com"));
        userIdentities.add(new UserIdentity(UserIdentity.Type.OTHER5, Identity.Encoding.SHA256, "test5@test.com"));
        userIdentities.add(new UserIdentity(UserIdentity.Type.MOBILE_NUMBER, Identity.Encoding.SHA256, "+12345678900"));
        userIdentities.add(new UserIdentity(UserIdentity.Type.PHONE_NUMBER_2, Identity.Encoding.SHA256, "+12345678901"));
        userIdentities.add(new UserIdentity(UserIdentity.Type.PHONE_NUMBER_3, Identity.Encoding.SHA256, "+12345678902"));
        userIdentities.add(new UserIdentity(UserIdentity.Type.CUSTOMER, Identity.Encoding.SHA256, "test_external_id"));
        return userIdentities;
    }

    private HashMap<String, String> createUserAttributes() {
        HashMap<String, String> userAttributes = new HashMap<>();
        userAttributes.put("$firstname", "test_first_name");
        userAttributes.put("$lastname", "test_last_name");
        userAttributes.put("$city", "test_city");
        userAttributes.put("$country", "test_country");
        userAttributes.put("$state", "test_state");
        userAttributes.put("$zip", "test_zip");
        userAttributes.put("url", "https://www.test.com?ttclid=test_ttclid_url");
        userAttributes.put("ttclid", "test_ttclid_user_attr");
        userAttributes.put("ttp", "test_ttp_id");
        return userAttributes;
    }

    private HashMap<String, String> createOfflineEventAttributes() {
        HashMap<String, String> eventAttributes = new HashMap<>();
        eventAttributes.put("description", "test_description");
        eventAttributes.put("query", "test_query");
        return eventAttributes;
    }

    private HashMap<String, String> createWebEventAttributes() {
        HashMap<String, String> eventAttributes = new HashMap<>();
        eventAttributes.put("url", "https://www.tiktok.com");
        eventAttributes.put("referrer", "https://www.tiktok.com/redirect");
        eventAttributes.put("description", "test_description");
        eventAttributes.put("query", "test_query");
        return eventAttributes;
    }

    private List<Product> createProductList() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            product.setId(String.valueOf(i));
            product.setName("product_name_" + i);
            product.setCategory("product_category_" + i);
            product.setBrand("product_brand_" + i);
            product.setPrice(new BigDecimal(i * 10));
            product.setQuantity(new BigDecimal(i));
            products.add(product);
        }
        return products;
    }

    @Test
    public void testProcessRegistrationRequest() throws Exception {
        ModuleRegistrationResponse response = new TiktokEapiExtension().processRegistrationRequest(null);
        System.out.println(serializer.serialize(response));

        Permissions permissions = response.getPermissions();

        List<UserIdentityPermission> userIdentities = permissions.getUserIdentities();
        assertEquals(14, userIdentities.size(), "User Identity Permission Check: ");

        int email = 0, phone = 0, customer = 0;
        for (UserIdentityPermission userIdentity : userIdentities) {
            switch(userIdentity.getType()){
                case EMAIL:
                case OTHER2:
                case OTHER3:
                case OTHER4:
                case OTHER5:
                    email++;
                    break;
                case MOBILE_NUMBER:
                case PHONE_NUMBER_2:
                case PHONE_NUMBER_3:
                    phone++;
                    break;
                case CUSTOMER:
                case YAHOO:
                case MICROSOFT:
                case FACEBOOK:
                case TWITTER:
                case GOOGLE:
                    customer++;
                    break;

            }
        }

        assertEquals(5, email, "TikTok eAPI Extension should register 5 email types: ");
        assertEquals(3, phone, "TikTok eAPI Extension should register 3 phone types: ");
        assertEquals(6, customer, "TikTok eAPI Extension should register 1 customer type:");

        List<DeviceIdentityPermission> deviceIdentityPermissions = permissions.getDeviceIdentities();
        assertEquals(12, deviceIdentityPermissions.size(), "Device Identity Permission Check: ");

        boolean idfa = false, idfv = false, androidId = false, gaId = false;
        for (DeviceIdentityPermission deviceIdentityPermission : deviceIdentityPermissions) {
            switch (deviceIdentityPermission.getType()) {
                case IOS_ADVERTISING_ID:
                    idfa = true;
                    break;
                case IOS_VENDOR_ID:
                    idfv = true;
                    break;
                case ANDROID_ID:
                    androidId = true;
                    break;
                case GOOGLE_ADVERTISING_ID:
                    gaId = true;
                    break;
            }
        }
        assertTrue(idfa, "TikTok eAPI Extension should register IDFA: ");
        assertTrue(idfv, "TikTok eAPI Extension should register IDFV: ");
        assertTrue(androidId, "TikTok eAPI Extension should register Android ID: ");
        assertTrue(gaId, "TikTok eAPI Extension should register GAID: ");

        List<Setting> accountSettings = response.getEventProcessingRegistration().getAccountSettings();
        assertEquals(5, accountSettings.size(), "Account Settings Check: ");

        boolean accessToken = false, eventSource = false, eventSourceId = false, contentType = false;
        for (Setting accountSetting : accountSettings) {
            switch (accountSetting.getId()) {
                case AccountSettings.SETTINGS_ACCESS_TOKEN:
                    accessToken = true;
                    break;
                case AccountSettings.SETTINGS_EVENT_SOURCE:
                    eventSource = true;
                    break;
                case AccountSettings.SETTINGS_EVENT_SOURCE_ID:
                    eventSourceId = true;
                    break;
                case AccountSettings.SETTINGS_CONTENT_TYPE:
                    contentType = true;
                    break;
            }
        }
        assertTrue(accessToken, "TikTok eAPI Extension should accept \"Access Token\" setting: ");
        assertTrue(eventSource, "TikTok eAPI Extension should accept \"Event Source\" setting: ");
        assertTrue(eventSourceId, "TikTok eAPI Extension should accept \"Event Source ID\" setting: ");
        assertTrue(contentType, "TikTok eAPI Extension should accept \"Content Type\" setting: ");

        List<Event.Type> eventTypes = response.getEventProcessingRegistration().getSupportedEventTypes();
        assertTrue(eventTypes.contains(Event.Type.CUSTOM_EVENT), "TikTok eAPI Extension should support custom events: ");
        assertTrue(eventTypes.contains(Event.Type.SCREEN_VIEW), "TikTok eAPI Extension should support screen view events: ");
        assertTrue(eventTypes.contains(Event.Type.PUSH_SUBSCRIPTION), "TikTok eAPI Extension should support push subscription events: ");
        assertTrue(eventTypes.contains(Event.Type.PRODUCT_ACTION), "TikTok eAPI Extension should support product action events: ");
        assertTrue(eventTypes.contains(Event.Type.PROMOTION_ACTION), "TikTok eAPI Extension should support promotion action events: ");
        assertTrue(eventTypes.contains(Event.Type.IMPRESSION), "TikTok eAPI Extension should support impression events: ");
        assertTrue(eventTypes.contains(Event.Type.ATTRIBUTION), "TikTok eAPI Extension should support attribution events: ");
    }

    @Test
    public void testProcessEventProcessingRequest() throws Exception {
        TiktokEapiExtension tiktokEapiExtension = new TiktokEapiExtension();
        EventProcessingRequest request = createWebRequest();

        List<Event> events = new LinkedList<>();
        Event event1 = new ScreenViewEvent();
        Event event2 = new ScreenViewEvent();
        Event event3 = new ScreenViewEvent();
        Event event4 = new ScreenViewEvent();
        Event event5 = new ScreenViewEvent();

        event1.setTimestamp(3);
        event2.setTimestamp(1);
        event3.setTimestamp(4);
        event4.setTimestamp(5);
        event5.setTimestamp(2);

        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(event4);
        events.add(event5);

        request.setEvents(events);

        tiktokEapiExtension.processEventProcessingRequest(request);

        assertEquals(1, request.getEvents().get(0).getTimestamp(), "Events should have been in order: ");
        assertEquals(2, request.getEvents().get(1).getTimestamp(), "Events should have been in order: ");
        assertEquals(3, request.getEvents().get(2).getTimestamp(), "Events should have been in order: ");
        assertEquals(4, request.getEvents().get(3).getTimestamp(), "Events should have been in order: ");
        assertEquals(5, request.getEvents().get(4).getTimestamp(), "Events should have been in order: ");

    }

    @Test
    public void testProcessScreenViewEvent() throws Exception {
        TiktokEapiExtension tiktokEapiExtension = new TiktokEapiExtension();
        ScreenViewEvent event = new ScreenViewEvent();
        event.setRequest(createWebRequest());

        HashMap<String, String> eventAttributes = new HashMap<>();
        eventAttributes.put("url", "https://www.tiktok.com");
        eventAttributes.put("referrer", "https://www.tiktok.com/redirect");
        event.setAttributes(eventAttributes);

        tiktokEapiExtension.processScreenViewEvent(event);
    }

    @Test
    public void testProcessImpressionViewEvent() throws Exception {
        TiktokEapiExtension tiktokEapiExtension = new TiktokEapiExtension();
        ImpressionEvent event = new ImpressionEvent();
        event.setRequest(createWebRequest());

        Impression impression = new Impression();
        impression.setProducts(createProductList());
        event.setImpressions(Arrays.asList(impression));
        event.setAttributes(createWebEventAttributes());
        event.setAttributes(createWebEventAttributes());

        tiktokEapiExtension.processImpressionEvent(event);
    }

    @Test
    public void testProcessPushSubscriptionEvent() throws Exception {
        TiktokEapiExtension tiktokEapiExtension = new TiktokEapiExtension();
        PushSubscriptionEvent event = new PushSubscriptionEvent();
        event.setRequest(createWebRequest());

        for(PushSubscriptionEvent.Action action : PushSubscriptionEvent.Action.values()) {
            event.setAction(action);
            tiktokEapiExtension.processPushSubscriptionEvent(event);
            TimeUnit.SECONDS.sleep(1);
        }
    }

    @Test
    public void testProcessProductActionWebEvent() throws Exception {
        TiktokEapiExtension tiktokEapiExtension = new TiktokEapiExtension();

        ProductActionEvent event = new ProductActionEvent();
        event.setRequest(createWebRequestWithGDPRLocation());
        event.setAttributes(createWebEventAttributes());
        event.setProducts(createProductList());
        event.setCurrencyCode("USD");
        event.setTotalAmount(new BigDecimal(500));

        for(ProductActionEvent.Action action : ProductActionEvent.Action.values()) {
            event.setAction(action);
            tiktokEapiExtension.processProductActionEvent(event);
            TimeUnit.SECONDS.sleep(1);
        }

//        for (int i = 0; i < 5; i++) {
//            ProductActionEvent event = new ProductActionEvent();
//            switch (i) {
//                case 0:
//                    event.setRequest(createWebRequest());
//                    break;
//                case 1:
//                    event.setRequest(createWebRequestWithCCPA());
//                    break;
//                case 2:
//                    event.setRequest(createWebRequestWithGDPRLocation());
//                    break;
//                case 3:
//                    event.setRequest(createWebRequestWithGDPRParental());
//                    break;
//                case 4:
//                    event.setRequest(createWebRequestWithGDPRBoth());
//                    break;
//            }
//            event.setAttributes(createWebEventAttributes());
//            event.setProducts(createProductList());
//            event.setCurrencyCode("USD");
//            event.setTotalAmount(new BigDecimal(500));
//
//            for(ProductActionEvent.Action action : ProductActionEvent.Action.values()) {
//                event.setAction(action);
//                tiktokEapiExtension.processProductActionEvent(event);
//                TimeUnit.SECONDS.sleep(1);
//            }
//        }



    }

    @Test
    public void testProcessProductActionWebEventNonWebEnv() throws Exception {
        TiktokEapiExtension tiktokEapiExtension = new TiktokEapiExtension();

        ProductActionEvent event = new ProductActionEvent();
        event.setRequest(createGenericEnvRequest());
        event.setAttributes(createWebEventAttributes());
        event.setProducts(createProductList());
        event.setCurrencyCode("USD");
        event.setTotalAmount(new BigDecimal(500));

        for(ProductActionEvent.Action action : ProductActionEvent.Action.values()) {
            event.setAction(action);
            tiktokEapiExtension.processProductActionEvent(event);
            TimeUnit.SECONDS.sleep(1);
        }

    }

    @Test
    public void testProcessProductActionOfflineEvent() throws Exception {
        TiktokEapiExtension tiktokEapiExtension = new TiktokEapiExtension();

        ProductActionEvent event = new ProductActionEvent();
        event.setRequest(createUnknownEnvRequest());
        event.setAttributes(createOfflineEventAttributes());
        event.setProducts(createProductList());
        event.setCurrencyCode("USD");
        event.setTotalAmount(new BigDecimal(500));

        for(ProductActionEvent.Action action : ProductActionEvent.Action.values()) {
            event.setAction(action);
            tiktokEapiExtension.processProductActionEvent(event);
            TimeUnit.SECONDS.sleep(1);
        }

    }

    @Test
    public void testProcessPromotionActionEvent() throws Exception {
        TiktokEapiExtension tiktokEapiExtension = new TiktokEapiExtension();

        PromotionActionEvent event = new PromotionActionEvent();
        event.setRequest(createWebRequest());
        event.setAttributes(createWebEventAttributes());

        Promotion promotion1 = new Promotion();
        promotion1.setId("test_promo_id");
        promotion1.setName("test_promo_name");
        promotion1.setCreative("test_promo_creative");
        promotion1.setPosition("test_promo_position");

        Promotion promotion2 = new Promotion();
        promotion2.setId("test_promo_id");
        promotion2.setName("test_promo_name");
        promotion2.setCreative("test_promo_creative");
        promotion2.setPosition("test_promo_position");

        event.setPromotions(Arrays.asList(promotion1,promotion2));

        for(PromotionActionEvent.Action action : PromotionActionEvent.Action.values()) {
            event.setAction(action);
            tiktokEapiExtension.processPromotionActionEvent(event);
            TimeUnit.SECONDS.sleep(1);
        }

    }

    @Test
    public void testProcessCustomWebEvent() throws Exception {
        TiktokEapiExtension tiktokEapiExtension = new TiktokEapiExtension();

        CustomEvent event = new CustomEvent();
        event.setRequest(createWebRequest());

        event.setAttributes(createWebEventAttributes());

        for(CustomEvent.CustomType type : CustomEvent.CustomType.values()) {
            event.setCustomType(type);
            event.setName(type.toString());
            tiktokEapiExtension.processCustomEvent(event);
            TimeUnit.SECONDS.sleep(1);
        }

    }

    @Test
    public void testProcessCustomWebEventNonWebEnv() throws Exception {
        TiktokEapiExtension tiktokEapiExtension = new TiktokEapiExtension();

        CustomEvent event = new CustomEvent();
        event.setRequest(createGenericEnvRequest());

        event.setAttributes(createWebEventAttributes());

        for(CustomEvent.CustomType type : CustomEvent.CustomType.values()) {
            event.setCustomType(type);
            event.setName(type.toString());
            tiktokEapiExtension.processCustomEvent(event);
            TimeUnit.SECONDS.sleep(1);
        }

    }
}