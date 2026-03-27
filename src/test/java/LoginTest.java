import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

@Feature("Авторизация")
public class LoginTest extends BaseTest{

    private static final String LOGIN_PAGE_URL = "https://the-internet.herokuapp.com/login";

    @Test
    @Story("Успешный логин")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Провекра входа с валидными учетными данными")
    public void successfulLoginTest() {
        LoginPage loginPage = open(LOGIN_PAGE_URL, LoginPage.class);
        SecureAreaPage secureAreaPage = loginPage.loginSuccess("tomsmith", "SuperSecretPassword!");
        secureAreaPage.verifySuccessMessage("You logged into a secure area!");
    }

    @Test
    @Story("Введение неправильного пароля")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка авторизации при введении неправильного пароля")
    void invalidPasswordTest() {
        LoginPage loginPage = open(LOGIN_PAGE_URL, LoginPage.class);
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("wrongPassword");
        loginPage.clickLogin();
        Attachments.attachScreenshot(Selenide.screenshot(OutputType.BYTES));
        loginPage.getFlashMessage().shouldHave(text("Your password is invalid!"));
    }

    @Test
    @Story("Видимость полей ввода")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверить доступность полей ввода и кнопки LOGIN")
    void loginPageElementsTest() {
        LoginPage loginPage = open(LOGIN_PAGE_URL, LoginPage.class);
        loginPage.getUsernameInput().shouldBe(visible);
        loginPage.getPasswordInput().shouldBe(visible);
        loginPage.getLoginButton().shouldBe(visible, enabled);
    }
}
