package kz.shop.test.tests;

import com.codeborne.selenide.Condition;
import kz.shop.test.pages.CartPage;
import kz.shop.test.pages.MainPage;
import kz.shop.test.pages.ProductCardPage;
import kz.shop.test.pages.SearchPage;
import kz.shop.test.testdata.TestData;
import kz.shop.test.utils.CloseBannerHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

@DisplayName("Тесты для магазина shop.kz")
public class ShopTest extends BaseTest {

    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();
    ProductCardPage productCardPage = new ProductCardPage();

    /**
     * ToDo добавить тесты на успешную и не успешную авторизацию (тестовые пользователи)
     * Перенести в page object
     * добавить тесты на поиск по артикулу
     * тест на проверку контактов
     * тесты на конфигуратор
     * удаление товаров из корзины
     * */

    @Test
    public void successfulAuthorizationTest(){
        step("Успешная авторизация на сайте", () -> {
            new CloseBannerHelper().closeBanner();
            $("#btn_show_auth").shouldHave(Condition.text("Вход")).click();
            //проверка открытия модального окна авторизации
            $("#simplemodal-container").shouldHave(Condition.text("Вход в интернет-магазин"));
            $("#ppUSER_LOGIN").setValue("123");
            $("#ppUSER_PASSWORD").setValue("123");
            $("#login_btn").click();
            $("a[href=\"/personal/\"]").shouldHave(Condition.text("Личный кабинет")).click();
            $("#pagetitle").shouldHave(Condition.text("Персональный раздел"));
        });
    }

    @Test
    public void unsuccessfulAuthorizationTest(){
        step("Авторизация на сайте, пользователь не зарегестрирован", () -> {
            new CloseBannerHelper().closeBanner();
            $("#btn_show_auth").shouldHave(Condition.text("Вход")).click();
            //проверка открытия модального окна авторизации
            $("#simplemodal-container").shouldHave(Condition.text("Вход в интернет-магазин"));
            $("#ppUSER_LOGIN").setValue("unregisteredUser");
            $("#ppUSER_PASSWORD").setValue("unregisteredPassword");
            $("#login_btn").click();
            $("#auth-status").shouldHave(Condition.text("Неверный логин или пароль.1"));
        });
    }

    @ParameterizedTest(name = "Проверка наличия разделов на главной странице: {0}")
    @ValueSource(strings = {"Наши покупатели выбирают", "Новинки"})
    public void checkMainPageSection(String value) {
        step("Проверяем наличие разделов на главной странице", () -> {
            mainPage.verifySectionTitleText(value);
        });
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка бокового меню {0}")
    public void verifySidebarTest(String items, List<String> categories) {
        step("Проверка бокового меню", () -> {
            mainPage.verifySidebarMenu(items, categories);
        });

    }

    @ParameterizedTest(name = "Проверка горизонтального меню {0}")
    @ValueSource(strings = {"Доставка", "Оплата", "Гарантия надёжности"})
    public void verifyHorizontalMenuTest(String items) {
        step("Проверка горизонтального меню", () -> {
            mainPage.verifyHorizontalMenu(items);
        });
    }

    @ParameterizedTest(name = "Проверка элементов: {0}")
    @CsvFileSource(resources = "/test.csv")
    public void verifyFooterMenuItems(String items) {
        step("Тестируем пункты меню в футере", () -> {
            mainPage.verifyFooter(items);
        });
    }

    @Test
    @DisplayName("Поиск на сайте shop.kz")
    public void searchTest() {
        step("Тестируем поиск на сайте", () -> {
            searchPage
                    .closeBanner()
                    .checkSearchInput()
                    .searchItemByItemName(TestData.ITEM_BY_NAME)
                    .checkResultAfterSearch(TestData.ITEM_BY_NAME);
            productCardPage
                    .checkPageTitleAvailableOnPage(TestData.ITEM_BY_NAME);
        });
    }

    @Test
    @DisplayName("Тест карточки товара")
    public void verifyProductCardPage() {
        step("Тестируем страницу карточки товара", () -> {
            searchPage
                    .closeBanner()
                    .searchItemByVendorCode(TestData.ITEM_BY_VENDOR_CODE);
            productCardPage
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
            searchPage
                    .closeBanner()
                    .searchItemByItemName(TestData.ITEM_BY_NAME)
                    .checkResultAfterSearch(TestData.ITEM_BY_NAME);
            productCardPage
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
                Arguments.of(
                        "Смартфоны и гаджеты",
                        List.of("Сотовые телефоны", "Гаджеты", "Программное обеспечение", "Аксессуары")),
                Arguments.of(
                        "Комплектующие",
                        List.of("Все для сборки компьютера", "Дополнительные комплектующие")),
                Arguments.of(
                        "Ноутбуки и компьютеры",
                        List.of("Ноутбуки", "Компьютеры", "Программное обеспечение", "Комплектующие для ноутбуков", "Аксессуары", "Мебель"))
        );
    }

}
