package kz.shop.test.tests;

import io.qameta.allure.*;
import kz.shop.test.pages.PriceListPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;

import static kz.shop.test.testdata.PriceLists.*;

@Epic("shop.kz")
@Feature("Price")
@Owner("ostepovoyv")
@DisplayName("Тесты доступности и содержимого прайс-листов")
public class PriceListTests extends BaseTest {

    PriceListPage priceListPage = new PriceListPage();

    @Test
    @DisplayName("Проверка доступности и содержимого розничного прайс-листа")
    public void checkRetailPriceList() throws FileNotFoundException {
        priceListPage
                .openPriceListPage("Прайс-листы")
                .selectRetailPriceList("Розничный общий")
                .checkPriceList(prRetailCompanyName)
                .checkPriceList(prRetailSite)
                .checkPriceList(prRetailName)
                .checkPriceList(prRetailCode)
                .checkPriceList(prRetailNomination)
                .checkPriceList(prRetailPrice)
                .checkPriceList(prRetailGuarantee)
                .checkPriceList(prRetailNote);
    }

    @Test
    @DisplayName("Проверка доступности и содержимого прайс-листа сервисных услуг")
    public void checkServicesPriceList() throws FileNotFoundException {
        priceListPage
                .openPriceListPage("Прайс-листы")
                .selectServicesPriceList("Сервисные услуги")
                .checkPriceList(prServicesCompanyName)
                .checkPriceList(prServicesName)
                .checkPriceList(prServicesCode)
                .checkPriceList(prServicesNomination)
                .checkPriceList(prServicesPrice);
    }

}
