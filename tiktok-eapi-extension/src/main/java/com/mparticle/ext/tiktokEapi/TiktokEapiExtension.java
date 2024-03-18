package com.mparticle.ext.tiktokEapi;

import com.mparticle.ext.tiktokEapi.eventProcessor.*;
import com.mparticle.ext.tiktokEapi.utils.AccountSettings;
import com.mparticle.sdk.model.eventprocessing.notification.CCPAConsentStateNotification;
import com.mparticle.sdk.model.eventprocessing.notification.GDPRConsentStateNotification;
import com.mparticle.sdk.MessageProcessor;
import com.mparticle.sdk.model.audienceprocessing.AudienceMembershipChangeRequest;
import com.mparticle.sdk.model.audienceprocessing.AudienceMembershipChangeResponse;
import com.mparticle.sdk.model.audienceprocessing.AudienceSubscriptionRequest;
import com.mparticle.sdk.model.audienceprocessing.AudienceSubscriptionResponse;
import com.mparticle.sdk.model.dsrprocessing.DsrProcessingRequest;
import com.mparticle.sdk.model.dsrprocessing.DsrProcessingResponse;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.registration.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.*;


/**
 * Arbitrary sample extension. Typically, this class would interface
 * with another library to connect to a 3rd-party API.
 * <p>
 * The two big responsibilities of a MessageProcessor are:
 * 1. Describe its capabilities and settings to mParticle
 * 2. Process batches of data sent from mParticle, typically to translate and send somewhere else.
 */
public class TiktokEapiExtension extends MessageProcessor {

    private final Logger logger = LogManager.getLogger(TiktokEapiExtension.class);

    //this name will show up in the mParticle UI
    public static final String NAME = "TikTok Events API";

