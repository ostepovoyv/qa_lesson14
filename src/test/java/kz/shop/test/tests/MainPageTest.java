package kz.shop.test.tests;

import kz.shop.test.pages.MainPage;
import kz.shop.test.utils.Helpers;
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
public class MainPageTest extends BaseTest {

    MainPage mainPage = new MainPage();
    Helpers helpers = new Helpers();

    /**
     * ToDo
     * тест на сравнение
     * тест сортировки
     * Добавление в корзину с главной страницы
     */

    @ParameterizedTest(name = "Проверка наличия разделов на главной странице: {0}")
    @ValueSource(strings = {"Новинки", "Популярные"})
    public void checkMainPageSection(String value) {
        step("Проверяем наличие разделов на главной странице", () -> {
            helpers.closeBanner();
            mainPage.verifySectionTitleText(value);
        });
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка бокового меню {0}")
    public void verifySidebarTest(String items, List<String> categories) {
        step("Проверка бокового меню", () -> {
            helpers.closeBanner();
            mainPage.verifySidebarMenu(items, categories);
        });
    }

    @ParameterizedTest(name = "Проверка горизонтального меню {0}")
    @ValueSource(strings = {"Доставка", "Оплата", "Гарантия надёжности"})
    public void verifyHorizontalMenuTest(String items) {
        step("Проверка горизонтального меню", () -> {
            helpers.closeBanner();
            mainPage.verifyHorizontalMenu(items);
        });
    }

    @ParameterizedTest(name = "Проверка элементов: {0}")
    @CsvFileSource(resources = "/test.csv")
    public void verifyFooterMenuItems(String items) {
        step("Тестируем пункты меню в футере", () -> {
            helpers.closeBanner();
            mainPage.verifyFooter(items);
        });
    }

    @Test
    @DisplayName("Проверка наличия контактной информации")
    public void checkContactInformation() {
        helpers.closeBanner();
        step("Проверка контактной информации в шапке на главной странице", () -> {
            mainPage.checkContactInfoInHeader(" +7 771 920-19-20");
        });
        step("Проверка контактной информации в футере", () -> {
            mainPage
                    .selectMenuItem("Адреса и телефоны")
                    .checkContactInfoInAdressAndPhone(
                            "Наши адреса в г. Алматы",
                            "Интернет-магазин",
                            "+7 (771) 920-19-20"
                    );
        });
        step("Проверка контактной информации в навигационном меню", () -> {
            mainPage.checkContactInfoInNavbar(" +7 771 920-19-20");
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
