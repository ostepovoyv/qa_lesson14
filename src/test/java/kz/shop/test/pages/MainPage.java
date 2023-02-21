package kz.shop.test.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.shop.test.utils.Helpers;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    Helpers helpers = new Helpers();

    private final ElementsCollection
            horizontalMenu = $$(".bx-inclinkstop-container"),
            sidebar = $$(".bx-nav-1-lvl"),
            catalogItemTitle = $$(".bx_catalog_tile_title"),
            recommenderBlockTitle = $$(".recommender-block-title"),
            footerHeaderMenu = $$(".footer_header_btn"),
            listLine = $$(".list-line");

    private final SelenideElement
            pageTitle = $("#pagetitle"),
            authButton = $("#btn_show_auth"),
            personalSection = $("a[href=\"/personal/\"]"),
            siteHeaderInfo = $(".bx-inc-orginfo"),
            navbar = $(".fixed-navbar");


    @Step("Проверяем горизонтальное меню")
    public MainPage verifyHorizontalMenu(String items) {
        horizontalMenu.filter(visible).shouldHave(texts(items));
        return this;
    }

    @Step("Проверяем боковое меню и входящие категории")
    public MainPage verifySidebarMenu(String items, List<String> categories) {
        sidebar.find(text(items)).click();
        helpers.closeBannerPromotions();
        pageTitle.shouldHave(text(items));
        catalogItemTitle.filter(visible).shouldHave(texts(categories));
        return this;
    }

    @Step("Проверяем наличие разделов на главной странице")
    public MainPage verifySectionTitleText(String value) {
        recommenderBlockTitle.shouldHave(texts(value));
        return this;
    }

    @Step("Проверяем пукты меню футера и входящие в них подпункты")
    public MainPage verifyFooter(String header) {
        footerHeaderMenu.find(text(header)).shouldHave(appear);
        return this;
    }

    @Step("Переходим к форме авторизации")
    public MainPage goToAuthModalForm(String value) {
        authButton.shouldHave(text(value)).click();
        return this;
    }

    @Step("Выполняем проверки после авторизации")
    public MainPage checkAfterLogin(String ps, String psTitle) {
        personalSection.shouldHave(text(ps)).click();
        pageTitle.shouldHave(text(psTitle));
        return this;
    }

    @Step("Проверяем номер телефона в шапке")
    public MainPage checkContactInfoInHeader(String value) {
        siteHeaderInfo.shouldHave(text(value));
        return this;
    }

    @Step("Переходим к пункту меню {item}")
    public MainPage selectMenuItem(String item) {
        $(By.linkText(item)).click();
        return this;
    }

    @Step("Проверяем контактную информацию")
    public MainPage checkContactInfoInAdressAndPhone(String text, String department, String phone) {
        pageTitle.shouldHave(text(text));
        listLine.filterBy(text(department)).shouldHave(texts(phone));
        return this;
    }

    @Step("Проверяем номер телефона в навигационном меню")
    public MainPage checkContactInfoInNavbar(String value) {
        navbar.shouldHave(text(value));
        return this;
    }
}
