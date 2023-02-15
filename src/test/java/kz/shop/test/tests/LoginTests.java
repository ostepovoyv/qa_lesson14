package kz.shop.test.tests;

import kz.shop.test.pages.AuthFormPage;
import kz.shop.test.pages.MainPage;
import kz.shop.test.utils.CloseBannerHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты авторизации в интернет магазине shop.kz")
public class LoginTests extends BaseTest {

    MainPage mainPage = new MainPage();
    CloseBannerHelper closeBannerHelper = new CloseBannerHelper();
    AuthFormPage authFormPage = new AuthFormPage();

    @Test
    @DisplayName("Успешная авторизация на сайте")
    public void successfulAuthorizationTest() {
        closeBannerHelper.closeBanner();
        mainPage.goToAuthModalForm("Вход");
        authFormPage
                .checkModalFormTitle("Вход в интернет-магазин")
                .setAuthInfo("123", "123")
                .auth();
        mainPage.checkAfterLogin("Личный кабинет", "Персональный раздел");
    }

    @Test
    @DisplayName("Авторизация на сайте, пользователь не зарегестрирован")
    public void unsuccessfulAuthorizationTest() {
        closeBannerHelper.closeBanner();
        mainPage.goToAuthModalForm("Вход");
        authFormPage
                .checkModalFormTitle("Вход в интернет-магазин")
                .setAuthInfo("unregisteredUser", "unregisteredPassword")
                .auth()
                .checkAuthStatus("Неверный логин или пароль.");
    }

}
