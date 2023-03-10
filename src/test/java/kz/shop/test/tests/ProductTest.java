package kz.shop.test.tests;

import io.qameta.allure.*;
import kz.shop.test.pages.CartPage;
import kz.shop.test.pages.PenCatalogPage;
import kz.shop.test.pages.ProductCardPage;
import kz.shop.test.pages.SearchPage;
import kz.shop.test.testdata.TestData;
import kz.shop.test.utils.Helpers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static kz.shop.test.testdata.Endpoints.BASKET;

@Epic("shop.kz")
@Feature("Product")
@Owner("ostepovoyv")
@DisplayName("Тесты с товарами в интернет магазине shop.kz")
public class ProductTest extends BaseTest {

    SearchPage searchPage = new SearchPage();
    ProductCardPage productCardPage = new ProductCardPage();
    CartPage cartPage = new CartPage();
    PenCatalogPage penCatalogPage = new PenCatalogPage();
    Helpers helpers = new Helpers();

    @Test
    @DisplayName("Проверка карточки товара")
    public void verifyProductCardPage() {
        step("Тестируем страницу карточки товара", () -> {
            helpers.closeBanner();
            searchPage
                    .searchItemByVendorCode(TestData.ITEM_BY_VENDOR_CODE);
            productCardPage
                    .checkPageTitleAvailableOnPage(TestData.ITEM_BY_VENDOR_CODE_NAME)
                    .checkVendorCodeAvailableOnPage(TestData.ITEM_BY_VENDOR_CODE)
                    .checkCurrentPriceAvailableOnPage(TestData.ITEM_BY_VENDOR_CODE_PRICE)
                    .checkBuyButtonAvailableOnPage(TestData.BUY_BUTTON_TEXT)
                    .checkProductSpecificationsAvailableOnPage(TestData.SPECIFICATIONS_TEXT);
        });
    }

    @Test
    @DisplayName("Проверка добавления товара в корзину")
    public void addProductToCart() {
        step("Тестируем добавление товара в корзину", () -> {
            helpers.closeBanner();
            searchPage
                    .searchItem(TestData.ITEM_BY_NAME)
                    .checkResultAfterSearch(TestData.ITEM_BY_NAME);
            helpers.closeBanner();
            productCardPage
                    .checkPageTitleAvailableOnPage(TestData.ITEM_BY_NAME)
                    .addProductToCart(TestData.ADD_TO_CART_BUTTON_AFTER_TEXT);
            cartPage
                    .goToCart()
                    .checkCartPage(TestData.CART_TITLE)
                    .checkProductInCart(TestData.ITEM_BY_NAME)
                    .checkOrderButton(TestData.ORDER_BUTTON_TEXT);
        });
    }

    @Test
    @DisplayName("Проверка удаления товара из корзины")
    public void deleteProductFromCart() {
        step("Тестируем удаление товара из корзины", () -> {
            helpers.closeBanner();
            searchPage
                    .searchItem(TestData.ITEM_BY_NAME)
                    .checkResultAfterSearch(TestData.ITEM_BY_NAME);
            helpers.closeBanner();
            productCardPage
                    .checkPageTitleAvailableOnPage(TestData.ITEM_BY_NAME)
                    .addProductToCart(TestData.ADD_TO_CART_BUTTON_AFTER_TEXT);
            open(BASKET);
            cartPage
                    .checkProductInCart(TestData.ITEM_BY_NAME)
                    .deleteProduct()
                    .checkAfterDelete();
        });
    }

    @Test
    @DisplayName("Проверка сортировки по имени")
    public void sortingTest() {
        penCatalogPage
                .openPenCatalog()
                .checkSortByName();
    }

}
