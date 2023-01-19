package kz.shop.test.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class TestBaseOld {

    @BeforeAll
    public static void setUpMain(){
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadTimeout = 15000;
        Configuration.timeout = 15000;
        Configuration.baseUrl= "https://shop.kz";
//        Configuration.browser= "firefox";
//        Configuration.holdBrowserOpen = true;
    }

    @BeforeEach
    void openUrl() {
        open("/");
    }

    @AfterEach
    public void afterAllTest(){
        Selenide.closeWebDriver();
    }
}