    @Override
    public ModuleRegistrationResponse processRegistrationRequest(ModuleRegistrationRequest request) {

        logger.info("processRegistrationRequest received.");

        //Set the permissions - the user, device and partner identities that this service can have access to
        Permissions permissions = new Permissions()
            .setAllowAccessIpAddress(true)
            .setAllowAccessHttpUserAgent(true)
            .setAllowConsentState(true)
            .setAllowAccessLocation(true)
            .setAllowAccessDeviceApplicationStamp(true)
            .setAllowDeviceInformation(true)
            .setAllowAccessMpid(true)
            .setUserIdentities(Arrays.asList(
                    new UserIdentityPermission(UserIdentity.Type.FACEBOOK, Identity.Encoding.RAW),
                    new UserIdentityPermission(UserIdentity.Type.MICROSOFT, Identity.Encoding.RAW),
                    new UserIdentityPermission(UserIdentity.Type.TWITTER, Identity.Encoding.RAW),
                    new UserIdentityPermission(UserIdentity.Type.GOOGLE, Identity.Encoding.RAW),
                    new UserIdentityPermission(UserIdentity.Type.YAHOO, Identity.Encoding.RAW),
                    new UserIdentityPermission(UserIdentity.Type.MOBILE_NUMBER, Identity.Encoding.RAW),
                    new UserIdentityPermission(UserIdentity.Type.PHONE_NUMBER_2, Identity.Encoding.RAW),
                    new UserIdentityPermission(UserIdentity.Type.PHONE_NUMBER_3, Identity.Encoding.RAW),
                    new UserIdentityPermission(UserIdentity.Type.EMAIL, Identity.Encoding.RAW),
                    new UserIdentityPermission(UserIdentity.Type.OTHER2, Identity.Encoding.RAW),
                    new UserIdentityPermission(UserIdentity.Type.OTHER3, Identity.Encoding.RAW),
                    new UserIdentityPermission(UserIdentity.Type.OTHER4, Identity.Encoding.RAW),
                    new UserIdentityPermission(UserIdentity.Type.OTHER5, Identity.Encoding.RAW),
                    new UserIdentityPermission(UserIdentity.Type.CUSTOMER, Identity.Encoding.RAW)))
            .setDeviceIdentities(Arrays.asList(
                    new DeviceIdentityPermission(DeviceIdentity.Type.IOS_ADVERTISING_ID, Identity.Encoding.RAW),
                    new DeviceIdentityPermission(DeviceIdentity.Type.IOS_VENDOR_ID, Identity.Encoding.RAW),
                    new DeviceIdentityPermission(DeviceIdentity.Type.ANDROID_ID, Identity.Encoding.RAW),
                    new DeviceIdentityPermission(DeviceIdentity.Type.GOOGLE_CLOUD_MESSAGING_TOKEN, Identity.Encoding.RAW),
                    new DeviceIdentityPermission(DeviceIdentity.Type.IOS_ADVERTISING_ID, Identity.Encoding.RAW),
                    new DeviceIdentityPermission(DeviceIdentity.Type.APPLE_PUSH_NOTIFICATION_TOKEN, Identity.Encoding.RAW),
                    new DeviceIdentityPermission(DeviceIdentity.Type.ROKU_ADVERTISING_ID, Identity.Encoding.RAW),
                    new DeviceIdentityPermission(DeviceIdentity.Type.ROKU_PUBLISHER_ID, Identity.Encoding.RAW),
                    new DeviceIdentityPermission(DeviceIdentity.Type.MICROSOFT_ADVERTISING_ID, Identity.Encoding.RAW),
                    new DeviceIdentityPermission(DeviceIdentity.Type.MICROSOFT_PUBLISHER_ID, Identity.Encoding.RAW),
                    new DeviceIdentityPermission(DeviceIdentity.Type.FIRE_ADVERTISING_ID, Identity.Encoding.RAW),
                    new DeviceIdentityPermission(DeviceIdentity.Type.GOOGLE_ADVERTISING_ID, Identity.Encoding.RAW)))
            .setPartnerIdentities(Arrays.asList(
                    new PartnerIdentityPermission("partnerIdentity", Identity.Encoding.RAW)
            ));

        logger.info("permissions: ", permissions);

        //the extension needs to define the settings it needs in order to connect to its respective service(s).
        //you can using different settings for Event Processing vs. Audience Processing, but in this case
        //we'll just use the same object, specifying that only an API key is required for each.
        List<Setting> processorSettings = new ArrayList<>();
        processorSettings.add(
                new TextSetting(AccountSettings.SETTINGS_ACCESS_TOKEN, "Access Token")
                        .setIsRequired(true)
                        .setIsConfidential(false)
                        .setDescription("Access Token obtained from TikTok Events Manager.")
        );
        processorSettings.add(
                new TextSetting(AccountSettings.SETTINGS_EVENT_SOURCE, "Event Source")
                        .setIsRequired(true)
                        .setIsConfidential(false)
                        .setDescription("This field is used to specify the type of events you are uploading through Events API. Possible values are: \"web\", \"app\", \"offline\", and \"crm\".")
        );
        processorSettings.add(
                new TextSetting(AccountSettings.SETTINGS_EVENT_SOURCE_ID, "Event Source ID")
                        .setIsRequired(true)
                        .setIsConfidential(false)
                        .setDescription("An Event Source ID that is used to track events.")
        );
        processorSettings.add(
                new TextSetting(AccountSettings.SETTINGS_CONTENT_TYPE, "Content Type Mapping")
                        .setIsRequired(true)
                        .setIsConfidential(false)
                        .setDescription("The type of content in the event. Possible values are: \"product\", and \"product_group\".")
        );
        processorSettings.add(
                new BooleanSetting(AccountSettings.SETTINGS_LDU, "Limited Data Use")
                        .setDescription("In order to help facilitate advertiser's compliance with the right to opt-out of sale and sharing of personal data under certain U.S. state privacy laws, TikTok offers a Limited Data Use (\"LDU\") feature.")
        );

        logger.info("processorSettings: ", processorSettings);

        //specify the supported event types. you should override the parent MessageProcessor methods
        //that correlate to each of these event types.
        // TODO: default mappings
        List<Event.Type> supportedEventTypes = Arrays.asList(
                Event.Type.CUSTOM_EVENT,
                Event.Type.SCREEN_VIEW,
                Event.Type.PUSH_SUBSCRIPTION,
                Event.Type.PRODUCT_ACTION,
                Event.Type.PROMOTION_ACTION,
                Event.Type.IMPRESSION,
                Event.Type.ATTRIBUTION
        );

        logger.info("supportedEventTypes: ", supportedEventTypes);

        //this extension only supports event data coming from Android and iOS devices
        List<RuntimeEnvironment.Type> environments = Arrays.asList(
                RuntimeEnvironment.Type.UNKNOWN,
                RuntimeEnvironment.Type.ANDROID,
                RuntimeEnvironment.Type.IOS,
                RuntimeEnvironment.Type.TVOS,
                RuntimeEnvironment.Type.MOBILEWEB,
                RuntimeEnvironment.Type.ROKU,
                RuntimeEnvironment.Type.XBOX,
                RuntimeEnvironment.Type.FIRETV,
                RuntimeEnvironment.Type.ALEXA,
                RuntimeEnvironment.Type.SMARTTV
        );

        logger.info("environments: ", environments);

        //finally use all of the above to assemble the EventProcessingRegistration object and set it in the response
        EventProcessingRegistration eventProcessingRegistration = new EventProcessingRegistration()
                .setSupportedRuntimeEnvironments(environments)
                .setAccountSettings(processorSettings)
                .setSupportedEventTypes(supportedEventTypes);

        logger.info("eventProcessingRegistration: ", eventProcessingRegistration);

        logger.info("Sending ModuleRegistrationResponse.");

        String extensionDescription = "TikTok's Events API is a secure server-to-server integration that allows advertisers to share the actions customers take on their websites, apps, offline or CRM systems directly with TikTok.";
        return new ModuleRegistrationResponse(NAME, "1.0")
                .setDescription(extensionDescription)
                .setEventProcessingRegistration(eventProcessingRegistration)
                .setPermissions(permissions);
    }

