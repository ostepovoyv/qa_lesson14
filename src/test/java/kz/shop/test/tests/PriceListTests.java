package kz.shop.test.tests;

import kz.shop.test.pages.PriceListPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;


@DisplayName("Проверка доступности и содержимого прайс-листов")
public class PriceListTests extends BaseTest {

    PriceListPage priceListPage = new PriceListPage();

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
