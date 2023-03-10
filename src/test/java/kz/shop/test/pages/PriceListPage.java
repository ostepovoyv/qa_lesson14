package kz.shop.test.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.xlstest.XLS;
import io.qameta.allure.Step;
import kz.shop.test.testdata.PriceList;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static kz.shop.test.testdata.Endpoints.PRICE_LIST_PAGE;
import static org.apache.commons.io.FileUtils.getFile;
import static org.assertj.core.api.Assertions.assertThat;

public class PriceListPage {

    private String
            retailPriceLocator = "#li_76476",
            retailPriceFileName = "WW_retail_all.xls",
            servicesPriceLocator = "#li_83700",
            servicesPriceFileName = "WW_services.xls";

    private final SelenideElement
            pagetitle = $("#pagetitle"),
            retailPriceList = $(retailPriceLocator),
            servicesPriceList = $(servicesPriceLocator);




    @Step("Переходим на страницу прайс листов")
    public PriceListPage openPriceListPage(String value) {
        open(PRICE_LIST_PAGE);
        pagetitle.shouldHave(Condition.text(value));
        return this;
    }

    @Step("Выбираем прайс лист {value}")
    public PriceListPage selectRetailPriceList(String value) {
        retailPriceList.shouldHave(Condition.text(value));
        return this;
    }

    @Step("Выбираем прайс лист {value}")
    public PriceListPage selectServicesPriceList(String value) {
        servicesPriceList.shouldHave(Condition.text(value));
        return this;
    }

    @Step("Проверяем файл {fileName} и его содержимое")
    public PriceListPage checkPriceList(PriceList priceList) throws FileNotFoundException {
        if (priceList.getFileName().equals(retailPriceFileName)) {
            checkFile(retailPriceLocator,
                    priceList.getFileName(),
                    priceList.getSheet(),
                    priceList.getRow(),
                    priceList.getCell(),
                    priceList.getValue());
        }
        if (priceList.getFileName().equals(servicesPriceFileName)) {
            checkFile(servicesPriceLocator,
                    priceList.getFileName(),
                    priceList.getSheet(),
                    priceList.getRow(),
                    priceList.getCell(),
                    priceList.getValue());
        }
        return this;
    }

    private void checkFile(String locatorValue,
                           String fileName,
                           Integer sheet,
                           Integer row,
                           Integer cell,
                           String value
    ) throws FileNotFoundException {
        File downloadedFile = $(locatorValue).download();
        assertThat(downloadedFile.getName()).matches(fileName);
        XLS xlsx = new XLS(getFile(downloadedFile));
        assertThat(xlsx.excel.getSheetAt(sheet).getRow(row).getCell(cell).getStringCellValue()).isEqualTo(value);
    }

}
