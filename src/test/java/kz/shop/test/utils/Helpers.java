package kz.shop.test.utils;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class Helpers {

    @Step("Принудительно закрываем баннеры")
    public void closeBanner(){
        executeJavaScript("$('#onesignal-slidedown-dialog').remove()");
        executeJavaScript("$('#regionsPopup').remove()");
    }

    @Step("Принудительно закрываем рекламный баннеры")
    public void closeBannerPromotions(){
        executeJavaScript("$('#onesignal-slidedown-dialog').remove()");
    }
}
