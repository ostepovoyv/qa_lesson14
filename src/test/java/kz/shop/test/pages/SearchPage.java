package kz.shop.test.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import kz.shop.test.utils.CloseBannerHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SearchPage {

    CloseBannerHelper closeBannerHelper = new CloseBannerHelper();

    private final SelenideElement
            searchField = $(".search-hover__field"),
            searchFieldMulti = $(".multi-input"),
            searchButton = $(".search-hover__submit"),
            modalViewSearchResult = $(".autocomplete-products__item-title");

    @Step("Закрываем всплывающие окна")
    public SearchPage closeBanner() {
        closeBannerHelper.closeBanner();
        return this;
    }

    @Step("Проверяем наличие поля поиска на главной странице")
    public SearchPage checkSearchInput() {
        searchField.shouldHave(Condition.attribute("placeholder", "Поиск по сайту"));
        return this;
    }

    @Step("Поиск товара по имени")
    public SearchPage searchItemByItemName(String value) {
        searchButton.click();
        searchFieldMulti.setValue(value);
        return this;
    }

    @Step("Проверка результатов поиска в выпадающем списке и переход на карточку товара")
    public SearchPage checkResultAfterSearch(String value) {
        modalViewSearchResult.shouldHave(text(value)).click();
        return this;
    }

    @Step("Поиск товара по артикулу")
    public SearchPage searchItemByVendorCode(String vendorCode) {
        searchField.click();
        searchFieldMulti.setValue(vendorCode).pressEnter();
        return this;
    }

}
