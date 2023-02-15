package kz.shop.test.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.shop.test.utils.CloseBannerHelper;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    CloseBannerHelper closeBannerHelper = new CloseBannerHelper();

    private final ElementsCollection
            horizontalMenu = $$(".bx-inclinkstop-container"),
            sidebar = $$(".bx-nav-1-lvl"),
            catalogItemTitle = $$(".bx_catalog_tile_title"),
            widgetTitle = $$(".widgettitle"),
            recommenderBlockTitle = $$(".recommender-block-title"),
            footerHeaderMenu = $$(".footer_header_btn");

    private final SelenideElement
            catalogPageTitle = $("#pagetitle"),
            authButton = $("#btn_show_auth"),
            personalSection = $("a[href=\"/personal/\"]"),
            personalSectionTitle = $("#pagetitle");


    @Step("Проверяем горизонтальное меню")
    public MainPage verifyHorizontalMenu(String items) {
        horizontalMenu.filter(visible).shouldHave(CollectionCondition.texts(items));
        return this;
    }

    @Step("Проверяем боковое меню и входящие категории")
    public MainPage verifySidebarMenu(String items, List<String> categories) {
        sidebar.find(text(items)).click();
        closeBannerHelper.closeBannerPromotions();
        catalogPageTitle.shouldHave(text(items));
        catalogItemTitle.filter(visible).shouldHave(CollectionCondition.texts(categories));
        return this;
    }

    @Step("Проверяем наличие разделов на главной странице")
    public MainPage verifySectionTitleText(String value) {
        recommenderBlockTitle.shouldHave(CollectionCondition.itemWithText(value));
        return this;
    }

    @Step("Проверяем пукты меню футера и входящие в них подпункты")
    public MainPage verifyFooter(String header) {
        footerHeaderMenu.find(text(header)).shouldHave(appear);
        return this;
    }

    @Step("Переходим к форме авторизации")
    public MainPage goToAuthModalForm(String value){
        authButton.shouldHave(text(value)).click();
        return this;
    }

    @Step("Выполняем проверки после авторизации")
    public MainPage checkAfterLogin(String ps, String psTitle){
        personalSection.shouldHave(text(ps)).click();
        personalSectionTitle.shouldHave(text(psTitle));
        return this;
    }
}
