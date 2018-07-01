package ru.vyukov.myappposition.accounts.controller;

import org.junit.Test;
import ru.vyukov.myappposition.utils.SelenideTest;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

/**
 * @author gelo
 */
public class LoginPageControllerTest extends SelenideTest {

    @Test
    public void loginPage() {
        open("/");
        $("#toAdminPanel").shouldBe(visible);
    }
}