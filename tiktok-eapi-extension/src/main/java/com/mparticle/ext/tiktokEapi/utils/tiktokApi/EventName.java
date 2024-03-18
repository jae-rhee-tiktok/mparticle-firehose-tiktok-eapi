package com.mparticle.ext.tiktokEapi.utils.tiktokApi;

public class EventName {
    public enum Name {

        PageView,
        AddPaymentInfo,
        AddToCart,
        AddToWishlist,
        ClickButton,
        CompletePayment,
        CompleteRegistration,
        Contact,
        Download,
        InitiateCheckout,
        PlaceAnOrder,
        Search,
        SubmitForm,
        Subscribe,
        ViewContent;

        Name() {
        }

        public String toString() {
            return this.name();
        }
    }

    public enum AppName {

        AchieveLevel,
        AddPaymentInfo,
        AddToCart,
        AddToWishlist,
        Checkout,
        CompleteTutorial,
        CreateGroup,
        CreateRole,
        GenerateLead,
        InAppADClick,
        InAppAdImpr,
        InstallApp,
        JoinGroup,
        LaunchAPP,
        LoanApplication,
        LoanApproval,
        LoanDisbursal,
        Login,
        Purchase,
        Rate,
        Registration,
        Search,
        SpendCredits,
        StartTrial,
        Subscribe,
        UnlockAchievement,
        ViewContent;

        AppName() {
        }

        public String toString() {
            return this.name();
        }
    }
}
