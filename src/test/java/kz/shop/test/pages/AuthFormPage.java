package kz.shop.test.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class AuthFormPage {

    private final SelenideElement
            modalFormTitle = $("#simplemodal-container"),
            loginInput = $("#ppUSER_LOGIN"),
            passwordInput = $("#ppUSER_PASSWORD"),
            loginButton = $("#login_btn"),
            authStatus = $("#auth-status");

    @Step("Проверка заголовка формы авторизации")
    public AuthFormPage checkModalFormTitle(String value) {
        modalFormTitle.shouldHave(Condition.text(value));
        return this;
    }

    @Step("Указываем данные для авторизации")
    public AuthFormPage setAuthInfo(String user, String password) {
        loginInput.setValue(user);
        passwordInput.setValue(password);
        return this;
    }

    @Step("Нажимаем кнопку войти")
    public AuthFormPage auth() {
        loginButton.click();
        return this;
    }

    @Step("Проверка статуса авторизации")
    public AuthFormPage checkAuthStatus(String value){
        authStatus.shouldHave(Condition.text(value));
        return this;
    }

}
