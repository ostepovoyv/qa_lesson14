package kz.shop.test.config;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import kz.shop.test.utils.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    @BeforeAll
    public static void setUpMain(){
        ProjectConfig.setUp();
    }

    @BeforeEach
    public void beforeEachTest(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open("/");
    }

    @AfterEach
    public void afterEachTest(){
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}
