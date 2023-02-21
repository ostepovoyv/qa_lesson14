package kz.shop.test.tests;

import io.qameta.allure.*;
import kz.shop.test.pages.ProductCardPage;
import kz.shop.test.pages.SearchPage;
import kz.shop.test.testdata.TestData;
import kz.shop.test.utils.Helpers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Epic("shop.kz")
@Feature("Search")
@Owner("ostepovoyv")
@DisplayName("Тесты на поиск товара в интернет магазине shop.kz")
public class SearchTests extends BaseTest {

    SearchPage searchPage = new SearchPage();
    ProductCardPage productCardPage = new ProductCardPage();
    Helpers helpers = new Helpers();

    @Test
    @DisplayName("Поиск по названию товара")
    public void searchByNameTest() {
        step("Тестируем поиск на сайте по названию", () -> {
            helpers.closeBanner();
            searchPage
                    .checkSearchInput(TestData.SEARCH_INPUT_TEXT)
                    .searchItem(TestData.ITEM_BY_NAME)
                    .checkResultAfterSearch(TestData.ITEM_BY_NAME);
            productCardPage
                    .checkPageTitleAvailableOnPage(TestData.ITEM_BY_NAME);
        });
    }

    @Test
    @DisplayName("Поиск по артикулу")
    public void searchByVendorCodeTest() {
        step("Тестируем поиск на сайте по артикулу", () -> {
            helpers.closeBanner();
            searchPage
                    .checkSearchInput(TestData.SEARCH_INPUT_TEXT)
                    .searchItem(TestData.ITEM_BY_VENDOR_CODE)
                    .checkResultAfterSearch(TestData.ITEM_BY_VENDOR_CODE_NAME);
            productCardPage
                    .checkPageTitleAvailableOnPage(TestData.ITEM_BY_VENDOR_CODE_NAME);
        });
    }

}
