package com.mparticle.ext.tiktokEapi;

import com.mparticle.ext.tiktokEapi.utils.AccountSettings;
import com.mparticle.sdk.model.MessageSerializer;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.registration.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.ConsoleHandler;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests are fun! Here's an empty test class to get you started.
 *
 * These tests can be run by executing the 'test' gradle task via `./gradlew test`
 */
public class TikTokEapiExtensionTest  {

    private Logger logger = LogManager.getLogger(TikTokEapiExtensionTest.class);

    MessageSerializer serializer = new MessageSerializer();

    private EventProcessingRequest createWebRequest() {
        EventProcessingRequest request = new EventProcessingRequest();

        request.setUserAttributes(createWebUserAttributes());

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

        request.setUserIdentities(userIdentities);

        request.setMpId("test_mp_id");

        Account account = new Account();
        HashMap<String, String> accSettings = new HashMap<>();
        accSettings.put(AccountSettings.SETTINGS_ACCESS_TOKEN, "b674415eb3a55f1444edfadaebbeaddd98c65070");
        accSettings.put(AccountSettings.SETTINGS_EVENT_SOURCE, "web");
        accSettings.put(AccountSettings.SETTINGS_EVENT_SOURCE_ID, "CNP1VUBC77U6D6GPFSC0");
        accSettings.put(AccountSettings.SETTINGS_CONTENT_TYPE, "product");
        account.setAccountSettings(accSettings);

        request.setAccount(account);

        WebRuntimeEnvironment webRuntimeEnvironment = new WebRuntimeEnvironment();
        webRuntimeEnvironment.setClientIpAddress("0.0.0.0");
        webRuntimeEnvironment.setUserAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
        webRuntimeEnvironment.setLocaleCountry("US");
        webRuntimeEnvironment.setLocaleLanguage("en");

        request.setRuntimeEnvironment(webRuntimeEnvironment);

        return request;
    }

    private HashMap<String, String> createWebUserAttributes() {
        HashMap<String, String> userAttributes = new HashMap<>();
        userAttributes.put("$firstname", "test_first_name");
        userAttributes.put("$lastname", "test_last_name");
        userAttributes.put("$city", "test_city");
        userAttributes.put("$country", "test_country");
        userAttributes.put("$state", "test_state");
        userAttributes.put("$zip", "test_zip");
        return userAttributes;
    }

    private HashMap<String, String> createWebEventAttributes() {
        HashMap<String, String> eventAttributes = new HashMap<>();
        eventAttributes.put("url", "https://www.tiktok.com?ttclid=test_ttclid");
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
        assertEquals(9, userIdentities.size(), "User Identity Permission Check: ");

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
                    customer++;
                    break;

            }
        }

        assertEquals(5, email, "TikTok eAPI Extension should register 5 email types: ");
        assertEquals(3, phone, "TikTok eAPI Extension should register 3 phone types: ");
        assertEquals(1, customer, "TikTok eAPI Extension should register 1 customer type:");

        List<DeviceIdentityPermission> deviceIdentityPermissions = permissions.getDeviceIdentities();
        assertEquals(4, deviceIdentityPermissions.size(), "Device Identity Permission Check: ");

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
        assertEquals(4, accountSettings.size(), "Account Settings Check: ");

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
    public void testProcessPushMessageReceiptEvent() throws Exception {

    }

    @Test
    public void testProcessPushSubscriptionEvent() throws Exception {

    }

    @Test
    public void testProcessUserIdentityChangeEvent() throws Exception {

    }

    @Test
    public void testProcessProductActionEvent() throws Exception {
        TiktokEapiExtension tiktokEapiExtension = new TiktokEapiExtension();

        ProductActionEvent event = new ProductActionEvent();
        event.setRequest(createWebRequest());

        event.setAttributes(createWebEventAttributes());

        event.setProducts(createProductList());
        event.setCurrencyCode("USD");
        event.setTotalAmount(new BigDecimal(500));

//        event.setAction(ProductActionEvent.Action.PURCHASE);
//
//        tiktokEapiExtension.processProductActionEvent(event);

        for(ProductActionEvent.Action action : ProductActionEvent.Action.values()) {
            event.setAction(action);
            tiktokEapiExtension.processProductActionEvent(event);
        }

    }

    @Test
    public void testProcessCustomEvent() throws Exception {

    }

    @Test
    public void testProcessAudienceMembershipChangeRequest() throws Exception {

    }

    @Test
    public void testProcessAudienceSubscriptionRequest() throws Exception {

    }
}