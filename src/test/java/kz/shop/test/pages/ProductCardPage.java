package kz.shop.test.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;


public class ProductCardPage {

    private final SelenideElement
            pageTitle = $("#pagetitle"),
            vendorCode = $(".bx-card-mark"),
            currentPrice = $(".item_current_price"),
            productSpecifications = $("#bx-card-features"),
            buyButton = $("#product-card-buttons");

    @Step("Проверка наличия названия товара")
    public ProductCardPage checkPageTitleAvailableOnPage(String value) {
        pageTitle.shouldHave(visible).shouldHave(text(value));
        return this;
    }

    @Step("Проверка наличия Артикула товара")
    public ProductCardPage checkVendorCodeAvailableOnPage(String value) {
        vendorCode.shouldHave(text("Артикул " + value));
        return this;
    }

    @Step("Проверка наличия цены товара")
    public ProductCardPage checkCurrentPriceAvailableOnPage(String value) {
        currentPrice.shouldBe(visible).shouldHave(text(value));
        return this;
    }

    @Step("Проверка наличия кнопки 'Купить' товар")
    public ProductCardPage checkBuyButtonAvailableOnPage(String value) {
        buyButton.shouldBe(visible).shouldHave(text(value));
        return this;
    }

    @Step("Проверка наличия информации о технических характеристиках товара")
    public ProductCardPage checkProductSpecificationsAvailableOnPage(String value) {
        productSpecifications.scrollTo().shouldBe(visible).shouldHave(text(value));
        return this;
    }

    @Step("Добавляем выбранный товар в корзину")
    public ProductCardPage addProductToCart(String value){
        buyButton.click();
        buyButton.shouldHave(text(value));
        return this;
    }

}
