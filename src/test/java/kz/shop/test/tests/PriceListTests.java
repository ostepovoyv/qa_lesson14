package kz.shop.test.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.xlstest.XLS;
import kz.shop.test.pages.PriceListPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static kz.shop.test.testdata.Endpoints.PRICE_LIST_PAGE;
import static org.apache.commons.io.FileUtils.getFile;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Проверка доступности и содержимого прайс-листов")
public class PriceListTests extends BaseTest {

    PriceListPage priceListPage = new PriceListPage();

    @Test
    @DisplayName("Проверка доступности и содержимого прайс-листов")
    public void checkPriceList() throws FileNotFoundException {
        open(PRICE_LIST_PAGE);
        $("#pagetitle").shouldHave(Condition.text("Прайс-листы"));

        $("#li_76476").shouldHave(Condition.text("Розничный общий"));

        File downloadedFile = $("#li_76476").download();
        assertThat(downloadedFile.getName()).matches("WW_retail_all.xls");
        XLS xlsx = new XLS(getFile(downloadedFile));
        assertThat(xlsx.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue()).isEqualTo("ТОО \"Белый Ветер KZ\"");
        assertThat(xlsx.excel.getSheetAt(0).getRow(0).getCell(6).getStringCellValue()).isEqualTo("http://www.ww.kz, http://shop.kz");
        assertThat(xlsx.excel.getSheetAt(0).getRow(2).getCell(2).getStringCellValue()).isEqualTo("Общий прайс-лист электронного магазина");

        assertThat(xlsx.excel.getSheetAt(0).getRow(5).getCell(1).getStringCellValue()).isEqualTo("Код");
        assertThat(xlsx.excel.getSheetAt(0).getRow(5).getCell(2).getStringCellValue()).isEqualTo("Наименование");
        assertThat(xlsx.excel.getSheetAt(0).getRow(5).getCell(3).getStringCellValue()).isEqualTo("Цена расчетная");
        assertThat(xlsx.excel.getSheetAt(0).getRow(5).getCell(4).getStringCellValue()).isEqualTo("Срок гар., мес");
        assertThat(xlsx.excel.getSheetAt(0).getRow(5).getCell(5).getStringCellValue()).isEqualTo("Примечание");


        $("#li_83700").shouldHave(Condition.text("Сервисные услуги"));
        File downloadedFile1 = $("#li_83700").download();
        assertThat(downloadedFile1.getName()).matches("WW_services.xls");
        XLS xlsx1 = new XLS(getFile(downloadedFile1));
        assertThat(xlsx1.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue()).isEqualTo("ТОО \"Белый Ветер KZ\"");
        assertThat(xlsx1.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue()).isEqualTo("Прайс лист на услуги Сервисного Центра");

        assertThat(xlsx1.excel.getSheetAt(0).getRow(3).getCell(0).getStringCellValue()).isEqualTo("Код");
        assertThat(xlsx1.excel.getSheetAt(0).getRow(3).getCell(1).getStringCellValue()).isEqualTo("Наименование");
        assertThat(xlsx1.excel.getSheetAt(0).getRow(3).getCell(2).getStringCellValue()).isEqualTo("Цена, тг.");
    }

    @Test
    @DisplayName("Проверка доступности и содержимого розничного прайс-листа")
    public void checkRetailPriceList() throws FileNotFoundException {
        priceListPage
                .openPriceListPage("Прайс-листы")
                .selectRetailPriceList("Розничный общий")
                .checkPriceList("WW_retail_all.xls",0,0,0,"ТОО \"Белый Ветер KZ\"")
                .checkPriceList("WW_retail_all.xls",0,0,6,"http://www.ww.kz, http://shop.kz")
                .checkPriceList("WW_retail_all.xls",0,2,2,"Общий прайс-лист электронного магазина")
                .checkPriceList("WW_retail_all.xls",0,5,1,"Код")
                .checkPriceList("WW_retail_all.xls",0,5,2,"Наименование")
                .checkPriceList("WW_retail_all.xls",0,5,3,"Цена расчетная")
                .checkPriceList("WW_retail_all.xls",0,5,4,"Срок гар., мес")
                .checkPriceList("WW_retail_all.xls",0,5,5,"Примечание");
    }

    @Test
    @DisplayName("Проверка доступности и содержимого прайс-листа сервисных услуг")
    public void checkServicesPriceList() throws FileNotFoundException {
        priceListPage
                .openPriceListPage("Прайс-листы")
                .selectServicesPriceList("Сервисные услуги")
                .checkPriceList("WW_services.xls",0,0,1,"ТОО \"Белый Ветер KZ\"")
                .checkPriceList("WW_services.xls",0,1,1,"Прайс лист на услуги Сервисного Центра")
                .checkPriceList("WW_services.xls",0,3,0,"Код")
                .checkPriceList("WW_services.xls",0,3,1,"Наименование")
                .checkPriceList("WW_services.xls",0,3,2,"Цена, тг.");
    }

}
