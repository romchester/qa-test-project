import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    private static final String LOGIN_PAGE_URL = "https://the-internet.herokuapp.com/login";

    @Test
    public void successfulLoginTest() {
        LoginPage loginPage = open(LOGIN_PAGE_URL, LoginPage.class);
        SecureAreaPage secureAreaPage = loginPage.loginSuccess("tomsmith", "SuperSecretPassword!");
        secureAreaPage.verifySuccessMessage("You logged into a secure area!");
    }

    @Test
    void invalidPasswordTest() {
        LoginPage loginPage = open(LOGIN_PAGE_URL, LoginPage.class);
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("wrongPassword");
        loginPage.clickLogin();

        loginPage.getFlashMessage().shouldHave(text("Your password is invalid!"));
    }

    @Test
    void loginPageElementsTest() {
        LoginPage loginPage = open(LOGIN_PAGE_URL, LoginPage.class);
        loginPage.getUsernameInput().shouldBe(visible);
        loginPage.getPasswordInput().shouldBe(visible);
        loginPage.getLoginButton().shouldBe(visible, enabled);
    }
}
