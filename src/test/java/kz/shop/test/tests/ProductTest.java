package kz.shop.test.tests;

import kz.shop.test.pages.CartPage;
import kz.shop.test.pages.ProductCardPage;
import kz.shop.test.pages.SearchPage;
import kz.shop.test.testdata.TestData;
import kz.shop.test.utils.Helpers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static kz.shop.test.testdata.Endpoints.BASKET;

@DisplayName("Тесты с товарами в интернет магазине shop.kz")
public class ProductTest extends BaseTest {

    SearchPage searchPage = new SearchPage();
    ProductCardPage productCardPage = new ProductCardPage();
    CartPage cartPage = new CartPage();
    Helpers helpers = new Helpers();

    @Test
    @DisplayName("Тест карточки товара")
    public void verifyProductCardPage() {
        step("Тестируем страницу карточки товара", () -> {
            helpers.closeBanner();
            searchPage
                    .searchItemByVendorCode(TestData.ITEM_BY_VENDOR_CODE);
            productCardPage
                    .checkPageTitleAvailableOnPage(TestData.ITEM_BY_VENDOR_CODE_NAME)
                    .checkVendorCodeAvailableOnPage(TestData.ITEM_BY_VENDOR_CODE)
                    .checkCurrentPriceAvailableOnPage(TestData.ITEM_BY_VENDOR_CODE_PRICE)
                    .checkBuyButtonAvailableOnPage()
                    .checkProductSpecificationsAvailableOnPage();
        });
    }

    @Test
    @DisplayName("Тест добавления товара в корзину")
    public void addProductToCart() {
        step("Тестируем добавление товара в корзину", () -> {
            helpers.closeBanner();
            searchPage
                    .searchItemByItemName(TestData.ITEM_BY_NAME)
                    .checkResultAfterSearch(TestData.ITEM_BY_NAME);
            helpers.closeBanner();
            productCardPage
                    .checkPageTitleAvailableOnPage(TestData.ITEM_BY_NAME)
                    .addProductToBasket();
            cartPage
                    .goToCart()
                    .checkCartPage()
                    .checkProductInCart(TestData.ITEM_BY_NAME)
                    .checkOrderButton();
        });
    }

    @Test
    @DisplayName("Тест удаления товара из корзины")
    public void deleteProductFromCart() {
        step("Тестируем удаление товара из корзины", () -> {
            helpers.closeBanner();
            searchPage
                    .searchItemByItemName(TestData.ITEM_BY_NAME)
                    .checkResultAfterSearch(TestData.ITEM_BY_NAME);
            helpers.closeBanner();
            productCardPage
                    .checkPageTitleAvailableOnPage(TestData.ITEM_BY_NAME)
                    .addProductToBasket();
            open(BASKET);
            cartPage
                    .checkProductInCart(TestData.ITEM_BY_NAME)
                    .deleteProduct()
                    .checkAfterDelete();
        });
    }

}
