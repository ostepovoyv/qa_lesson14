package kz.shop.test.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.shop.test.utils.CloseBannerHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class ProductCardPage {

    CloseBannerHelper closeBannerHelper = new CloseBannerHelper();

    private final SelenideElement
            pageTitle = $("#pagetitle"),
            vendorCode = $(".bx-card-mark"),
            currentPrice = $(".item_current_price"),
            productSpecifications = $("#bx-card-features"),
            buyButton = $("#product-card-buttons");


    @Step("Закрываем высплывающие окна")
    public ProductCardPage closeBanner() {
        closeBannerHelper.closeBanner();
        return this;
    }

    @Step("Закрываем высплывающие окна")
    public ProductCardPage closePromoBanner() {
        closeBannerHelper.closeBannerPromotions();
        return this;
    }

    @Step("Проверка наличия названия товара")
    public ProductCardPage checkPageTitleAvailableOnPage(String value) {
        this.pageTitle.shouldHave(visible).shouldHave(text(value));
        return this;
    }

    @Step("Проверка наличия Артикула товара")
    public ProductCardPage checkVendorCodeAvailableOnPage(String value) {
        this.vendorCode.shouldHave(text("Артикул " + value));
        return this;
    }

    @Step("Проверка наличия цены товара")
    public ProductCardPage checkCurrentPriceAvailableOnPage(String value) {
        this.currentPrice.shouldBe(visible).shouldHave(text(value));
        return this;
    }

    @Step("Проверка наличия кнопки 'Купить' товар")
    public ProductCardPage checkBuyButtonAvailableOnPage() {
        this.buyButton.shouldBe(visible).shouldHave(text("Купить"));
        return this;
    }

    @Step("Проверка наличия информации о технических характеристиках товара")
    public ProductCardPage checkProductSpecificationsAvailableOnPage() {
        this.productSpecifications.scrollTo().shouldBe(visible).shouldHave(text("Технические характеристики"));
        return this;
    }

    @Step("Добавляем выбранный товар в корзину")
    public ProductCardPage addProductToBasket(){
        this.buyButton.click();
        return this;
    }

}
