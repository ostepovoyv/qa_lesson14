package kz.shop.test.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static kz.shop.test.testdata.Endpoints.PEN_CATALOG;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PenCatalogPage {

    private final SelenideElement filterName = $("[data-value=name");

    private final ElementsCollection itemName = $$(".bx_catalog_item_title_text");

    @Step("Переходим в каталог")
    public PenCatalogPage openPenCatalog() {
        open(PEN_CATALOG);
        return this;
    }

    @Step("Проверка сортировки каталога товаров ")
    public PenCatalogPage checkSortByName() {
        List<String> actualItemsNames = itemName.texts();
        Collections.sort(actualItemsNames);
        filterName.click();
        List<String> sortedItemsNames = itemName.texts();
        assertThat(actualItemsNames, is(sortedItemsNames));
        return this;
    }

}