    /**
     * When a MessageProcessor is given a batch of data/events, it will first call this method.
     * This is a good time to do some setup. For example since a given batch will all be for the same device,
     * you could contact the server once here and make sure that that device/user exists in the system, rather than
     * doing that every time one of the more specific methods (ie processCustomEvent) is called.
     */
    @Override
    public EventProcessingResponse processEventProcessingRequest(EventProcessingRequest request) throws IOException {
        //do some setup, then call super. if you don't call super, you'll effectively short circuit
        //the whole thing, which isn't really fun for anyone.

        logger.info("processEventProcessingRequest sending PageView event.");
        Collections.sort(
                request.getEvents(),
                (a, b) -> a.getTimestamp() > b.getTimestamp() ? 1 : a.getTimestamp() == b.getTimestamp() ? 0 : -1
        );

        return super.processEventProcessingRequest(request);
    }

    @Override
    public void processGDPRConsentStateNotification(GDPRConsentStateNotification notification) throws IOException {
        super.processGDPRConsentStateNotification(notification); // TODO: update
    }

    @Override
    public void processCCPAConsentStateNotification(CCPAConsentStateNotification notification) throws IOException {
        super.processCCPAConsentStateNotification(notification); // TODO: update
    }

    @Override
    public void processAttributionEvent(AttributionEvent event) throws IOException {

        AttributionEventProcessor attributionEventProcessor = new AttributionEventProcessor(event);
        attributionEventProcessor.execute();

        super.processAttributionEvent(event);
    }

    @Override
    public void processImpressionEvent(ImpressionEvent event) throws IOException {

//        PageViewEventProcessor pageViewEventProcessor = new PageViewEventProcessor(event);
//        pageViewEventProcessor.execute();

        ImpressionEventProcessor impressionEventProcessor = new ImpressionEventProcessor(event);
        impressionEventProcessor.execute();

        super.processImpressionEvent(event);
    }

    @Override
    public void processPromotionActionEvent(PromotionActionEvent event) throws IOException {

        PromotionActionEventProcessor promotionActionEventProcessor = new PromotionActionEventProcessor(event);
        promotionActionEventProcessor.execute();

        super.processPromotionActionEvent(event);
    }

    @Override
    public void processProductActionEvent(ProductActionEvent event) throws IOException {

//        PageViewEventProcessor pageViewEventProcessor = new PageViewEventProcessor(event);
//        pageViewEventProcessor.execute();

        ProductActionEventProcessor productActionEventProcessor = new ProductActionEventProcessor(event);
        productActionEventProcessor.execute();

        super.processProductActionEvent(event);
    }

    @Override
    public void processPushMessageOpenEvent(PushMessageOpenEvent event) throws IOException {

        PushMessageOpenEventProcessor pushMessageOpenEventProcessor = new PushMessageOpenEventProcessor(event);
        pushMessageOpenEventProcessor.execute();

        super.processPushMessageOpenEvent(event);
    }

