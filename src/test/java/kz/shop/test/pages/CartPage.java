package kz.shop.test.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {

    private final SelenideElement
            cartButton = $(".hub-i-cart-link"),
            cartTitle = $("#pagetitle"),
            productNameInCart = $(".cart-bill-name"),
            cartSubmit = $("#CartSubmit");

    @Step("Переход в корзину")
    public CartPage goToCart() {
        this.cartButton.click();
        return this;
    }

    @Step("Проверяем, переход в корзину")
    public CartPage checkCartPage() {
        this.cartTitle.shouldHave(text("Моя корзина"));
        return this;
    }

    @Step("Проверяем, добавленный товар в корзине")
    public CartPage checkProductInCart(String value) {
        this.productNameInCart.shouldHave(text(value));
        return this;
    }

    @Step("Провереям доступность кнопки оформить")
    public CartPage checkOrderButton() {
        this.cartSubmit.shouldHave(type("button")).shouldHave(value("Оформить заказ"));
        return this;
    }
}
