package kz.shop.test.utils;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class CloseBannerHelper {

    @Step("Принудительно закрываем баннеры")
    public void closeBanner(){
        $("#onesignal-slidedown-dialog").shouldBe(appear);
        $("#regionsPopup").shouldBe(appear);
        executeJavaScript("$('#onesignal-slidedown-dialog').remove()");
        executeJavaScript("$('#regionsPopup').remove()");
    }

    @Step("Принудительно закрываем рекламный баннеры")
    public void closeBannerPromotions(){
        $("#onesignal-slidedown-dialog").shouldBe(appear);
        executeJavaScript("$('#onesignal-slidedown-dialog').remove()");
    }
}
