package kz.shop.test.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.xlstest.XLS;
import io.qameta.allure.Step;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static kz.shop.test.testdata.Endpoints.PRICE_LIST_PAGE;
import static org.apache.commons.io.FileUtils.getFile;
import static org.assertj.core.api.Assertions.assertThat;

public class PriceListPage {

    private final SelenideElement
            pagetitle = $("#pagetitle"),
            retailPriceList = $("#li_76476"),
            servicesPriceList = $("#li_83700");

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
    public PriceListPage checkPriceList(String fileName,
                                        Integer sheet,
                                        Integer row,
                                        Integer cell,
                                        String value
    ) throws FileNotFoundException {
        if (fileName.equals("WW_retail_all.xls")) {
            checkFile("#li_76476", fileName, sheet, row, cell, value);
        }
        if (fileName.equals("WW_services.xls")) {
            checkFile("#li_83700", fileName, sheet, row, cell, value);
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
