package kz.shop.test.tests;

import kz.shop.test.pages.CartPage;
import kz.shop.test.pages.MainPage;
import kz.shop.test.pages.ProductCardPage;
import kz.shop.test.pages.SearchPage;
import kz.shop.test.testdata.TestData;
import kz.shop.test.utils.CloseBannerHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@DisplayName("Тесты с продуктами в интернет магазине shop.kz")
public class ProductTest extends BaseTest {

    SearchPage searchPage = new SearchPage();
    ProductCardPage productCardPage = new ProductCardPage();
    CloseBannerHelper closeBannerHelper = new CloseBannerHelper();

    @Test
    @DisplayName("Тест карточки товара")
    public void verifyProductCardPage() {
        step("Тестируем страницу карточки товара", () -> {
            searchPage
                    .searchItemByItemName(TestData.ITEM_BY_VENDOR_CODE);
            closeBannerHelper.closeBanner();
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
            closeBannerHelper.closeBanner();
            searchPage
                    .searchItemByItemName(TestData.ITEM_BY_NAME)
                    .checkResultAfterSearch(TestData.ITEM_BY_NAME);
            closeBannerHelper.closeBanner();
            productCardPage
                    .checkPageTitleAvailableOnPage(TestData.ITEM_BY_NAME)
                    .addProductToBasket();
            new CartPage()
                    .goToCart()
                    .checkCartPage()
                    .checkProductInCart(TestData.ITEM_BY_NAME)
                    .checkOrderButton();
        });
    }

}
