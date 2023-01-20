package kz.shop.test;

import kz.shop.test.config.TestBase;
import kz.shop.test.pages.CartPage;
import kz.shop.test.pages.MainPage;
import kz.shop.test.pages.ProductCardPage;
import kz.shop.test.pages.SearchPage;
import kz.shop.test.testdata.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;

@DisplayName("Тесты для магазина shop.kz")
public class ShopTest extends TestBase {

    @ParameterizedTest(name = "Проверка наличия разделов на главной странице: {0}")
    @ValueSource(strings = {"Наши покупатели выбирают", "Новинки"})
    public void checkMainPageSection(String value) {
        step("Проверяем наличие разделов на главной странице", () -> {
            new MainPage().verifySectionTitleText(value);
        });
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка бокового меню {0}")
    public void verifySidebarTest(String items, List<String> categories) {
        new MainPage().verifySidebarMenu(items, categories);
    }

    @ParameterizedTest(name = "Проверка горизонтального меню {0}")
    @ValueSource(strings = {"Доставка", "Оплата", "Гарантия надёжности"})
    public void verifyHorizontalMenuTest(String items) {
        new MainPage().verifyHorizontalMenu(items);
    }

    @ParameterizedTest(name = "Проверка элементов: {0}")
    @CsvFileSource(resources = "/test.csv")
    public void verifyFooterMenuItems(String items) {
        step("Тестируем пункты меню в футере", () -> {
            new MainPage().verifyFooter(items);
        });
    }

    @Test
    @DisplayName("Поиск на сайте shop.kz")
    public void searchTest() {
        step("Тестируем поиск на сайте", () -> {
            new SearchPage()
                    .closeBanner()
                    .checkSearchInput()
                    .searchItemByItemName(TestData.ITEM_BY_NAME)
                    .checkResultAfterSearch(TestData.ITEM_BY_NAME);
            new ProductCardPage()
                    .checkPageTitleAvailableOnPage(TestData.ITEM_BY_NAME);
        });
    }

    @Test
    @DisplayName("Тест карточки товара")
    public void verifyProductCardPage() {
        step("Тестируем страницу карточки товара", () -> {
            new SearchPage()
                    .closeBanner()
                    .searchItemByVendorCode(TestData.ITEM_BY_VENDOR_CODE);
            new ProductCardPage()
                    .closePromoBanner()
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
            new SearchPage()
                    .closeBanner()
                    .searchItemByItemName(TestData.ITEM_BY_NAME)
                    .checkResultAfterSearch(TestData.ITEM_BY_NAME);
            new ProductCardPage()
                    .closePromoBanner()
                    .checkPageTitleAvailableOnPage(TestData.ITEM_BY_NAME)
                    .addProductToBasket();
            new CartPage()
                    .goToCart()
                    .checkCartPage()
                    .checkProductInCart(TestData.ITEM_BY_NAME)
                    .checkOrderButton();
        });
    }

    static Stream<Arguments> verifySidebarTest() {
        return Stream.of(
                Arguments.of("Смартфоны и гаджеты", List.of("Сотовые телефоны", "Гаджеты", "Программное обеспечение", "Аксессуары")),
                Arguments.of("Комплектующие", List.of("Все для сборки компьютера", "Дополнительные комплектующие")),
                Arguments.of("Ноутбуки и компьютеры", List.of("Ноутбуки", "Компьютеры", "Программное обеспечение", "Комплектующие для ноутбуков", "Аксессуары", "Мебель"))
        );
    }

}
