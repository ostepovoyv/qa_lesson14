package kz.shop.test.tests;

import io.qameta.allure.*;
import kz.shop.test.pages.AuthFormPage;
import kz.shop.test.pages.MainPage;
import kz.shop.test.testdata.PersonalAreaData;
import kz.shop.test.utils.Helpers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("shop.kz")
@Feature("Login")
@Owner("ostepovoyv")
@DisplayName("Тесты авторизации в интернет магазине shop.kz")
public class LoginTests extends BaseTest {

    MainPage mainPage = new MainPage();
    Helpers helpers = new Helpers();
    AuthFormPage authFormPage = new AuthFormPage();

    @Test
    @DisplayName("Успешная авторизация на сайте")
    public void successfulAuthorizationTest() {
        helpers.closeBanner();
        mainPage.goToAuthModalForm(PersonalAreaData.ENTER_BUTTON_TEXT);
        authFormPage
                .checkModalFormTitle(PersonalAreaData.MODAL_FORM_TITLE_TEXT)
                .setAuthInfo("123", "123")
                .auth();
        mainPage.checkAfterLogin(PersonalAreaData.PA_TEXT, PersonalAreaData.PERSONAL_SECTION_TITLE);
    }

    @Test
    @DisplayName("Авторизация на сайте, пользователь не зарегестрирован")
    public void unsuccessfulAuthorizationTest() {
        helpers.closeBanner();
        mainPage.goToAuthModalForm(PersonalAreaData.ENTER_BUTTON_TEXT);
        authFormPage
                .checkModalFormTitle(PersonalAreaData.MODAL_FORM_TITLE_TEXT)
                .setAuthInfo("unregisteredUser", "unregisteredPassword")
                .auth()
                .checkAuthStatus(PersonalAreaData.ERROR_TEXT);
    }

}