    @Override
    public void processApplicationStateTransitionEvent(ApplicationStateTransitionEvent event) throws IOException {

        ApplicationStateTransitionEventProcessor applicationStateTransitionEventProcessor = new ApplicationStateTransitionEventProcessor(event);
        applicationStateTransitionEventProcessor.execute();

        super.processApplicationStateTransitionEvent(event);
    }

    @Override
    public void processSessionStartEvent(SessionStartEvent event) throws IOException {

        SessionStartEventProcessor sessionStartEventProcessor = new SessionStartEventProcessor(event);
        sessionStartEventProcessor.execute();

        super.processSessionStartEvent(event);
    }

    @Override
    public void processSessionEndEvent(SessionEndEvent event) throws IOException {

        SessionEndEventProcessor sessionEndEventProcessor = new SessionEndEventProcessor(event);
        sessionEndEventProcessor.execute();

        super.processSessionEndEvent(event);
    }

    @Override
    public void processCustomEvent(CustomEvent event) throws IOException {

//        PageViewEventProcessor pageViewEventProcessor = new PageViewEventProcessor(event);
//        pageViewEventProcessor.execute();

        CustomEventProcessor customEventProcessor = new CustomEventProcessor(event);
        customEventProcessor.execute();

        super.processCustomEvent(event);
    }

    @Override
    public void processScreenViewEvent(ScreenViewEvent event) throws IOException {

//        PageViewEventProcessor pageViewEventProcessor = new PageViewEventProcessor(event);
//        pageViewEventProcessor.execute();

        ScreenViewEventProcessor screenViewEventProcessor = new ScreenViewEventProcessor(event);
        screenViewEventProcessor.execute();

        super.processScreenViewEvent(event);
    }

    @Override
    public void processErrorEvent(ErrorEvent event) throws IOException {

        ErrorEventProcessor errorEventProcessor = new ErrorEventProcessor(event);
        errorEventProcessor.execute();

        super.processErrorEvent(event);
    }

    @Override
    public void processPrivacySettingChangeEvent(PrivacySettingChangeEvent event) throws IOException {

        PrivacySettingChangeEventProcessor privacySettingChangeEventProcessor = new PrivacySettingChangeEventProcessor(event);
        privacySettingChangeEventProcessor.execute();

        super.processPrivacySettingChangeEvent(event);
    }

    @Override
    public void processUserAttributeChangeEvent(UserAttributeChangeEvent event) throws IOException {

        UserAttributeChangeEventProcessor userAttributeChangeEventProcessor = new UserAttributeChangeEventProcessor(event);
        userAttributeChangeEventProcessor.execute();

        super.processUserAttributeChangeEvent(event);
    }

    @Override
    public void processPushMessageReceiptEvent(PushMessageReceiptEvent event) throws IOException {

        PushMessageReceiptEventProcessor pushMessageReceiptEventProcessor = new PushMessageReceiptEventProcessor(event);
        pushMessageReceiptEventProcessor.execute();

        super.processPushMessageReceiptEvent(event);
    }

    @Override
    public void processPushSubscriptionEvent(PushSubscriptionEvent event) throws IOException {

        PushSubscriptionEventProcessor pushSubscriptionEventProcessor = new PushSubscriptionEventProcessor(event);
        pushSubscriptionEventProcessor.execute();

        super.processPushSubscriptionEvent(event);
    }

    @Override
    public void processUserIdentityChangeEvent(UserIdentityChangeEvent event) throws IOException {

        UserIdentityChangeEventProcessor userIdentityChangeEventProcessor = new UserIdentityChangeEventProcessor(event);
        userIdentityChangeEventProcessor.execute();

        super.processUserIdentityChangeEvent(event);
    }

    @Override
    public AudienceMembershipChangeResponse processAudienceMembershipChangeRequest(AudienceMembershipChangeRequest request) throws IOException {
        return super.processAudienceMembershipChangeRequest(request); // TODO: update
    }

    @Override
    public AudienceSubscriptionResponse processAudienceSubscriptionRequest(AudienceSubscriptionRequest request) throws IOException {
        return super.processAudienceSubscriptionRequest(request); // TODO: update
    }

    @Override
    public DsrProcessingResponse processDsrProcessingRequest(DsrProcessingRequest request) throws IOException {
        return super.processDsrProcessingRequest(request); // TODO: update
    }
}
