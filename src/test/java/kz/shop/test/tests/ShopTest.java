package kz.shop.test.tests;


import kz.shop.test.pages.MainPage;
import kz.shop.test.pages.ProductCardPage;
import kz.shop.test.pages.SearchPage;
import kz.shop.test.utils.CloseBannerHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.stream.Stream;
import static io.qameta.allure.Allure.step;

@DisplayName("Тесты для магазина shop.kz")
public class ShopTest extends BaseTest {

    MainPage mainPage = new MainPage();
    CloseBannerHelper closeBannerHelper = new CloseBannerHelper();

    /**
     * ToDo
     * добавить тесты на поиск по артикулу
     * тест на проверку контактов
     * тесты на конфигуратор
     * удаление товаров из корзины
     * */

    @ParameterizedTest(name = "Проверка наличия разделов на главной странице: {0}")
    @ValueSource(strings = {"Новинки", "Популярные"})
    public void checkMainPageSection(String value) {
        step("Проверяем наличие разделов на главной странице", () -> {
            closeBannerHelper.closeBanner();
            mainPage.verifySectionTitleText(value);
        });
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка бокового меню {0}")
    public void verifySidebarTest(String items, List<String> categories) {
        step("Проверка бокового меню", () -> {
            closeBannerHelper.closeBanner();
            mainPage.verifySidebarMenu(items, categories);
        });

    }

    @ParameterizedTest(name = "Проверка горизонтального меню {0}")
    @ValueSource(strings = {"Доставка", "Оплата", "Гарантия надёжности"})
    public void verifyHorizontalMenuTest(String items) {
        step("Проверка горизонтального меню", () -> {
            closeBannerHelper.closeBanner();
            mainPage.verifyHorizontalMenu(items);
        });
    }

    @ParameterizedTest(name = "Проверка элементов: {0}")
    @CsvFileSource(resources = "/test.csv")
    public void verifyFooterMenuItems(String items) {
        step("Тестируем пункты меню в футере", () -> {
            closeBannerHelper.closeBanner();
            mainPage.verifyFooter(items);
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
